package fr.utt.lo02.partie;

import java.util.LinkedList;
import java.util.ListIterator;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.Tapis;
import fr.utt.lo02.joueur.Joueur;

public class ActionSpeciale {

	private Tapis tapis;
	private Joueur joueur;
	private Partie partie;

	public ActionSpeciale(Partie partie, int JoueurCourant) {
		this.partie = partie;
		this.tapis = partie.getTapis();
		this.joueur = partie.getJoueur(JoueurCourant);
	}

	public void appelerBonneMethode() {
		switch (this.tapis.carteDuDessus().getValeur()) {
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

	
	private void effectuerActionA() {

		if (this.partie.getJoueurSuivant().peutJouer(this.tapis.carteDuDessus())==false) {
			int choixDuJoueur = this.joueur.choisirUnJoueur(partie.getListeJoueurs());
			LinkedList<Carte> tapis = this.tapis.prendreTapis();
			Joueur joueur = this.partie.getListeJoueurs().get(choixDuJoueur);

			joueur.getMainJoueur().ajouterPlusieursCartes(tapis);
		}
	}

	private void effectuerAction10() {
		this.partie.getTapis().viderTas();
	}

	private void effectuerAction8() {

		ListIterator<Carte> iterator = this.tapis.getListeCartes().listIterator(this.tapis.getListeCartes().size()); 
		int i = 0;
		while (iterator.hasPrevious() && i < 3) {
			Carte carte = iterator.previous();
			if (carte.getValeur() == 8) {
				this.partie.gestionDuJoueurCourant();
				System.out.println(joueur.getNom() +" passe son tour !");
			}			
			i++;
		}	
	}

}
