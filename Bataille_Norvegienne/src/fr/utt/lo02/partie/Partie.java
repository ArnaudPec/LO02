package fr.utt.lo02.partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.Pioche;
import fr.utt.lo02.carte.Tapis;
import fr.utt.lo02.joueur.Joueur;

public class Partie {

	private int nbJoueurs;
	private int joueurCourant;
	private ArrayList<Joueur> listeJoueurs;
	private Pioche pioche;
	private Tapis tapis;
	private static Partie instancePartie;
	

	private Partie() {
		this.listeJoueurs = new ArrayList<Joueur>();
		this.pioche = pioche.getInstancePioche();
		this.tapis = tapis.getInstanceTapis();
		this.joueurCourant = 0;
	}
	
	public static Partie getInstancePartie() {
		
		Partie instance;
		
		if(instancePartie==null)
		{
			instance = new Partie();
			instancePartie = instance;
		}
		else
		{
			instance = instancePartie; 
		}		
		return instance;
	}
	
	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public int getJoueurCourant() {
		return this.joueurCourant;
	}
	
	public Joueur getJoueur(int position){
		Joueur joueur=null;
		try 
		{
			joueur = this.listeJoueurs.get(position);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return joueur;	
	}

	public void setJoueurCourant(int joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public ArrayList<Joueur> getListeJoueurs() {
		return this.listeJoueurs;
	}

	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public Pioche getPioche() {
		return this.pioche;
	}

	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}

	public Tapis getTapis() {
		return this.tapis;
	}

	public void setTapis(Tapis tapis) {
		this.tapis = tapis;
	}

	public static void setInstancePartie(Partie instancePartie) {
		Partie.instancePartie = instancePartie;
	}
	
	
	public void ajouterJoueur(Joueur joueur){
		this.listeJoueurs.add(joueur);
		this.nbJoueurs++;
	}
	
	public void interfaceAjouterJoueur()
	{
		boolean bol = true;
		Scanner sc = new Scanner(System.in);
		
		while(bol)
		{
			System.out.println("Voulez vous ajouter un joueur (oui/non)");
			String ajout = sc.nextLine();

			if(ajout.toUpperCase().equals("OUI"))
			{
				System.out.println("Entrez le nom du joueur");
				String nomJoueur = sc.nextLine();
				Joueur joueur = new Joueur(nomJoueur, this.nbJoueurs);
				ajouterJoueur(joueur);
			}
			else
			{
				if(this.nbJoueurs < 1)
				{
					System.out.println("Vous devez être deux joueurs minimum pour lancer une partie.");
				}
				else
				{
					bol = false;
				}	
			}
		}	
	}
	
	public boolean estGagnee()
	{
		return true;
	}
	
	
	/**
	 *Methode permettant d'initialiser le jeu.
	 *-Melanger les cartes
	 *-Distribution des cartes
	 *-lancer la boucle de jeu 
	 */
	public void initialisationPartie(){
		
		this.pioche.melanger();
		this.pioche.distribuerCarte(instancePartie);
		
		lancerPartie();
		
	}
	
	
	/**
	 * Methode permettant de lancer la boucle de jeu.
	 */
	public void lancerPartie(){
		
		boolean estDanish = false;
		boolean estGagnee = false;
		
		while(!estGagnee)
		{
			//tant que tout le monde peu jouer ..
			while(!(estDanish && estGagnee))
			{
				Joueur joueur = this.listeJoueurs.get(joueurCourant); //récupération du joueur courant
				//Si le joueur peu jouer alors on passe au suivant
				if(joueur.peuJouer())
				{
					System.out.println(joueur.getMainJoueur().toString());
					// ! \\ 
					//à modifier car le joueur peu choisir de jouer plusieurs cartes 
					
					for (int i = 0; i < choisirCarteAJouer().length; i++) {
						
					}
					this.tapis.ajouterCarte(joueur.jouer());
					joueurCourant++;
					
					//Si le joueur à posé et qu'il n'a plus de carte il à gagné !
					if(joueur.estGagnant())
					{
						estGagnee = true;
					}
				}
				else //Sinon on donne la main au dernier à avoir poser et on fini la manche.
				{
					joueur.getMainJoueur().getListeCartes().addAll(this.tapis.getListeCartes());//Je donne le tapis au joueur qui n'a pas pu jouer.
					this.tapis=null;
					joueurCourant--;
					estDanish = true;
				}
			}
		}	
	}
	
	/**
	 * Permet de faire piocher un joueur
	 * @param nbCartes nombre de carte à faire piocher
	 * @param joueur joueur qui doit piocher
	 */
	public void piocher(int nbCartes, Joueur joueur){
		LinkedList<Carte> liste = new LinkedList<Carte>();
		
		for (int i = 0; i < nbCartes; i++) {
			liste.add(this.pioche.prendreCarteDuDessus());
		}
		
		joueur.getMainJoueur().getListeCartes().addAll(liste);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Nouvelle partie de Bataille Norvegienne.");		
		Partie partie = getInstancePartie();
		partie.interfaceAjouterJoueur();
		
		partie.initialisationPartie();
		partie.lancerPartie();		
	}
	
	
}
