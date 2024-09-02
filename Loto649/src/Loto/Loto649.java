/*
 * Fichier : Loto649.java11 févr. 2022
 * Auteur : Nom Prénom : 201974751
 * Date de création : {date}
 */
package Loto;

import java.util.Arrays;
import java.util.Random;

/**
 * Loto649: Description de la classe Auteur : 201974751
 */
public class Loto649 {

	// Les attributs

	private final int MAX_ESSAIS = 3;
	private final int TAILLE = 6;
	private int[] combinaisonGagnante;
	private int[] combinaison;
	private int nbEssais;
	private int nbNumerosTrouves;
	private int nbEssaisG;
	private static int nbTirages;

	// Les methodes

	/**
	 * Crée une objet de type Loto649
	 */
	public Loto649() {
		nbTirages++;
	}

	/**
	 * Génère une combinaison de 6 chiffres qui seront la combinaison gagnante
	 */
	public void genererCombinaisonG() {
		nbEssaisG = 0;
		nbNumerosTrouves = 0;
		combinaisonGagnante = new int[TAILLE];
		Random valeur = new Random();

		// Met les valeurs randoms crée dans le tableau
		for (int i = 0; i < TAILLE; i++) {
			int nombre = (valeur.nextInt(49) + 1);
			boolean verif = true;

			// Verifie si le nombre random crée existe deja dans le tableau
			do {
				for (int j = i; j >= 0; j--) {
					if (nombre == combinaisonGagnante[j]) {
						verif = true;
						nombre = (valeur.nextInt(49) + 1);
					} else if (j == 0) {
						verif = false;
						combinaisonGagnante[i] = nombre;
					}
				}
			} while (verif == true);
		}

	}

	/**
	 * @param Prend les chiffres données pour voir si elles sont conforment : 01-
	 *              plus grand que 1 02- plus petit que 49 03- ne pas se repéter 04-
	 *              être au nombre de 6;
	 * @return Prue si toute les données repondent a tout les critères et false au
	 *         cas contraire
	 */
	public boolean validerCombinaison(String donnée) {

		boolean validation = true;
		combinaison = new int[TAILLE];

		// Enlève les espaces de trop possible dans la combinaison reçu -
		donnée = donnée.replaceAll("( ){2,}", "");

		// Transforme les données string reçut en tableau string
		String[] items = donnée.split(" ");

		// Verifie s'il y a 6 chiffres
		if (items.length != 6)
			return false;
		else {
			validation = true;
		}

		// Transforme le tableau en int et verifie si les chiffres sont entre 1 - 49
		int[] arrays = new int[TAILLE];
		for (int i = 0; i < items.length; i++) {
			int nb = Integer.parseInt(items[i]);
			if ((nb < 1) || (nb > 49))
				return false;
			else {
				validation = true;
				arrays[i] = nb;
			}
		}

		// Verifie s'il y'a des doublons dans le tableau
		for (int i = 0; i < TAILLE; i++) {
			boolean verif = true;
			do {
				for (int j = i + 1; j < TAILLE; j++) {
					if (arrays[i] == arrays[j])
						return false;
				}
				verif = false;
				validation = true;
			} while (verif == true);
		}

		// Instencie le tableau combinaison
		if (validation == true) {
			for (int i1 = 0; i1 < TAILLE; i1++) {
				combinaison[i1] = arrays[i1];
			}
		}
		return validation;

	}

	/**
	 * Trie en ordre les chiffres données par l'utilisateur ainsi que la serie
	 * gagante, puis les comparent pour voir combien de chiffre en commun ils ont
	 */
	public void verifierPresence() {
		nbEssaisG = 0;

		// Trie les deux tableaux de combinaison
		Arrays.sort(combinaison);
		Arrays.sort(combinaisonGagnante);

		// Compare les deux tableaux
		int NumerosTrouves = 0;
		for (int e = 0; e < combinaison.length; e++) {
			int valRech = combinaison[e];
			for (int a = 0; a < combinaisonGagnante.length; a++) {
				if (combinaisonGagnante[a] == valRech) {
					NumerosTrouves += 1;
				}
			}
		}
		if (NumerosTrouves >= 2) {
			nbEssaisG = 1;
		}
		nbNumerosTrouves = NumerosTrouves;
	}

