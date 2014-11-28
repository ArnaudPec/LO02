package fr.utt.lo02.carte;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MainJoueur extends Tas{

	public MainJoueur() {
		this.listeCartes = new LinkedList<Carte>();
	}
	
	/**
	 * Cette méthode permet de prendre une carte de la main du Joueur et la supprime.
	 * Elle est différente de getCarte qui ne fait que retourner la Carte sans la supprimer.
	 * Elle est utile pour faire jouer le joueur. 
	 * @param position
	 * @return la carte à jouer.
	 */
	public Carte prendreCarte(int position)
	{

		Carte carte = this.listeCartes.get(position);
		this.listeCartes.remove(position);
		
		return carte;
	}

	@Override
	public String toString(){
		
		String resultat = "Main \n\n";
		String numCarte = "";
		String bordureDessus ="";
		String dessus = "";
		String milieu = "";
		String bas = "";
		String bordureDessous = "";
		
		for (int i = 0; i < this.listeCartes.size(); i++) {
			
			numCarte+= "  n°" + i + "    ";
			bordureDessus+= " —————️ " + "  ";
			dessus+= "|"+ this.listeCartes.get(i).getCouleurAffichage() + "    |  ";
			milieu+= "|  " + this.listeCartes.get(i).getValeurAffichage() + "  |  ";
			bas+= "|    "+ this.listeCartes.get(i).getCouleurAffichage() + "|  ";
			bordureDessous+= " —————️ " + "  ";		
		}
		
		resultat += numCarte + "\n" + bordureDessus + "\n" + dessus+ "\n" + milieu + "\n" + bas + "\n" + bordureDessous + "\n";
				
		return resultat;
	}
	
	public static void main(String[] args) {
		
		MainJoueur main = new MainJoueur();
		
		Carte carte = new Carte(0,2);
		Carte carte2 = new Carte(1,11);
		Carte carte3 = new Carte(2,11);
		Carte carte4 = new Carte(3,14);
		main.listeCartes.add(carte);
		main.listeCartes.add(carte2);
		main.listeCartes.add(carte3);
		main.listeCartes.add(carte4);

		System.out.println(main.toString());
		
//		System.out.println(main.calculerNbMaxCarteMemeValeur());
//		System.out.println(main.calculerNbOccurenceMemeValeur(new Carte(1, 11)));
		
	}
	
	/**
	 * Méthode qui permet de déterminer le nombre maximum de carte de même valeur dans une main.
	 * Elle est utile afin d'empêcher un joueur de poser un nombre n de cartes (avec n>1) alors qu'il ne dispose pas de n cartes
	 * de même valeur.
	 * Pour ce faire on parcourt la main et on complète un tableau avec le nombre d'occurences de chaque valeur.
	 * Un tri du tableau nous permettant d'obtenir la valeur maximale.
	 * @return le nombre de maximum de carte de carte de même valeur par valeur
	 */
	public int calculerNbMaxCarteMemeValeur() {

		int max=0;
		int [] tab = new int[16];
		for (int i = 0; i < tab.length; i++) tab[i]=0;
		
		for (Iterator iterator = listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			tab[carte.getValeur()]++;
		}
		
		for (int i = 0; i < tab.length; i++){ 
			if(tab[i]>max) max = tab[i];
			}
		return max;
		
	}
	
	/**
	 * Méthode qui permet de compter le nombre de cartes de même valeur pour une carte de valeur donnée
	 * @param c Carte de valeur donnée
	 * @return le nombre de cartes de même valeur pour la valeur donnée
	 */
	public int calculerNbOccurenceMemeValeur(Carte c) {

		int compteur=0;
		
		for (Iterator iterator = listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if (c.getValeur()==carte.getValeur()) compteur ++;
		}
				return compteur;
		
	}
	
	/**
	 * Méthode permettant de parcourir la main et de renvoyer la position de la première carte de valeur donnée en paramètre
	 * @param valeur dont la position est recherchée
	 * @return position de la prochaine carte de cette même valeur ou la valeur 99 : code d'erreur
	 */
	public int calculerPositionCarteValeur(int valeur){
		int i=0;
		for (Iterator iterator = listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if(carte.getValeur()==valeur) return i;
			i++;
		}
		return 99;
	}
	
}
