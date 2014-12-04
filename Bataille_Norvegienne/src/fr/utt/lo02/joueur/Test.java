package fr.utt.lo02.joueur;

import fr.utt.lo02.carte.Carte;


public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	

	public static void main(String args[]) {
		
		
		Joueur bob  = new Humain("bob", 4);
		bob.getMainJoueur().ajouterCarte(new Carte(2, 3));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 2));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 11));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 15));
		
		
		bob.getTasCache().ajouterCarte(new Carte(2, 8));
		bob.getTasCache().ajouterCarte(new Carte(1, 4));
		bob.getTasCache().ajouterCarte(new Carte(3, 7));

		
		bob.getTasVisible().ajouterCarte(new Carte(1, 4));
		bob.getTasVisible().ajouterCarte(new Carte(1, 5));
		bob.getTasVisible().ajouterCarte(new Carte(0, 13));


		Carte carteTest = new Carte(0, 7);
		
		System.out.println(bob.peutJouer(carteTest));
		bob.choisirCarteAJouer(carteTest);	
		System.out.println(bob.getMainJoueur());

		//bob.changerCartes();
		//System.out.println(bob.getMainJoueur());

	
	}

}
