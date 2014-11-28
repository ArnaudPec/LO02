package fr.utt.lo02.joueur;

import java.util.*;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.TasCache;
import fr.utt.lo02.carte.TasVisible;

public class Humain extends Joueur {

	public Humain(String nomJoueur, int numJoueur) {
		super(nomJoueur, numJoueur);
	}


	/** 
	 * Méthode permettant de choisir les cartes à jouer. ATTENTION,  ne gère pas encore les choix du joueur dans le cas où celui ci voudrait jouer 3 cartes alors qu'il n'en a que deux de même valeur
	 * @return Une liste de carte
	 * 
	 */
		
	/*public HashSet<Carte> choisirCarteAJouer(){
			
			HashSet<Carte> listeCartes ;
			Scanner scanner = new Scanner(System.in);
			int numCarte;
			int valCarte;
			boolean cartesMemeValeur;
			boolean memeCartes;
			
			System.out.println("Choisissez le nombre de carte à jouer");
			int nb = scanner.nextInt();
			
			while (nb>3) {

				System.out.println("Erreur : Choisissez un nombre inférieur ou égal à 3");
				nb = scanner.nextInt();
				
			}
			
			System.out.println(this.mainJoueur+ "\n");
			
			do 
			{
				listeCartes = new HashSet<Carte>();
				cartesMemeValeur = true;
				memeCartes=false;
				
				for (int i = 0; i < nb; i++) {
					System.out.println("\nChoisissez la carte " +i + ": \n");
					numCarte=scanner.nextInt();
					listeCartes.add(this.mainJoueur.getCarte(numCarte));
				}
				
				if(listeCartes.size()!=nb) memeCartes = true;
				
				java.util.Iterator<Carte> it = listeCartes.iterator();
				
				Carte premiereCarte = (Carte) it.next();
				
				while (it.hasNext()) {
					Carte carte = (Carte) it.next();
					if(carte.getValeur()!=premiereCarte.getValeur()) cartesMemeValeur = false;
				}
				
				System.out.println(memeCartes);			
				
			} while (!cartesMemeValeur && memeCartes);
			
			
			System.out.println("ok");
			
			return listeCartes;
			
		}

	}*/

	

	/** 
	 * Méthode permettant au joueur humain de choisir les cartes à jouer. ATTENTION,  ne gère pas encore tous les cas de figure. Il faut encore implémenter 
	 * des contrôles : 
	 * 1 - ajouter une condition au choix du nombre de carte à jouer 
	 * 		pour cela il faut au préalable vérifier le nombre max de carte de même valeur de sa main ( ex : 8coeur 8carreau Aspic RoiTrefle  =  2)
	 * 		et interdir un choix supérieur à ce nombre --> NORMALEMENT OK 
	 * 
	 * 2 - A la sélection de la première carte d'une série de plusieurs carte, il faut implémenter une vérification de la main (ex: l'humain souhaite jouer deux cartes,
	 * 		selon l'exemple du 1 - il ne peut que choisir une carte de valeur 8, s'il demande l'as il faut lui redemander de choisir avant de prendreCarte(l'as en question)
	 * 		---> NORMALEMENT OK
	 */
	public Carte[] choisirCarteAJouer(){
		
		Scanner scanner = new Scanner(System.in);
	
		Carte[] listeCartes;
		int numCarte=0;
		int nombreMaxCarteJouable;
		//boolean error =false; 
		boolean error = true;
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
		System.out.println("Choix correct");
		int valeur = this.mainJoueur.getCarte(numCarte).getValeur();
		for (int i = 0; i < listeCartes.length; i++) {
			listeCartes[i]=this.mainJoueur.prendreCarte(this.mainJoueur.calculerPositionCarteValeur(valeur));
		}
		
		
//		for (int i = 0; i < nb; i++) {
//			
//			error =true;
//			
//			while(error){
//				error=false;
//				System.out.println(this.mainJoueur);
//				System.out.println("\nChoisissez la carte " +i + ": \n");
//				numCarte=scanner.nextInt();
//				while(numCarte>3){
//					System.out.println("\nIl n'y a que quatre cartes ! Choississez la carte "  +i + ": \n");
//					numCarte=scanner.nextInt();
//				}	
//				for(int j=0; j<i; j++){
//					if(mainJoueur.getCarte(numCarte).equals(listeCartes[j])) error=true;
//					if(mainJoueur.getCarte(numCarte).getValeur()!=listeCartes[j].getValeur()) error =true;
//				}
//			}
//			
//			listeCartes[i]=this.mainJoueur.prendreCarte(numCarte);	
//		}
		
		System.out.println("Choix : \n");
		for (int i = 0; i < listeCartes.length; i++) {
			System.out.println(listeCartes[i]);
			
		}

		return listeCartes;
	}
	
	
	/** 
	 * Méthode permettant au joueur humain d'échanger ces cartes en début de partie
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
