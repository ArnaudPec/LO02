package fr.utt.lo02.carte;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MainJoueur extends Tas {

	/**
	 * Liste utilisée pour stocker les cartes qui ne sont pas jouable à un tour
	 * donnée. Permet de ne travailler que sur une main de carte jouables au
	 * moment de l'action de jouer
	 */
	private LinkedList<Carte> listesCartesNonJouable;

	public MainJoueur() {
		super.listeCartes = new LinkedList<Carte>();
		this.listesCartesNonJouable = new LinkedList<Carte>();
	}

	/**
	 * Méthode qui permet de savoir si la main contient une carte de valeur
	 * donnee
	 * 
	 * @param un
	 *            entier, valeur de carte à rechercher
	 * @return un booleen qui indique la présence ou non d'une carte de valeur
	 *         donnée
	 */
	public boolean contenirCarte(int valeur) {
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if (carte.getValeur() == valeur)
				return true;
		}
		return false;
	}

	/**
	 * Méthode qui permet de savoir si la main contient des cartes non spéciales
	 * 
	 * @return un booleen qui indique la présence ou non d'une carte spéciale
	 */
	public boolean contenirCarteNonSpeciale() {
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if (!carte.estSpeciale())
				return true;
		}
		return false;
	}

	/**
	 * Méthode qui permet de savoir si la main contient des cartes spéciales
	 * 
	 * @return un booleen qui indique la présence ou non d'une carte spéciale
	 */
	public boolean contenirCarteSpeciale() {
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if (carte.estSpeciale())
				return true;
		}
		return false;
	}

	/**
	 * Méthode permettant de savoir si la main contient des cartes jouables.
	 */
	public boolean contenirCartesJouables(Carte derniereCarte) {
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if (carte.estPosable(derniereCarte))
				return true;
		}
		return false;
	}

	/**
	 * Cette méthode permet de prendre une carte de la main du Joueur et la
	 * supprime. Elle est différente de getCarte qui ne fait que retourner la
	 * Carte sans la supprimer. Elle est utile pour faire jouer le joueur.
	 * 
	 * @param position
	 * @return la carte à jouer.
	 */
	public Carte prendreCarte(int position) {

		Carte carte = super.listeCartes.get(position);
		super.listeCartes.remove(position);

		return carte;
	}

	/**
	 * Méthode qui permet de récupérer une carte spéciale contenue dans la
	 * MainJoueur.
	 * 
	 * @return une carte spéciale
	 */
	public Carte prendreCarteSpeciale() {
		Collections.shuffle(super.listeCartes);
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if (carte.estSpeciale()) {
				it.remove();
				return carte;
			}
		}
		return null;
	
	}

	/**
	 * Méthode qui permet de récupérer une carte non spéciale contenue dans la
	 * MainJoueur.
	 * 
	 * @return une carte non spéciale
	 */
	public Carte prendreCarteNonSpeciale() {
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if (!carte.estSpeciale()) {
				it.remove();
				return carte;
			}
		}
		return null;
	
	}

	/**
	 * Cette méthode permet de prendre une carte de la main du Joueur et la
	 * supprime. Elle permet de récupérer une carte sélectionnée par sa valeur.
	 * 
	 * @param valeur
	 *            de la carte à prendre
	 * @return une carte de valeur donnée, ou null si une telle carte est
	 *         absente de la main
	 */

	public Carte prendreCarteValeur(int valeur) {
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if (carte.getValeur() == valeur) {
				it.remove();
				return carte;
			}
		}

		return null;
	}
	
	/**
	 * Méthode permettant de retourner la plus petite valeur de carte contenue dans la main.
	 * @return valeurMin
	 */
	public int calculerPlusPetiteValeur(){
		
		int valeurMin = 15;
		for (Iterator<Carte> iterator = super.listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if(carte.getValeur()<valeurMin) valeurMin=carte.getValeur();
		}
		
		return valeurMin;
	}

	/**
	 * Méthode permettant de retourner la plus petite valeur de carte contenue dans la main.
	 * @return valeurMin
	 */
	public int calculerPlusGrandeValeur(){
		
		int valeurMax = 0;
		for (Iterator<Carte> iterator = super.listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if(carte.getValeur()>valeurMax) valeurMax=carte.getValeur();
		}
		
		return valeurMax;
	}
	/**
	 * Méthode qui permet de déterminer le nombre maximum de carte de même
	 * valeur dans une main. Elle est utile afin d'empêcher un joueur de poser
	 * un nombre n de cartes (avec n>1) alors qu'il ne dispose pas de n cartes
	 * de même valeur. Pour ce faire on parcourt la main et on complète un
	 * tableau avec le nombre d'occurences de chaque valeur. Un tri du tableau
	 * nous permettant d'obtenir la valeur maximale.
	 * 
	 * @return le nombre de maximum de carte de carte de même valeur par valeur
	 */
	public int calculerNbMaxCarteMemeValeur() {

		int max = 0;
		int[] tab = new int[16];
		for (int i = 0; i < tab.length; i++)
			tab[i] = 0;

		for (Iterator<Carte> iterator = super.listeCartes.iterator(); iterator
				.hasNext();) {
			Carte carte = (Carte) iterator.next();
			tab[carte.getValeur()]++;
		}

		for (int i = 0; i < tab.length; i++) {
			if (tab[i] > max)
				max = tab[i];
		}
		return max;

	}

	/**
	 * Méthode qui permet de compter le nombre de cartes de même valeur pour une
	 * carte de valeur donnée
	 * 
	 * @param c
	 *            Carte de valeur donnée
	 * @return le nombre de cartes de même valeur pour la valeur donnée
	 */
	public int calculerNbOccurenceMemeValeur(Carte c) {

		int compteur = 0;

		for (Iterator<Carte> iterator = super.listeCartes.iterator(); iterator
				.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if (c.getValeur() == carte.getValeur())
				compteur++;
		}
		return compteur;

	}

	/**
	 * Méthode qui permet de compter le nombre de cartes de même valeur pour une
	 * carte de valeur donnée
	 * 
	 * @param entier valeur donnée
	 * @return le nombre de cartes de même valeur pour la valeur donnée
	 */
	public int calculerNbOccurenceMemeValeur(int valeur) {

		int compteur = 0;

		for (Iterator<Carte> iterator = super.listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if (valeur == carte.getValeur()) compteur++;
		}
		return compteur;

	}
	
	/**
	 * Méthode permettant de parcourir la main et de renvoyer la position de la
	 * première carte de valeur donnée en paramètre
	 * 
	 * @param valeur dont la position est recherchée
	 * @return position de la prochaine carte de cette même valeur ou la valeur 99 : code d'erreur
	 */
	public int calculerPositionCarteValeur(int valeur) {
		int i = 0;
		for (Iterator<Carte> iterator = super.listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if (carte.getValeur() == valeur) return i;
			i++;
		}
		return 99;
	}

	/**
	 * Méthode permettant de trier les cartes de la main. Les cartes qui ne sont
	 * pas jouables après la dernière carte posée sur le tapis sont placé dans
	 * une liste différente. De cette manière, on peut restreindre efficacement
	 * le choix du joueur.
	 * 
	 * @param derniereCarte
	 *            jouée
	 */
	public void trierCartesJouables(Carte derniereCarte) {

		this.listesCartesNonJouable = new LinkedList<Carte>();
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if (!carte.estPosable(derniereCarte)) {
				this.listesCartesNonJouable.add(carte);
				it.remove();
			}
		}
	}

	/**
	 * Méthode permettant à la suite d'un tour de replacer les cartes non
	 * jouables dans this.listeCartes
	 */
	public void fusionner() {
		super.listeCartes.addAll(this.listesCartesNonJouable);
	}

	public String toString() {
	
		String resultat = "Main \n\n";
		String numCarte = "";
		String bordureDessus = "";
		String dessus = "";
		String milieu = "";
		String bas = "";
		String bordureDessous = "";
	
		for (int i = 0; i < super.listeCartes.size(); i++) {
	
			numCarte += "  n°" + i + "    ";
			bordureDessus += " —————️ " + "  ";
			dessus += "|" + super.listeCartes.get(i).getCouleurAffichage()
					+ "    |  ";
			if (super.listeCartes.get(i).getValeur() == 10) {
				milieu += "| " + super.listeCartes.get(i).getValeurAffichage()
						+ "  |  ";
			} else {
				milieu += "|  " + super.listeCartes.get(i).getValeurAffichage()
						+ "  |  ";
			}
			bas += "|    " + super.listeCartes.get(i).getCouleurAffichage()
					+ "|  ";
			bordureDessous += " —————️ " + "  ";
		}
	
		resultat += numCarte + "\n" + bordureDessus + "\n" + dessus + "\n"
				+ milieu + "\n" + bas + "\n" + bordureDessous + "\n";
	
		return resultat;
	}

}
