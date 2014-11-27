package fr.utt.lo02.joueur;

import fr.utt.lo02.carte.Carte;
import fr.utt.*;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	

	public static void main(String args[]) {
		System.out.println("hello");
		Humain j = new Humain("bob", 4);
		j.getTasCache().ajouterCarte(new Carte(3, 11));
		j.getTasCache().ajouterCarte(new Carte(1, 10));
		j.getTasVisible().ajouterCarte(new Carte(1, 10));
		System.out.println(j);
		

	}

}
