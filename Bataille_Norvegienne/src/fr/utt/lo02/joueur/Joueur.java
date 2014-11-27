package fr.utt.lo02.joueur;

import java.util.Scanner;

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
	
	public boolean estGagnant(){
		return (this.tasCache.getListeCartes().isEmpty() && this.tasVisible.getListeCartes().isEmpty() && this.mainJoueur.getListeCartes().isEmpty());
	}
	
	public boolean peutJouer(){
		if( ){
			return true;
		}
		else return false;
		
	}
	
	public void changerCarte() {
		//fera appel à choisirCarte et sera implémenté dans les classes filles afin de définir un choix inhérent au type de joueur et donc sa stratégie
	}
	

//	public Carte[] choisirCarteAJouer(){
//		
//		System.out.println("choisissez les ou la carte(s) que vous voulez jouer.");
//		Scanner sc = new Scanner(System.in);
//		String str = sc.nextLine();
//		
//		String[] split = str.split("-");
//		
//		Carte[] listeCarte = new Carte[split.length];
//		
//		for(int i = 0; i < split.length; i++) {
//			listeCarte[i] = this.mainJoueur.getListeCartes().get(Integer.parseInt(split[i]));
//		}

	public String toString() {
		return "Joueur [numJoueur=" + numJoueur + ", nom=" + nom
				+ ", mainJoueur=" + mainJoueur + ", tasCache=" + tasCache
				+ ", tasVisible=" + tasVisible + "]";
	}

	public void jouer() {
		// TODO Auto-generated method stub	
	}

	public  Carte[] choisirCarteAJouer() { //sera précisé dans les classes filles
		return null;
		
	}
	
	
}
