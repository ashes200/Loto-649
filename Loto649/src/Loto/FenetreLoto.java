/*
 * Fichier : FenetreLoto.java18 févr. 2022
 * Auteur : Nom Prénom : 201974751
 * Date de création : {date}
 */
package Loto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * FenetreLoto: Description de la classe Auteur : 201974751
 */
public class FenetreLoto extends JFrame {

	private JPanel contentPane;
	private JTextField txtSaisie;
	private JTextArea txtrDevinerLaCombinaisan;
	private JLabel lbEssais;
	private JButton btnAfficher;
	private JButton btnValider;
	private JButton btnNouveauTirage;
	private JLabel lbMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FenetreLoto frame = new FenetreLoto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetreLoto() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbNomJeux01 = new JLabel("Loto");
		lbNomJeux01.setForeground(Color.BLUE);
		lbNomJeux01.setHorizontalAlignment(SwingConstants.CENTER);
		lbNomJeux01.setFont(new Font("Tahoma", Font.BOLD, 35));
		lbNomJeux01.setBounds(338, 10, 84, 55);
		contentPane.add(lbNomJeux01);

		JLabel lbNomJeux02 = new JLabel("649");
		lbNomJeux02.setForeground(Color.RED);
		lbNomJeux02.setHorizontalAlignment(SwingConstants.CENTER);
		lbNomJeux02.setFont(new Font("Tahoma", Font.BOLD, 35));
		lbNomJeux02.setBounds(432, 10, 67, 55);
		contentPane.add(lbNomJeux02);

		JLabel lbSaisie = new JLabel("Votre combinaison");
		lbSaisie.setHorizontalAlignment(SwingConstants.CENTER);
		lbSaisie.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbSaisie.setBounds(32, 83, 163, 55);
		contentPane.add(lbSaisie);

