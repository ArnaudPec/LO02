package fr.utt.lo02.joueur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import fr.utt.lo02.carte.*;

/**
 * @see Joueur
 * 
 */
public class IaAleatoire extends IA {

	public IaAleatoire(String nomJoueur, int numJoueur) {
		super(nomJoueur, numJoueur);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Méthode permettant au joueur IaAleatoire de choisir les cartes à jouer.
	 * Ici tout est choisi aléatoirement. On utilise l'objet random.
	 * 
	 * @return une liste de carte
	 * 
	 * @see Random
	 * 
	 * @see MainJoueur#calculerNbMaxCarteMemeValeur()
	 * @see MainJoueur#calculerNbOccurenceMemeValeur(Carte)
	 * @see MainJoueur#calculerPositionCarteValeur(int)
	 * 
	 */
	public Carte[] choisirCarteAJouer(Carte derniereCarte) {

		Random rand = new Random();
		Carte[] listeCartes;
		int numCarte = 0;
		int nombreMaxCarteJouable;
		
		this.mainJoueur.trierCartesJouables(derniereCarte); // on restreint le choix aux cartes jouables
		System.out.println(this.mainJoueur);

		nombreMaxCarteJouable = this.mainJoueur.calculerNbMaxCarteMemeValeur();
		if (nombreMaxCarteJouable > 3)
			nombreMaxCarteJouable = 3;

		int nb = rand.nextInt(nombreMaxCarteJouable + 1);
		while (nb == 0) {
			nb = rand.nextInt(nombreMaxCarteJouable + 1);
		}

		listeCartes = new Carte[nb];

		numCarte = rand.nextInt(this.mainJoueur.getListeCartes().size() + 0);

		while (numCarte > this.mainJoueur.getListeCartes().size()
				|| (this.mainJoueur
						.calculerNbOccurenceMemeValeur(this.mainJoueur
								.getCarte(numCarte)) != 
								nombreMaxCarteJouable)) {
			numCarte = rand.nextInt(this.mainJoueur.getListeCartes().size() + 0);
		}
		int valeur = this.mainJoueur.getCarte(numCarte).getValeur();
		for (int i = 0; i < listeCartes.length; i++) {
			listeCartes[i] = this.mainJoueur.prendreCarte(this.mainJoueur
					.calculerPositionCarteValeur(valeur));
		}

		System.out.println("Choix : \n");
		for (int i = 0; i < listeCartes.length; i++) {
			System.out.println(listeCartes[i]);
		}
		
		this.mainJoueur.fusionner();


		return listeCartes;
	}

	/**
	 * M��thode permettant au joueur humain d'��changer ses cartes en d��but de
	 * partie.
	 * 
	 * @see Random
	 * 
	 * @see MainJoueur#ajouterCarte(Carte)
	 * @see MainJoueur#prendreCarte(int)
	 * 
	 * @see TasVisible#ajouterCarte(Carte)
	 * @see TasVisible#prendreCarte(int)
	 */
	public void changerCartes() {

		int numCarteMain, numCarteVis;
		int nbChangement = 3;
		Random rand = new Random();

		boolean changer = rand.nextBoolean();
		System.out.println(changer);
		while (changer && nbChangement > 0) {

			numCarteMain = rand.nextInt(3);
			numCarteVis = rand.nextInt(3);

			this.mainJoueur.ajouterCarte(this.tasVisible
					.prendreCarte(numCarteVis));
			this.tasVisible.ajouterCarte(this.mainJoueur
					.prendreCarte(numCarteMain));
			nbChangement--;
			changer = rand.nextBoolean();
		}

	}
	/** 
	 * Méthode permettant de choisir un joueur dans le cas de la pose d'un as. L'IA aléatoire désigne aléatoirement 
	 * un joueur qui va prendre le tas. Il ne doit pas se désigner lui même
	 */	
	public int interfaceDemandeChoisirUnJoueur(ArrayList<Joueur> liste){
			
			int num;
			Random rand = new Random();
			do{
				num = rand.nextInt(liste.size());
			}while(num == this.numJoueur || num < 0);
			
			System.out.println(this.nom + " a choisi : " +liste.get(num).getNom());
			
			return num ;
		}

}
