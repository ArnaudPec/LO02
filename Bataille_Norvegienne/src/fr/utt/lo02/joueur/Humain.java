package fr.utt.lo02.joueur;

import java.util.*;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.TasVisible;


/**
 * @see Joueur
 *
 */
public class Humain extends Joueur {

	public Humain(String nomJoueur, int numJoueur) {
		super(nomJoueur, numJoueur);
	}


	/**
	 * Méthode permettant au joueur humain de choisir les cartes à jouer. 
	 * Il choisit le nombre de carte à jouer et la ou les cartes en questions.
	 * La cohérence des choix est vérifiée.
	 * 
	 * @return une liste de carte
	 * 
	 * @see MainJoueur#calculerNbMaxCarteMemeValeur()
	 * @see MainJoueur#calculerNbOccurenceMemeValeur(Carte)
	 * @see MainJoueur#calculerPositionCarteValeur(int)
	 * 
	 */
	public Carte[] choisirCarteAJouer(Carte derniereCarte) {

		System.out.println(this.mainJoueur);
		
		Scanner scanner = new Scanner(System.in);
		Carte[] listeCartes;
		int numCarte = 0;
		int nombreMaxCarteJouable;
		this.mainJoueur.trierCartesJouables(derniereCarte); // on restreint le choix aux cartes jouables

		nombreMaxCarteJouable = this.mainJoueur.calculerNbMaxCarteMemeValeur();
		if (nombreMaxCarteJouable > 3) nombreMaxCarteJouable = 3;

		System.out.println(this.mainJoueur + "Choisissez le nombre de carte à jouer");

		int nb = scanner.nextInt();
		
		while (nb > nombreMaxCarteJouable || nb<1) {
			System.out.println("Erreur : Vous ne pouvez jouer au maximum que " + nombreMaxCarteJouable + " carte(s)\nChoisissez un nombre inférieur ou égal à " + nombreMaxCarteJouable);
			nb = scanner.nextInt();
		}

		listeCartes = new Carte[nb];

		System.out.println(this.mainJoueur +"\nChoisissez la(les) carte(s) à jouer (pour un groupe, un seul choix) : \n");
		numCarte = scanner.nextInt();
		
		while (numCarte > this.mainJoueur.getListeCartes().size()-1/*|| (this.mainJoueur.calculerNbOccurenceMemeValeur(this.mainJoueur.getCarte(numCarte)) != nombreMaxCarteJouable)*/) {
			System.out.println("Choix incorrect, vous aviez choisi de jouer "+ nb + " carte(s). Recommencez : \n" + this.mainJoueur);
			numCarte = scanner.nextInt();
		}
		
		System.out.println("Choix correct\n");
		
		scanner.reset();
		
		int valeur = this.mainJoueur.getCarte(numCarte).getValeur();
		for (int i = 0; i < listeCartes.length; i++) {
			listeCartes[i] = this.mainJoueur.prendreCarte(this.mainJoueur.calculerPositionCarteValeur(valeur));
		}

		System.out.println("Choix : \n");
		for (int i = 0; i < listeCartes.length; i++) {
			System.out.println(listeCartes[i]);
		}

		this.mainJoueur.fusionner();
		
		return listeCartes;
	}

	/**
	 * Méthode permettant au joueur humain d'échanger ses cartes en début de partie
	 * 
	 * @see MainJoueur#ajouterCarte(Carte)
	 * @see MainJoueur#prendreCarte(int)
	 * 
	 * @see TasVisible#ajouterCarte(Carte)
	 * @see TasVisible#prendreCarte(int)
	 * 
	 */
	public void changerCartes() {

		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		int numCarteMain, numCarteVis;

		System.out.println(this.mainJoueur);
		System.out.println(this.tasVisible);

		System.out.println("Souhaitez vous echanger une carte ? O/n \n");

		String chaine = scanner.nextLine();
		char c = chaine.charAt(0);

		while (c != 'n') {
			System.out.println(this.mainJoueur);
			System.out.println(this.tasVisible);

			System.out.println("Numéro de la carte à échanger (main) :");
			numCarteMain = scanner2.nextInt();

			while (numCarteMain > 3) {
				System.out
						.println("Erreur. Numéro de la carte à échanger (main) :");
				numCarteMain = scanner2.nextInt();
			}
			System.out
					.println("Numéro de la carte à échanger (cartes visibles) :");
			numCarteVis = scanner2.nextInt();

			while (numCarteVis > 2) {
				System.out
						.println("Erreur. Numéro de la carte à échanger (main) :");
				numCarteVis = scanner2.nextInt();
			}

			this.mainJoueur.ajouterCarte(this.tasVisible
					.prendreCarte(numCarteVis));
			this.tasVisible.ajouterCarte(this.mainJoueur
					.prendreCarte(numCarteMain));

			System.out.println(this.mainJoueur);
			System.out.println(this.tasVisible);

			System.out
					.println("Souhaitez vous echanger une autre carte ? O/n \n");
			chaine = scanner.nextLine();
			c = chaine.charAt(0);
		}
		scanner.close();
		scanner2.close();
	}
	
	/** Méthode permettant au joueur humain de désigner un autre joueur de la partie
	 * @see fr.utt.lo02.joueur.Joueur#choisirUnJoueur(java.util.ArrayList)
	 */
	public int choisirUnJoueur(ArrayList<Joueur> liste){
		
		int num;
		
		Scanner scanner = new Scanner(System.in);
		do{
			
			System.out.println(this.nom + " ,choisissez un autre joueur : \n");
			
			for (Iterator<Joueur> iterator = liste.iterator(); iterator.hasNext();) {
				Joueur joueur = (Joueur) iterator.next();
				System.out.println(joueur.getNumJoueur()+ " : " +joueur.getNom() + ", Main : " + joueur.getMainJoueur().getListeCartes().size() );
				
			}
			
			num = scanner.nextInt();
			
		}while(num>liste.size()-1 || num == this.numJoueur || num < 0);
		
		System.out.println("Vous avez choisi : " +liste.get(num).getNom());
		
		return num ;
	}


	public Carte[] choisirCarteAJouer(ArrayList<Carte> listeCartesSelectionnees) {

		int nbCarte = listeCartesSelectionnees.size();
		Carte[] listeCartes = new Carte[nbCarte];
		Carte carte = listeCartesSelectionnees.get(0);
		
		for (int i = 0; i < listeCartes.length; i++) {
			listeCartes[i] = this.mainJoueur.prendreCarte(this.mainJoueur.calculerPositionCarteValeur(carte.getValeur()));
		}
		
		return listeCartes;
		
	}

}
