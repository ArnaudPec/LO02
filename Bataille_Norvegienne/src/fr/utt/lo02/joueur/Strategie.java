package fr.utt.lo02.joueur;

import fr.utt.lo02.carte.Carte;

public interface Strategie {

	public void jouer();
	public Carte[] choisirCarteAJouer();
	

}
