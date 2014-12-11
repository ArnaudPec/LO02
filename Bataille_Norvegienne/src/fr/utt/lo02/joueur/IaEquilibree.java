package fr.utt.lo02.joueur;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.lo02.carte.Carte;

public class IaEquilibree extends IA {

	/**
	 * @see Joueur
	 * @param nomJoueur
	 * @param numJoueur
	 */
	public IaEquilibree(String nomJoueur, int numJoueur) {
		super(nomJoueur, numJoueur);
		// TODO Auto-generated constructor stub
	}


		/**
		 * Méthode permettant au joueur IaEquilibree de choisir les cartes à jouer.
		 * Ici, on ne privilégie pas la pose de cartes spéciales et on n'a pas recours
		 * à une logique aléatoire. On choisit de poser à chaque fois les cartes de valeurs les plus
		 * faibles possibles. 
		 * 
		 * Cette stratégie augmente le nombre maximum de cartes posées par tour.
		 * 
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
			if (nombreMaxCarteJouable > 3)	nombreMaxCarteJouable = 3;

			int nb = rand.nextInt(nombreMaxCarteJouable )+1;
		
			listeCartes = new Carte[nb];

			numCarte = rand.nextInt(this.mainJoueur.getListeCartes().size()/* + 0*/);

			while (numCarte > this.mainJoueur.getListeCartes().size() || (this.mainJoueur.calculerNbOccurenceMemeValeur(this.mainJoueur.getCarte(numCarte).getValeur()) < nombreMaxCarteJouable)) {
				numCarte = rand.nextInt(this.mainJoueur.getListeCartes().size() /*+ 0*/);
			}
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
		 * Méthode permettant au joueur d'échanger ses cartes en début de partie.
		 * Ici on privilégie au maximum la diversité des cartes : pas deux cartes de 
		 * même valeur. De cette manière on s'adapte à la stratégie déployée dans la méthode 
		 * de choix des cartes.
		 * 
		 * @see #choisirCarteAJouer(Carte)
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
		 * Méthode permettant de choisir un joueur dans le cas de la pose d'un as. Il ne doit pas se désigner lui même.
		 * L'IA offensive désigne le joueur ayant le moins de cartes en main.
		 */
		public int choisirUnJoueur(ArrayList<Joueur> liste) {
			int nbcarte = 1000;
			int num = 0;
			
			for (Iterator<Joueur> iterator = liste.iterator(); iterator.hasNext();) {
				Joueur joueur = (Joueur) iterator.next();
					if(joueur.calculerNombreTotalCarte()<nbcarte && joueur.getNumJoueur()!=this.numJoueur){
						num = joueur.getNumJoueur();
						nbcarte=joueur.calculerNombreTotalCarte();
						
					}
			}

			System.out.println(this.nom + " a choisi : " + liste.get(num).getNom());

			return num;
		}

	


}
