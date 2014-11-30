package fr.utt.lo02.joueur;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.TasCache;
import fr.utt.lo02.carte.TasVisible;

public class IaOffensive extends IA {

	public IaOffensive(String nomJoueur, int numJoueur) {
		super(nomJoueur, numJoueur);
		// TODO Auto-generated constructor stub
	}
	

		/** 
		 * Méthode permettant au joueur IaAleatoire de choisir les cartes à jouer. 
		 * COMPORTEMENT EGAL AU JOUEUR HUMAIN POUR L'INSTANT
		 */
	
		public Carte[] choisirCarteAJouer(){
			
			Scanner scanner = new Scanner(System.in);
		
			Carte[] listeCartes;
			int numCarte=0;
			int nombreMaxCarteJouable;
			
			nombreMaxCarteJouable=this.mainJoueur.calculerNbMaxCarteMemeValeur();
			if(nombreMaxCarteJouable>3) nombreMaxCarteJouable = 3;
			
			System.out.println(this.mainJoueur);
			
			System.out.println("Choisissez le nombre de carte à jouer");
			
			int nb = scanner.nextInt();
			while (nb>nombreMaxCarteJouable) {
				System.out.println("Erreur : Vous ne pouvez jouer au maximum que "+nombreMaxCarteJouable+ " carte(s)\nChoisissez un nombre inférieur ou égal à " +nombreMaxCarteJouable);
				nb = scanner.nextInt();
			}
			
			listeCartes = new Carte[nb];
			
			System.out.println(this.mainJoueur);
			System.out.println("\nChoisissez la(les) carte(s) à jouer (pour un groupe, un seul choix) : \n");
			numCarte=scanner.nextInt();

			while(this.mainJoueur.calculerNbOccurenceMemeValeur(this.mainJoueur.getCarte(numCarte))!=nombreMaxCarteJouable){
				System.out.println("Choix incorrect, vous aviez choisi de jouer " +nb+ " carte(s). Recommencez : \n");
				System.out.println(this.mainJoueur);
				numCarte=scanner.nextInt();
				}
			System.out.println("Choix correct\n");
			int valeur = this.mainJoueur.getCarte(numCarte).getValeur();
			for (int i = 0; i < listeCartes.length; i++) {
				listeCartes[i]=this.mainJoueur.prendreCarte(this.mainJoueur.calculerPositionCarteValeur(valeur));
			}
			
			System.out.println("Choix : \n");
			for (int i = 0; i < listeCartes.length; i++) {
				System.out.println(listeCartes[i]);
				
			}

			return listeCartes;
		}
		
		
		/** 
		 * Méthode permettant au joueur humain d'échanger ses cartes en début de partie
		 */
		public void changerCartes(){
			
			Scanner scanner = new Scanner(System.in);
			Scanner scanner2 =new Scanner(System.in);
			int numCarteMain, numCarteVis;
			
			System.out.println(this.mainJoueur);
			System.out.println(this.tasVisible);
			
			System.out.println("Souhaitez vous echanger une carte ? O/n \n");
			
			String chaine=scanner.nextLine();
			char c = chaine.charAt(0);
			
			while(c!='n'){
				System.out.println(this.mainJoueur);
				System.out.println(this.tasVisible);
				
				System.out.println("Numéro de la carte à échanger (main) :");
				numCarteMain = scanner2.nextInt();
				
				System.out.println("Numéro de la carte à échanger (cartes visibles) :");
				numCarteVis = scanner2.nextInt();
				this.mainJoueur.ajouterCarte(this.tasVisible.prendreCarte(numCarteVis));
				this.tasVisible.ajouterCarte(this.mainJoueur.prendreCarte(numCarteMain));	
				
				System.out.println(this.mainJoueur);
				System.out.println(this.tasVisible);
				
				System.out.println("Souhaitez vous echanger une autre carte ? O/n \n");
				chaine=scanner.nextLine();
				c = chaine.charAt(0);
			}		
		}
		
	}




	


