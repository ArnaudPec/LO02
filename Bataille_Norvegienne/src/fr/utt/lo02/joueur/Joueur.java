package fr.utt.lo02.joueur;

import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.TasCache;
import fr.utt.lo02.carte.TasVisible;
import fr.utt.lo02.*;
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
	
	/**
	 * Méthode qui permet d'évaluer si le joueur peut jouer, c'est-à-dire s'il a en sa possession une ou plusieurs cartes supérieures à la dernière carte posée.
	 * On utilise un itérateur pour parcourir la main du joueur. Il est nécessaire de gérer le cas de la carte 7.
	 * @param derniereCarteTapis
	 * @return Un booleen 
	 */
	public boolean peutJouer(Carte derniereCarteTapis){
		
		if(derniereCarteTapis.getValeur()==7){
			
			java.util.Iterator<Carte> it = this.mainJoueur.getListeCartes().iterator();
			while (it.hasNext()) {
				Carte carte = (Carte) it.next();
				if(carte.getValeur()<=7) return true;
			}
			
			return false;
		}
		
		else{
			
			java.util.Iterator<Carte> it = this.mainJoueur.getListeCartes().iterator();
			while (it.hasNext()) {
				Carte carte = (Carte) it.next();
				if(carte.estSuperieurOuEgale(derniereCarteTapis)) return true;
			}
			
			return false;
		}
 	}
	
	public void changerCarte() {
		//fera appel à choisirCarte et sera implémenté dans les classes filles afin de définir un choix inhérent au type de joueur et donc sa stratégie
	}

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
