package fr.utt.lo02.joueur;

import java.util.Random;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.TasVisible;


public class IaOffensive extends IA {

	public IaOffensive(String nomJoueur, int numJoueur) {
		super(nomJoueur, numJoueur);
	}
	
	/**
	 * Méthode permettant au joueur IaOffensif de choisir les cartes à jouer.
	 * L'Ia offensive privilégie la pose de cartes spéciales. Elle pose le maximum de cartes posables. 
	 * Pour l'instant même comportement que l'iaAléatoire
	 */

	public Carte[] choisirCarteAJouer() {

		Carte[] listeCartes;
		int as = 14, huit = 8;

		if(this.mainJoueur.contenirCarte(as)){ //on récupère le(s) as
			
			listeCartes = new Carte[this.mainJoueur.calculerNbOccurenceMemeValeur(as)]; //on initialise le tableau avec le nombre d'as présents
			for (int i = 0; i < listeCartes.length; i++) {
				listeCartes[i]=this.mainJoueur.prendreCarteValeur(as); //on prend tous les as trouvés
			}
		}
		
		else if (this.mainJoueur.contenirCarte(huit)){ // on pose le(s) huit(s)
			listeCartes = new Carte[this.mainJoueur.calculerNbOccurenceMemeValeur(huit)];
			for (int i = 0; i < listeCartes.length; i++) {
				listeCartes[i]=this.mainJoueur.prendreCarteValeur(huit);
			}
		}
		
		else if (this.mainJoueur.contenirCarteSpeciale()){ // on pose une carte spéciale
			Carte carte = this.mainJoueur.prendreCarteSpeciale();
			listeCartes=new Carte[this.mainJoueur.calculerNbOccurenceMemeValeur(carte)];
			listeCartes[0]=carte;
			for (int i = 1; i < listeCartes.length; i++) {
				listeCartes[i]=this.mainJoueur.prendreCarteValeur(carte.getValeur());
			}
		}
		
		else { //s'il n'y a pas de cartes spéciales on tire une carte au hasard
			Random rand =new Random();
			int valeur = this.mainJoueur.getCarte(rand.nextInt(3)).getValeur();
			listeCartes= new Carte[this.mainJoueur.calculerNbOccurenceMemeValeur(valeur)];
			for (int i = 0; i < listeCartes.length; i++) {
				listeCartes[i]=this.mainJoueur.prendreCarteValeur(valeur);
				
			}
			
		}
		System.out.println("Choix : \n");
		for (int i = 0; i < listeCartes.length; i++) {
			System.out.println(listeCartes[i]);
		}
		return listeCartes;
	}

	/**
	 * Méthode permettant au joueur IaOffensif d'échanger ses cartes en début de partie.
	 * Ce dernier va tenter de récupérer en priorité des cartes spéciales. Parmi ces dernières, 
	 * l'as (envoyer le tapis à un joueur) et le huit(faire passer son tour) seront privilégiées
	 * On n'échange pas les cartes spéciales déjà présentes dans la main.
	 * 
	 * @see MainJoueur#ajouterCarte(Carte)
	 * @see MainJoueur#contenirCarteNonSpeciale()
	 * 
	 * @see TasVisible#ajouterCarte(Carte)
	 * @see TasVisible#prendreCarteSpeciale()
	 */
	public void changerCartes() {

		int nbChangement = 3;
		
		boolean changer = true;
		System.out.println(changer);
		while (this.tasVisible.contenirCarteSpeciale() && this.mainJoueur.contenirCarteNonSpeciale() && nbChangement > 0) {
			this.mainJoueur.ajouterCarte(this.tasVisible.prendreCarteSpeciale());
			this.tasVisible.ajouterCarte(this.mainJoueur.prendreCarteNonSpeciale());
			nbChangement--;
		}
	}
	

		
	}




	


