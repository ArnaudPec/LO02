package fr.utt.lo02.partie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.Pioche;
import fr.utt.lo02.carte.Tapis;
import fr.utt.lo02.joueur.Humain;
import fr.utt.lo02.joueur.IaAleatoire;
import fr.utt.lo02.joueur.IaOffensive;
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
		this.pioche = Pioche.getInstancePioche();
		this.tapis = Tapis.getInstanceTapis();
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
		boolean conditionIA = false;
		boolean conditionJ = false;
		
		int nbJoueursHumain=0;
		int nbJoueursIA=0;
		
		int niveau=0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Combien de joueur humain voulez vous ajouter");
		String demandeNbJoueurs = sc.nextLine();
		
		nbJoueursHumain = Integer.parseInt(demandeNbJoueurs);
		
		
		do {
			if(nbJoueursHumain > 0 && nbJoueursHumain <= 11 )
			{
				conditionJ = true;
			}
		} while (!conditionJ);
			

		System.out.println("Voulez vous ajouter des IA ?(Oui/Non)");
		String demandeIA = sc.nextLine();
		
		if("OUI".equals(demandeIA.toUpperCase())){
			
			System.out.println("Combien d'IA voulez vous ajouter ?");
			String demandeNbIA = sc.nextLine();
			
			nbJoueursIA= Integer.parseInt(demandeNbIA);
			
			do {
				if(nbJoueursHumain+nbJoueursIA > 0 && nbJoueursHumain+nbJoueursIA <= 11 )
				{
					conditionJ = true;
				}
			} while (!conditionIA);
			
			System.out.println("Quel niveau d'IA désirez vous (0=aléatoire, 1=offensive)");
			String niveauIA = sc.nextLine();
			niveau = Integer.parseInt(niveauIA);
		}
		else{
			if(nbJoueursHumain<2)
			{
				System.out.println("Vous ne pouvez pas lancer de partie seul, une IA a été crée ");
				nbJoueursIA=1;
				niveau=0;
			}
		}
		sc.close();
		sc.reset();
		creationJoueur(nbJoueursHumain);
		creationIA(nbJoueursIA, niveau);
		
	}
	
	
	public void creationJoueur(int nbJoueur)
	{
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < nbJoueur ; i++) {
			System.out.println("Entrez le nom du joueur");
			String nomJoueur = sc.nextLine();
			Humain joueur = new Humain(nomJoueur, this.nbJoueurs);
			ajouterJoueur(joueur);
		}
		
		sc.close();
		sc.reset();
		
	}
	
	public void creationIA(int nbIA, int niveau){
		
		for (int i = 0; i < nbIA ; i++) {
			if(niveau == 0)
			{
				IaAleatoire iaAleatoire = new IaAleatoire("ia"+i, this.nbJoueurs);
				ajouterJoueur(iaAleatoire);
				this.nbJoueurs++;
			}
			if(niveau == 1)
			{
				IaOffensive iaOfensive = new IaOffensive("ia"+i, this.nbJoueurs);
				ajouterJoueur(iaOfensive);
				this.nbJoueurs++;
			}

		}
		
	}
	
	
	/**
	 * Méthode qui retourne si la partie est gagnée en vérifiant que le joueur courant a gagné ou non.
	 * @return [true=la partie est gagnée, false=la partie continue]
	 */
	public boolean estGagnee()
	{
		return this.listeJoueurs.get(joueurCourant).estGagnant();
	}
	
	
	/**
	 *Methode permettant d'initialiser le jeu.
	 *-Melanger les cartes
	 *-Distribution des cartes
	 *-lancer la boucle de jeu 
	 */
	public void initialisationPartie(Partie partie){
		
		this.interfaceAjouterJoueur();
		this.pioche.melanger();
		this.pioche.distribuerCarte(partie);	
	}
	
	/**
	 * Méthode permettant de gérer le joueur courant au sein de la boucle de jeu.
	 * Incrémente le joueur s'il y'a un joueur après lui sinon repasse au premier joueur.
	 */
	public void gestionDuJoueurCourant()
	{
		if(this.joueurCourant == this.nbJoueurs-1){
			this.joueurCourant=0;
		}else{
			joueurCourant++;	
		}
	}
	
	
	public void faireJouerJoueur(Joueur joueur){
		
		System.out.println("C'est " +joueur.getNom()+ " qui joue ! \n");
		
		System.out.println(this.getTapis());
		
		Carte[] carteJouees = joueur.choisirCarteAJouer(this.getTapis().carteDuDessus());
		this.getTapis().ajouterPlusieursCartes(carteJouees);	
		
		this.fairePiocherJoueur(joueur, carteJouees.length);

	}
	
	public void fairePiocherJoueur(Joueur joueur, int nbCarte){
		
		int nbmax;
		
		if(!this.pioche.getListeCartes().isEmpty()){
			if(this.pioche.getListeCartes().size()>nbCarte) nbmax = nbCarte;
			else nbmax = this.pioche.getListeCartes().size();
			
			for (int i = 0; i < nbmax; i++) {
				joueur.getMainJoueur().ajouterCarte(this.pioche.prendreCarteDuDessus());
			}
			System.out.println(nbmax +" cartes a(ont) été piochée(s) par "+joueur.getNom() +"\n");

		}
		
		else if(this.pioche.getListeCartes().isEmpty() && joueur.getMainJoueur().getListeCartes().isEmpty())
		{
			joueur.getMainJoueur().ajouterPlusieursCartes(joueur.getTasVisible().getListeCartes());
		}
		
		else if(this.pioche.getListeCartes().isEmpty() && joueur.getTasVisible().getListeCartes().isEmpty() &&
				joueur.getMainJoueur().getListeCartes().isEmpty())
		{
			joueur.getMainJoueur().ajouterCarte(joueur.getTasCache().prendreCarte());
		}
		
		
	}
	
	
	/**
	 * Methode permettant de lancer la boucle de jeu.
	 */
	public void lancerPartie(){
		
		System.out.println("La partie démarre\n");
		boolean estDanish = false;
		boolean estGagnee = false;
		
		while(!estGagnee)
		{
			//tant que tout le monde peu jouer ..
			while(!(estDanish && estGagnee))
			{
				Joueur joueur = this.listeJoueurs.get(joueurCourant); //récupération du joueur courant
				//Si le joueur peu jouer alors on passe au suivant
				if(joueur.peutJouer(this.getTapis().carteDuDessus()))
				{					
					//Permet de choisir les cartes que le joueur veut jouer
					this.faireJouerJoueur(joueur);
					
					
					//Gestion du joueur courant
									
				}
				else //Sinon on donne la main au dernier à avoir poser et on fini la manche.
				{
					System.out.println("Ahah " +joueur.getNom() + " tu ne peux pas jouer mécréant, prend toi le tapis dans la face ! \n\n");
					joueur.getMainJoueur().getListeCartes().addAll(this.tapis.prendreTapis());//Je donne le tapis au joueur qui n'a pas pu jouer.
					//this.tapis=null;
					//joueurCourant--;
					//estDanish = true;
				}
				this.gestionDuJoueurCourant();

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
		partie.initialisationPartie(partie);
		partie.lancerPartie();
		//System.out.println(partie.getJoueur(0).getMainJoueur());
	
	}
	
	
}
