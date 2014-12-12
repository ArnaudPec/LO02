package fr.utt.lo02.carte;

import java.util.Collections;
import java.util.LinkedList;

import fr.utt.lo02.partie.Partie;

/**
 * Classe singleton qui hérite de Tas
 */
public class Pioche extends Tas {

	/**
	 * Attribut static permettant de stocker l'instance de pioche afin de ne
	 * pouvoir créer qu'une seule pioche.
	 */
	private static Pioche instancePioche = null;

	/**
	 * Constructeur de pioche, crée l'ensemble des cartes de la pioche.
	 */
	private Pioche() {
		super.listeCartes = new LinkedList<Carte>();

		for (int i = 0; i < 4; i++) { 
			for (int j = 2; j < 15; j++) {
				Carte carte = new Carte(i, j);
				this.listeCartes.add(carte);
			}
		}
	}

	/**
	 * Permet d'appeler une instance de pioche, s'il n'y a pas elle en crée une,
	 * sinon elle retourne celle déjà crée.
	 * 
	 * @return une instance de pioche
	 */
	public static Pioche getInstancePioche() {
		Pioche instance;

		if (instancePioche == null) {
			instance = new Pioche();
			instancePioche = instance;
		} else {
			instance = instancePioche;
		}
		return instance;
	}

	/**
	 * Ajoute un autre paquet de carte a la pioche (dans le cas ou il y'a plus
	 * que 5 joueurs).
	 */
	public void ajouterUnSecondJeuDeCarte() {
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 15; j++) {
				Carte carte = new Carte(i, j);
				super.listeCartes.add(carte);
			}
		}
	}

	/**
	 * Permet de prendre la première carte de la pioche et de la supprimer de la
	 * pioche.
	 * 
	 * @return la première carte de la pioche.
	 */
	public Carte prendreCarteDuDessus() {
		return super.listeCartes.pollFirst();
	}

	/**
	 * Permet de mélanger la pioche
	 */
	public void melanger() {
		Collections.shuffle(this.listeCartes);
	}

	/**
	 * Permet de distribuer les cartes entre les différents joueurs au début de
	 * partie. 3 cartes face cachée, 3 cartes face visible, 3 cartes en main
	 * 
	 * @param partie
	 */
	public void distribuerCarte(Partie partie) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < partie.getNbJoueurs(); j++) {
				Carte carteTire = this.prendreCarteDuDessus();
				carteTire.setVisibilite(false);
				partie.getJoueur(j).getTasCache().ajouterCarte(carteTire);
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < partie.getNbJoueurs(); j++) {
				Carte carteTire = this.prendreCarteDuDessus();
				partie.getJoueur(j).getTasVisible().ajouterCarte(carteTire);
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < partie.getNbJoueurs(); j++) {
				Carte carteTire = this.prendreCarteDuDessus();
				partie.getJoueur(j).getMainJoueur().ajouterCarte(carteTire);
			}
		}

	}

}
