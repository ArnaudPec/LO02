package fr.utt.lo02.joueur;

import java.util.ArrayList;

import fr.utt.lo02.carte.Carte;

/**
 * Interface définissant la stratégie des joueurs. 
 *
 */
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

	/**
	 * Cette méthode permet au joueur de désigner un autre joueur de la partie. Est utilisé notamment dans le cas d'une action spéciale à la pose d'un as.
	 * @param liste
	 * @return un entier, le numéri du joueur choisi
	 */
	public int choisirUnJoueur(ArrayList<Joueur> liste);
}
