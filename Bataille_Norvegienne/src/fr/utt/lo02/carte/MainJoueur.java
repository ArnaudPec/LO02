package fr.utt.lo02.carte;


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
	
	/**
	 * Cette méthode permet de prendre une carte de la main du Joueur et la supprime.
	 * Elle permet de récupérer une carte sélectionnée par sa valeur.
	 * 
	 * @param valeur de la carte à prendre
	 * @return une carte de valeur donnée, ou null si une telle carte est absente de la main
	 */
	
	public Carte prendreCarteValeur(int valeur)
	{
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.getValeur()==valeur){
				it.remove();
				return carte;
			}
		}

		return null;
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
	
<<<<<<< HEAD
=======
	public static void main(String[] args) {
		
		MainJoueur main = new MainJoueur();
		
		Carte carte = new Carte(0,2);
		Carte carte2 = new Carte(1,8);
		Carte carte3 = new Carte(2,10);
		Carte carte4 = new Carte(3,7);
		main.listeCartes.add(carte);
		main.listeCartes.add(carte2);
		main.listeCartes.add(carte3);
		main.listeCartes.add(carte4);
		System.out.println(main);
		LinkedList<Carte> list = main.getListeCartesJouables(new Carte(0,9));
		System.out.println(list);
		
		
	}
	
>>>>>>> FETCH_HEAD
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
		
		for (Iterator<Carte> iterator = listeCartes.iterator(); iterator.hasNext();) {
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
		
		for (Iterator<Carte> iterator = listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if (c.getValeur()==carte.getValeur()) compteur ++;
		}
				return compteur;
		
	}
	
	/**
	 * Méthode qui permet de compter le nombre de cartes de même valeur pour une carte de valeur donnée
	 * @param entier valeur donnée
	 * @return le nombre de cartes de même valeur pour la valeur donnée
	 */
	public int calculerNbOccurenceMemeValeur(int valeur) {

		int compteur=0;
		
		for (Iterator<Carte> iterator = listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if (valeur==carte.getValeur()) compteur ++;
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
		for (Iterator<Carte> iterator = listeCartes.iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			if(carte.getValeur()==valeur) return i;
			i++;
		}
		return 99;
	}
	
	/**
	 * Méthode qui permet de savoir si la main contient une carte de valeur donnee
	 * @param un entier, valeur de carte à rechercher
	 * @return un booleen qui indique la présence ou non d'une carte de valeur donnée
	 */
	public boolean contenirCarte(int valeur){
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.getValeur()==valeur)return true;
		}
		return false;
	}
	
	
	/**
	 * Méthode qui permet de savoir si la main contient des cartes non spéciales
	 * @return un booleen qui indique la présence ou non d'une carte spéciale
	 */
	public boolean contenirCarteNonSpeciale(){
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(!carte.estSpeciale())return true;
		}
		return false;
	}
	
	/**
	 * Méthode qui permet de récupérer une carte non spéciale contenue dans la MainJoueur.
	 * @return une carte non spéciale
	 */
	public Carte prendreCarteNonSpeciale() {
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(!carte.estSpeciale()) { 
				it.remove();
				return carte;
			}
		}
		return null;
		
	}
	
	/**
	 * Méthode qui permet de savoir si la main contient des cartes spéciales
	 * @return un booleen qui indique la présence ou non d'une carte spéciale
	 */
	public boolean contenirCarteSpeciale(){
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.estSpeciale())return true;
		}
		return false;
	}
	
	
	/**
	 * Méthode qui permet de récupérer une carte spéciale contenue dans la MainJoueur.
	 * @return une carte spéciale
	 */
	public Carte prendreCarteSpeciale() {
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.estSpeciale()) { 
				it.remove();
				return carte;
			}
		}
		return null;
		
	}
	

<<<<<<< HEAD
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
=======
	/**
	 * Méthode permettant de savoir si la main contient des cartes jouables.
	 */
	public boolean contenirCartesJouables(Carte derniereCarte){
		LinkedList<Carte> listeCartesJouables = new LinkedList<Carte>();
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.estPosable(derniereCarte)) return true;
		}
		return false;
	}
	
	/**
	 * Méthode permettant de récupérer de la main les cartes jouables : prendre en compte le 7, le 2 ....
	 * @return la liste des cartes jouables
	 */
	public LinkedList<Carte> getListeCartesJouables(Carte derniereCarte){
		LinkedList<Carte> listeCartesJouables = new LinkedList<Carte>();
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.estPosable(derniereCarte)) listeCartesJouables.add(carte);
		}
		return listeCartesJouables;
	}
	
>>>>>>> FETCH_HEAD
	
}
