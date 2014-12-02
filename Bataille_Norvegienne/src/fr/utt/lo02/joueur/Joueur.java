package fr.utt.lo02.joueur;


import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.TasCache;
import fr.utt.lo02.carte.TasVisible;

public class Joueur implements Strategie{
	
	protected int numJoueur;
	protected String nom;
	protected MainJoueur mainJoueur;
	protected TasCache tasCache;
	protected TasVisible tasVisible;
	
	public Joueur(String nomJoueur, int numJoueur)
	{
		this.numJoueur = numJoueur;
		this.nom = nomJoueur;
		this.mainJoueur = new MainJoueur();
		this.tasCache = new TasCache();
		this.tasVisible = new TasVisible();
	}
	
	/**
	 * Méthode permettant de déterminer si un joueur a gagné, c'est à dire si tous ses tas sont vides
	 * @return un booleen
	 */
	public boolean estGagnant(){
		
		return (this.tasCache.getListeCartes().isEmpty() && this.tasVisible.getListeCartes().isEmpty() && this.mainJoueur.getListeCartes().isEmpty());
	}
	
	/**
	 * Méthode qui permet d'évaluer si le joueur peut jouer, c'est-à-dire s'il a en sa possession une ou plusieurs cartes jouables
	 * @param derniereCarteTapis
	 * @return Un booleen 
	 */
	public boolean peutJouer(Carte derniereCarteTapis){
		if(this.mainJoueur.contenirCartesJouables(derniereCarteTapis)) return true;
		else return false;
 	}
	
	/**
	 * Méthode permettant au joueur de choisir la ou les cartes de sa main qu'il souhaite jouer. Son implémentation est différente selon le type de joueur.
	 */
	public  Carte[] choisirCarteAJouer(Carte derniereCarte) { 
		return null;
	}
	

	/**
	 * Méthode permettant au joueur d'échanger les cartes de sa main avec celles de son tas visible. Son implémentation est différente selon le type de joueur.
	 */
	public void changerCartes() {
		
	}

	public String toString() {
		return "Joueur [numJoueur=" + numJoueur + ", nom=" + nom + ", mainJoueur=" + mainJoueur + ", tasCache=" + tasCache+ ", tasVisible=" + tasVisible + "]";
	}
	
	public int getNumJoueur() {
		return numJoueur;
	}
	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public MainJoueur getMainJoueur() {
		return this.mainJoueur;
	}
	public void setMainJoueur(MainJoueur mainJoueur) {
		this.mainJoueur = mainJoueur;
	}
	public TasCache getTasCache() {
		return this.tasCache;
	}
	public void setTasCache(TasCache tasCachee) {
		this.tasCache = tasCachee;
	}
	public TasVisible getTasVisible() {
		return tasVisible;
	}
	public void setTasVisible(TasVisible tasVisible) {
		this.tasVisible = tasVisible;
	}

	

}