	/**
	 * Retourne les resultats de l'essais : la combinaison du joueur, le nombre de
	 * numero trouvé et le gains
	 */
	@Override
	public String toString() {
		this.calculerGains();
		String retour = "\t";
		String essais = "essais n: " + nbEssais;
		String verdictTour = "";
		// Affiche le nombre d'essais deja éffectué
		retour = retour + "\n\t" + essais + "\n\tla combinaison du joueur : ";
		for (int e = 0; e < TAILLE; e++) {
			retour += combinaison[e] + " ";
		}
		// Affiche le gains éffectué par le joueur
		if (nbEssaisG >= 1) {
			verdictTour = "\n\ttour gagné \n\tvous avez gagné : " + this.calculerGains() + " $";
			if (this.calculerGains() == 1000000) {
				verdictTour = "\n\ttour gagné \n\tvous avez gagné : " + this.calculerGains() + " million";
			}
		} else {
			verdictTour = "\n\ttour perdu";
		}
		retour = retour + "\n\tnumero trouvé : " + nbNumerosTrouves + verdictTour;
		return retour;
	}

	/**
	 * @return Calcule combien d'argent le joueur a gagner en fonction du nombre de
	 *         chiffre gagant trouvé
	 */
	public int calculerGains() {
		int gains = 0;
		if (nbNumerosTrouves < 2) {
			gains = 0;
		} else if (nbNumerosTrouves == 2) {
			gains = 10;
		} else if (nbNumerosTrouves == 3) {
			gains = 20;
		} else if (nbNumerosTrouves == 4) {
			gains = 100;
		} else if (nbNumerosTrouves == 5) {
			gains = 5000;
		} else if (nbNumerosTrouves == 6) {
			gains = 1000000;
		}
		return gains;
	}

	// Les methodes Set et Get pour les attributs.

	/**
	 * @return the combinaisonGagnante
	 */
	public int[] getCombinaisonGagnante() {
		return combinaisonGagnante;
	}

	/**
	 * @param combinaisonGagnante the combinaisonGagnante to set
	 */
	public void setCombinaisonGagnante(int[] combinaisonGagnante) {
		this.combinaisonGagnante = combinaisonGagnante;
	}

	/**
	 * @return the combinaison
	 */
	public int[] getCombinaison() {
		return combinaison;
	}

	/**
	 * @param combinaison the combinaison to set
	 */
	public void setCombinaison(int[] combinaison) {
		this.combinaison = combinaison;
	}

	/**
	 * @return the nbEssais
	 */
	public int getNbEssais() {
		return nbEssais;
	}

	/**
	 * @param nbEssais the nbEssais to set
	 */
	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}

	/**
	 * @return the nbNumerosTrouves
	 */
	public int getNbNumerosTrouves() {
		return nbNumerosTrouves;
	}

	/**
	 * @param nbNumerosTrouves the nbNumerosTrouves to set
	 */
	public void setNbNumerosTrouves(int nbNumerosTrouves) {
		this.nbNumerosTrouves = nbNumerosTrouves;
	}

	/**
	 * @return the nbEssaisG
	 */
	public int getNbEssaisG() {
		return nbEssaisG;
	}

	/**
	 * @param nbEssaisG the nbEssaisG to set
	 */
	public void setNbEssaisG(int nbEssaisG) {
		this.nbEssaisG = nbEssaisG;
	}

	/**
	 * @return the nbTirages
	 */
	public int getNbTirages() {
		return nbTirages;
	}

	/**
	 * @param nbTirages the nbTirages to set
	 */
	public void setNbTirages(int nbTirages) {
		this.nbTirages = nbTirages;
	}

	/**
	 * @return the mAX_ESSAIS
	 */
	public int getMAX_ESSAIS() {
		return MAX_ESSAIS;
	}

	/**
	 * @return the tAILLE
	 */
	public int getTAILLE() {
		return TAILLE;
	}
}
