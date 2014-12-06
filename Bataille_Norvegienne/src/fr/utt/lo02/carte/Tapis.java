package fr.utt.lo02.carte;

import java.util.LinkedList;

public class Tapis extends Tas {

	private static Tapis instanceTapis;

	private Tapis() {
		super.listeCartes = new LinkedList<Carte>();
	}

	/**
	 * Méthode pour récupérer une instance de Tapis.
	 * 
	 * @return une instance de Tapis
	 */
	public static Tapis getInstanceTapis() {
		Tapis instance;

		if (instanceTapis == null) {
			instance = new Tapis();
			instanceTapis = instance;
		} else {
			instance = instanceTapis;
		}

		return instance;
	}

	/**
	 * Permet de récupérer la première carte de la liste.
	 * 
	 * @return Une carte.
	 */
	public Carte carteDuDessus() {

		Carte carte = null;

		if (!super.listeCartes.isEmpty()) {
			carte = super.listeCartes.getLast();
		}

		return carte;
	}

	/**
	 * Permet de vider le tapis, supprime tout les éléments de la liste.
	 */
	public void viderTapis() {
		super.listeCartes.clear();
	}

	/**
	 * Méthode permettant de faire prendre tout le contenu du tapis à joueur.
	 * 
	 * @return La liste des cartes à ajouter à la main du joueur
	 */
	public LinkedList<Carte> prendreTapis() {
		LinkedList<Carte> liste = new LinkedList<Carte>();
		liste.addAll(this.listeCartes);
		this.viderTapis();
		return liste;

	}

	@Override
	public String toString() {
		String res = "";
		if (this.listeCartes.isEmpty()) {
			res = "Pas de carte";
		} else {
			res = "Tapis \n" + carteDuDessus().toString() + "\n";
		}
		return res;
	}

	public static void main(String[] args) {

	}
}
