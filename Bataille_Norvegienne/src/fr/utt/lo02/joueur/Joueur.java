package fr.utt.lo02.joueur;

public class Joueur implements Strategie{
	
	protected int numJoueur;
	protected String nom;
	protected Main main;
	protected TasCachee tasCachee;
	protected TasVisible tasVisible;
	/**
	 * @param numJoueur
	 * @param nom
	 * @param main
	 * @param tasCachee
	 * @param tasVisible
	 */
	public Joueur(int numJoueur, String nom, Main main, TasCachee tasCachee,
			TasVisible tasVisible) {
		super();
		this.numJoueur = numJoueur;
		this.nom = nom;
		this.main = main;
		this.tasCachee = tasCachee;
		this.tasVisible = tasVisible;
	}
	public int getNumJoueur() {
		return numJoueur;
	}
	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public TasCachee getTasCachee() {
		return tasCachee;
	}
	public void setTasCachee(TasCachee tasCachee) {
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
