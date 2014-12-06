package fr.utt.lo02.joueur;

import fr.utt.lo02.carte.Carte;

public interface Strategie {

	/**
	 * Cette méthode permet au joueur de sélectionner les cartes qu'il souhaite jouer. Différent comportements sont définis en fonction du type de joueur
	 * @param derniereCarte TODO
	 * @return un tableau de cartes
	 * @see Carte
	 */
	public Carte[] choisirCarteAJouer(Carte derniereCarte);
	
	/**
	 * Cette méhode permet au joueur de changer les cartes de sa main avec celles de son tas visibles en début de partie. Le mécanisme de choix dépend du type de joueur.
	 */
	public void changerCartes();	

	public void interfaceDemandeChoisirUnJoueur();
}
