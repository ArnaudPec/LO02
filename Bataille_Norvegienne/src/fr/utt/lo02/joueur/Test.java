package fr.utt.lo02.joueur;

import sun.security.jca.GetInstance;
import fr.utt.lo02.*;
import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.Tapis;
import fr.utt.lo02.carte.TasCache;
import fr.utt.lo02.carte.TasVisible;
import fr.utt.*;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	

	public static void main(String args[]) {
		
		
		Joueur bob  = new Humain ("bob", 4);
		bob.getMainJoueur().ajouterCarte(new Carte(2, 8));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 10));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 11));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 12));
		
		
		bob.getTasCache().ajouterCarte(new Carte(2, 8));
		bob.getTasCache().ajouterCarte(new Carte(1, 4));
		bob.getTasCache().ajouterCarte(new Carte(3, 7));

		
		bob.getTasVisible().ajouterCarte(new Carte(1, 8));
		bob.getTasVisible().ajouterCarte(new Carte(1, 5));
		bob.getTasVisible().ajouterCarte(new Carte(0, 2));



		//System.out.println(bob.peutJouer(new Carte(2, 2)));
		//System.out.println(bob.peutJouer(new Carte(2, 5)));
		
		
		//System.out.println(bob);
		
		
		bob.changerCartes();
		bob.choisirCarteAJouer();

	
	}

}
