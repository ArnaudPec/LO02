package fr.utt.lo02.partie;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.Tapis;
import fr.utt.lo02.joueur.Joueur;

public class ActionSpeciale {

	private Tapis tapis;
	private Joueur joueur;
	private Partie partie;
		
	public ActionSpeciale(Partie partie, int JoueurCourant)
	{
		this.partie = partie;
		this.tapis = partie.getTapis();
		this.joueur = partie.getJoueur(JoueurCourant);
	}
	
	
	public void appelerBonneMethode(Carte carte)
	{
		switch (carte.getValeur()) {
		case 8:
			effectuerAction8();
			break;
		case 10:
			effectuerAction10();
			break;
		case 14:
			//effectuerActionA();
			break;
		}
	}
	
//	private void effectuerActionA() {
//		
//		if(!this.partie.getJoueurSuivant().peutJouer(this.tapis.carteDuDessus()))
//		{
//			int choixDuJoueur = this.joueur.interfaceDemandeChoisirUnJoueur();
//			LinkedList<Carte> tapis = this.tapis.getListeCartes();
//			Joueur joueur = this.partie.getListeJoueurs().get(choixDuJoueur);
//			
//			joueur.getMainJoueur().ajouterPlusieursCartes(tapis);
//		}
//	}


	private void effectuerAction10() {
		this.partie.getTapis().viderTapis();		
	}
	
	private void effectuerAction8() {		
		
		int nb8 = 0;
		ListIterator<Carte> iterator = this.tapis.getListeCartes().listIterator(this.tapis.getListeCartes().size()); // On précise la position initiale de l'iterator. Ici on le place à la fin de la liste
		int i=0;
		while(iterator.hasPrevious() && i < 3){
		   Carte carte = iterator.previous();
		   if( carte.getValeur() == 8)
		   {
			   nb8++;
		   }
		   i++;
		}		
		this.partie.setJoueurCourant(this.partie.getJoueurCourant()+nb8+1);
	}
	
	
}
