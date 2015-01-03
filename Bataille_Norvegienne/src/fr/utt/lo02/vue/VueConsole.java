package fr.utt.lo02.vue;

import java.util.Scanner;

import fr.utt.lo02.joueur.Humain;
import fr.utt.lo02.joueur.IaAleatoire;
import fr.utt.lo02.joueur.IaEquilibree;
import fr.utt.lo02.joueur.IaOffensive;
import fr.utt.lo02.partie.Partie;

public class VueConsole {

	private Partie partie;
	
	
	public VueConsole() {
		this.partie = Partie.getInstancePartie();
		//this.partie.initialisationPartie(this.partie);
		this.interfaceAjouterJoueur();
		this.partie.getPioche().melanger();
		this.partie.getPioche().distribuerCarte(this.partie);
		this.partie.lancerPartie();
	}
	
	/**
	 * Méthode permettant de gérer le mécanisme d'ajout des joueurs en début de partie
	 */
	public void interfaceAjouterJoueur() {
		
		int nbJoueursHumain = 0;
		int nbJoueursIA = 0;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);

		do{
			System.out.println("Combien de joueur humain souhaitez vous ajouter ? (0-11 joueurs)");
			nbJoueursHumain = scanner.nextInt();
		}while(nbJoueursHumain>11 || nbJoueursHumain <0);
			
		System.out.println("Souhaitez vous ajouter des IA ?(Oui/Non)");
		String reponse = scanner2.nextLine();
		
		if (reponse.toUpperCase().equals("OUI") && nbJoueursHumain<11){
			do{
				System.out.println("Combien de joueur IA souhaitez vous ajouter ? (1-" + (11-nbJoueursHumain) + ")");
				nbJoueursIA= scanner.nextInt();
			}while(nbJoueursHumain + nbJoueursIA >11);
			if(nbJoueursIA==1 && nbJoueursHumain==0){
				System.out.println("Vous ne pouvez pas lancer de partie avec une seule IA, une IA a ete ajoutee !");
				nbJoueursIA ++;
			}
		} 
		else if(reponse.toUpperCase().equals("OUI")){
			System.out.println("Nombre maximum de joueurs atteint. Impossible d'ajouter des IAs");	
		}
		else if (nbJoueursHumain ==1) {
			System.out.println("Vous ne pouvez pas lancer de partie seul, une IA a ete ajoutee !");
			nbJoueursIA = 1;
		}
		else if(nbJoueursIA==0 && nbJoueursHumain==0){
			System.out.println("Vous ne pouvez pas lancer de partie sans joueurs, un joueur humain et une IA ont ete ajoutees !");
			nbJoueursIA = 1;
			nbJoueursHumain =1;
		}	
		this.creationHumain(nbJoueursHumain);
		this.creationIA(nbJoueursIA);
		
		if(this.partie.getNbJoueurs()>5)this.partie.getPioche().ajouterUnSecondJeuDeCarte();
	}
	
	public void creationHumain(int nbJoueur) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < nbJoueur; i++) {
			System.out.println("\nEntrez le nom du joueur");
			String nomJoueur = sc.nextLine();
			Humain joueur = new Humain(nomJoueur, this.partie.getNbJoueurs());
			this.partie.ajouterJoueur(joueur);
		}
		
	}
	
	

	/**Méthode permettant de créer les joueurs gérés par l'ordinateur
	 * @param nbJoueur à créer
	 */
	public void creationIA(int nbJoueur) {
		
		Scanner scanner = new Scanner(System.in);
		int strategie;
		boolean joueurOf=false;
		
		for (int i = 0; i < nbJoueur; i++) {
			
			System.out.println("\nIA numero "+i +" : Quelle stratégie souahitez vous affronter ? \n0 : Aleatoire | 1 : Offensive (MAX 1)| 2 : Equilibree");
			strategie = scanner.nextInt();
			
			while(strategie<0 || strategie > 2){
				System.out.println("Erreur, recommencez :");
				strategie = scanner.nextInt();
			}
			
			joueurOf=this.partie.verifierPresenceIaOffensive();
			
			switch (strategie) {
			case 0:
				IaAleatoire iaAleatoire = new IaAleatoire("ia_" + i +"_Aleatoire",this.partie.getNbJoueurs());
				this.partie.ajouterJoueur(iaAleatoire);
				break;
				
			case 1:
				if(!joueurOf){
				IaOffensive  IaOffensive = new IaOffensive("ia_" + i +"_Offensive",this.partie.getNbJoueurs());
				this.partie.ajouterJoueur(IaOffensive);}
				else {
					System.out.println("Nombre max d'IA offensive atteint, creation d'une IA aleatoire");
					this.partie.ajouterJoueur(new IaAleatoire("ia_" + i +"_Aleatoire",this.partie.getNbJoueurs()));
				}
				break;
				
			case 2:
				IaEquilibree  IaEquilibree = new IaEquilibree("ia_" + i +"_Equilibree",this.partie.getNbJoueurs());
				this.partie.ajouterJoueur(IaEquilibree);
				break;

			default:
				break;
			}
		}
	}

	
}
