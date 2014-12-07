package fr.utt.lo02.joueur;

import java.util.ArrayList;

import fr.utt.lo02.carte.Carte;


public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	

	public static void main(String args[]) {
		
		
		Joueur bob  = new IaOffensive("bob", 0);
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


		//Carte carteTest = new Carte(0, 7);
		
//		System.out.println(bob.peutJouer(carteTest));
//		bob.choisirCarteAJouer(carteTest);	
//		System.out.println(bob.getMainJoueur());

		Joueur yves = new Humain("Yves", 1);
		Joueur patrick = new Humain("Patrick", 2);
		Joueur jean = new Humain("Jean", 3);
		
		patrick.getMainJoueur().ajouterCarte(new Carte(2, 3));
		yves.getMainJoueur().ajouterCarte(new Carte(2, 2));
		yves.getMainJoueur().ajouterCarte(new Carte(2, 11));
		
		
		ArrayList<Joueur> j = new ArrayList<Joueur>();
		j.add(bob);
		j.add(yves);
		j.add(patrick);
		j.add(jean);
		
		System.out.println(bob.choisirUnJoueur(j));
		
		
	}

}
