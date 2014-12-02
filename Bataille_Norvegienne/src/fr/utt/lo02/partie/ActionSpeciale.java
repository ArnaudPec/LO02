package fr.utt.lo02.partie;

import java.util.LinkedList;
import java.util.Scanner;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.Tapis;
import fr.utt.lo02.joueur.Joueur;

public class ActionSpeciale {

	private Tapis tapis;
	private Joueur joueur;
	private Partie partie;
	private LinkedList<Carte> carteDuTourPrecedent;
	private LinkedList<Carte> carteDuTourCourant;
	
	
	public ActionSpeciale(Partie partie, int JoueurCourant)
	{
		this.partie = partie;
		this.tapis = partie.getTapis();
		this.joueur = partie.getJoueur(JoueurCourant);
	}
	
	
	public void appelerBonneMethode(Carte carte)
	{
		switch (carte.getValeur()) {
		case 2:
			effectuerAction2();
			break;
		case 7:
			effectuerAction7();
			break;
		case 8:
			effectuerAction8();
			break;
		case 10:
			effectuerAction10();
			break;
		case 14:
			effectuerActionA();
			break;
		}
	}

	public void changerTasCourantEnTasPrecedent(){
		this.carteDuTourPrecedent = this.carteDuTourCourant;
		this.carteDuTourCourant=null;
	}

	private void effectuerActionA() {
		
		int choixDuJoueur = interfaceDemandeChoisirUnJoueur();
		LinkedList<Carte> tapis = this.tapis.getListeCartes();
		Joueur joueur = this.partie.getListeJoueurs().get(choixDuJoueur);
		
		joueur.getMainJoueur().ajouterPlusieursCartes(tapis);
		
	}


	private void effectuerAction10() {
		this.partie.getTapis().viderTapis();		
	}


	private void effectuerAction8() {		
		this.partie.setJoueurCourant(this.partie.getJoueurCourant()+2);
	}


	private void effectuerAction7() {
		 
		//Pas de meiller idée que de faire repartir la boucle de jeu ici pour le prochain joueur et contrôlé ce qu'il joue.
		
	}


	public int interfaceDemandeChoisirUnJoueur(){
		
		Scanner sc = new Scanner(System.in);
		boolean condition=false;		
		int resultat = -1;
		
		do
		{
			String joueur = sc.nextLine();		
			int numJoueur = Integer.parseInt(joueur);
			
			if(numJoueur>=0 && numJoueur < this.partie.getNbJoueurs() && numJoueur != this.partie.getJoueurCourant())
			{
				resultat = numJoueur;
				condition = true;
			}
		}while(!condition);
		
		return resultat;
	}
	
	private void effectuerAction2() {
		// TODO Auto-generated method stub
		
	}
	
}
