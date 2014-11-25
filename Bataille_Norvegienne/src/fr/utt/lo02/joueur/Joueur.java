package fr.utt.lo02.joueur;

import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.TasCache;
import fr.utt.lo02.carte.TasVisible;

public class Joueur implements Strategie{
	
	protected int numJoueur;
	protected String nom;
	protected MainJoueur mainJoueur;
	protected TasCache tasCachee;
	protected TasVisible tasVisible;
	/**
	 * @param numJoueur
	 * @param nom
	 * @param mainJoueur
	 * @param tasCachee
	 * @param tasVisible
	 */
	public Joueur(int numJoueur, String nom, MainJoueur mainJoueur, TasCache tasCache,
			TasVisible tasVisible) {
		super();
		this.numJoueur = numJoueur;
		this.nom = nom;
		this.mainJoueur = mainJoueur;
		this.tasCachee = tasCache;
		this.tasVisible = tasVisible;
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
		this.mainJoueur = this.mainJoueur;
	}
	public TasCache getTasCache() {
		return this.tasCachee;
	}
	public void setTasCache(TasCache tasCachee) {
		this.tasCachee = tasCachee;
	}
	public TasVisible getTasVisible() {
		return tasVisible;
	}
	public void setTasVisible(TasVisible tasVisible) {
		this.tasVisible = tasVisible;
	}
	
	
	public void estDanish() {
		
	} 
	
	public void jouer() {
		
	}
	
	public void changerCarte() {
		
	}

	
	
}
