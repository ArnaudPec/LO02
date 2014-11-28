package fr.utt.lo02.joueur;

import java.util.HashSet;

import fr.utt.lo02.carte.Carte;

public interface Strategie {

	public Carte[] choisirCarteAJouer();
	public void changerCartes();	

}
