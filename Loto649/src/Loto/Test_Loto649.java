/*
 * Fichier : Test_Loto649.java13 févr. 2022
 * Auteur : Nom Prénom : 201974751
 * Date de création : {date}
 */
package Loto;

/**
 * Test_Loto649: Description de la classe Auteur : 201974751
 */
public class Test_Loto649 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Loto649 instance = new Loto649();
		instance.validerCombinaison("02 10 06 01 19 03");
		instance.genererCombinaisonG();
		instance.verifierPresence();

		String combinaisonGagnante = " ";
		for (int i = 0; i < 6; i++) {
			combinaisonGagnante += instance.getCombinaisonGagnante()[i] + " ";
		}
		System.out.println("\ncombinaison gagnante : " + combinaisonGagnante);
		System.out.println(instance.toString());

	}

}
