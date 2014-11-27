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
		
		
		Joueur bob  = new Joueur("bob", 4);
		bob.getMainJoueur().ajouterCarte(new Carte(2, 9));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 10));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 11));
		bob.getMainJoueur().ajouterCarte(new Carte(2, 12));

		//System.out.println(bob.peutJouer(new Carte(2, 2)));
		System.out.println(bob.peutJouer(new Carte(2, 14)));
		
		
	}

}