		txtSaisie = new JTextField();
		txtSaisie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSaisie.setBounds(202, 89, 114, 46);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);

		btnValider = new JButton("Valider");
		btnValider.addActionListener(new BtnValiderActionListener());
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnValider.setBounds(326, 85, 114, 50);
		btnValider.setEnabled(false);
		contentPane.add(btnValider);

		btnAfficher = new JButton("Afficher");
		btnAfficher.addActionListener(new BtnAfficherActionListener());
		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAfficher.setBounds(450, 85, 114, 50);
		btnAfficher.setEnabled(false);
		contentPane.add(btnAfficher);

		btnNouveauTirage = new JButton("Nouveau tirage");
		btnNouveauTirage.addActionListener(new BtnNouveauTirageActionListener());
		btnNouveauTirage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNouveauTirage.setBounds(574, 85, 212, 50);
		contentPane.add(btnNouveauTirage);

		txtrDevinerLaCombinaisan = new JTextArea(
				"Deviner la combinaisan gagnante de 6 chiffre compris entre 1 et 49.\r\nChaque chiffre est unique et separer par un espace. EX: 1 2 3 4 5 6\r\n\r\nveuiller trouver au moins un nombre pour gagner un prix :\r\n2 numéros : gain de 10 $\r\n3 numéros : gain de 20 $\r\n4 numéros : gain de 100 $\r\n5 numéros : gain de 5000 $\r\n6 numéros : gain de 1 million $");
		txtrDevinerLaCombinaisan.setFont(new Font("Monospaced", Font.PLAIN, 17));
		txtrDevinerLaCombinaisan.setEditable(false);
		txtrDevinerLaCombinaisan.setBounds(32, 216, 754, 437);
		contentPane.add(txtrDevinerLaCombinaisan);

		lbMessage = new JLabel("Veuillez efectuer un tirage");
		lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbMessage.setForeground(Color.BLACK);
		lbMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbMessage.setBounds(32, 177, 754, 29);
		contentPane.add(lbMessage);

		lbEssais = new JLabel("Essais 0/3");
		lbEssais.setHorizontalAlignment(SwingConstants.CENTER);
		lbEssais.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbEssais.setBounds(32, 148, 754, 29);
		contentPane.add(lbEssais);
	}

	// Instancie l'objet loto
	Loto649 loto = new Loto649();
	// Crée la variable cash qui sera la somme totale gagné par le joueur durant une
	// partie complete (les 3 essais)
	int cash;

	/**
	 *
	 * BtnNouveauTirageActionListener: Genère une nouvelle combinaison gagnante, en
	 * plus de renitialiser le nombre d'essais et le montant gagner Auteur :
	 * 201974751
	 */
	private class BtnNouveauTirageActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Génère une nouvelle combinaison gagnante
			loto.genererCombinaisonG();

			// Rénitialise certaine valeur a 0
			loto.setNbEssais(0);
			cash = 0;

			lbMessage.setForeground(Color.BLACK);
			btnValider.setEnabled(true);
			btnNouveauTirage.setEnabled(false);
			txtrDevinerLaCombinaisan.setText(
					"Deviner la combinaisan gagnante de 6 chiffre compris entre 1 et 49.\r\nChaque chiffre est unique et separer par un espace. EX: 1 2 3 4 5 6\r\n\r\nveuiller trouver au moins un nombre pour gagner un prix :\r\n2 numéros : gain de 10 $\r\n3 numéros : gain de 20 $\r\n4 numéros : gain de 100 $\r\n5 numéros : gain de 5000 $\r\n6 numéros : gain de 1 million $");

			lbEssais.setText("Essais " + loto.getNbEssais() + "/3");
			lbMessage.setText("Nouvelle combinaison gagnante générer");
		}
	}

	/**
	 * BtnValiderActionListener: Envoie la combinaison saisie pour la verifier et si
	 * correcte, met a jour le nombre d'éssais Auteur : 201974751
	 */
	private class BtnValiderActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Variable a incrementer pour incrementer le nombre d'essais
			int i = loto.getNbEssais();

			// Envoie la saisie dans la methode valideCombinaison() pour faire la
			// verification
			loto.validerCombinaison(txtSaisie.getText());

			// Verifie le resultat de la verification
			boolean verif = loto.validerCombinaison(txtSaisie.getText());
			if (verif == true) {
				loto.verifierPresence();
				i += 1;
				lbMessage.setForeground(Color.BLACK);
				loto.setNbEssais(i);
				lbEssais.setText("Essais " + loto.getNbEssais() + "/3");
				lbMessage.setText("Combinaison valide");
				btnAfficher.setEnabled(true);
				btnValider.setEnabled(false);
				btnNouveauTirage.setEnabled(false);
			} else {
				lbMessage.setForeground(Color.RED);
				btnValider.setEnabled(true);
				btnNouveauTirage.setEnabled(false);
				lbMessage.setText("Combinaison invalide");
			}
		}
	}

	/**
	 * BtnAfficherActionListener: Affiche les resultats de l'essais courant en plus
	 * des revenues totaux gagné au cour d'une partie complête Auteur : 201974751
	 */
	private class BtnAfficherActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String combinaisonGagnante = " ";
			for (int i = 0; i < 6; i++) {
				combinaisonGagnante += loto.getCombinaisonGagnante()[i] + " ";
			}
			// Additionne tous les gains gagné pendant les 3 essais
			cash += loto.calculerGains();

			// Affiche les resultats des 2 premiers essais
			if (loto.getNbEssais() < 3) {
				lbMessage.setForeground(Color.BLACK);
				// txtrDevinerLaCombinaisan.append(loto.toString() + "\n");

				if (loto.getNbEssais() == 1) {
					txtrDevinerLaCombinaisan.setText(loto.toString() + "\n");
				} else {
					txtrDevinerLaCombinaisan.append(loto.toString() + "\n");
				}

				btnValider.setEnabled(true);
				btnAfficher.setEnabled(false);
				btnNouveauTirage.setEnabled(false);
			}
			// Affiche le resultats du dernier essais
			else if (loto.getNbEssais() == 3) {
				lbMessage.setForeground(Color.RED);
				txtrDevinerLaCombinaisan.append(loto.toString());
				txtrDevinerLaCombinaisan.append("\n\n\tvous avez gagner un total de : " + cash
						+ "$\nCombinaison gagnante : " + combinaisonGagnante);
				btnAfficher.setEnabled(false);
				btnNouveauTirage.setEnabled(true);
				lbMessage.setText("Parti terminer. Veuillez relancer une nouvelle partie.");
			}
		}
	}
}
