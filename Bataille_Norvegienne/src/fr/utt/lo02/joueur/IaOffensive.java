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
	 * L'Ia agressive privilégie la pose de cartes spéciales. Elle pose le maximum de cartes posables. 
	 * Pour l'instant même comportement que l'iaAléatoire
	 */

	public Carte[] choisirCarteAJouer() {

		Random rand = new Random();
		Carte[] listeCartes;
		int numCarte = 0;
		int nombreMaxCarteJouable;

		nombreMaxCarteJouable = this.mainJoueur.calculerNbMaxCarteMemeValeur();
		if (nombreMaxCarteJouable > 3)
			nombreMaxCarteJouable = 3;

		int nb = rand.nextInt(nombreMaxCarteJouable + 1);
		while (nb == 0) {
			nb = rand.nextInt(nombreMaxCarteJouable + 1);
		}

		listeCartes = new Carte[nb];

		numCarte = rand.nextInt(3 + 1);

		while (numCarte > 3
				|| (this.mainJoueur
						.calculerNbOccurenceMemeValeur(this.mainJoueur
								.getCarte(numCarte)) != nombreMaxCarteJouable)) {
			numCarte = rand.nextInt(3 + 1);
		}
		int valeur = this.mainJoueur.getCarte(numCarte).getValeur();
		for (int i = 0; i < listeCartes.length; i++) {
			listeCartes[i] = this.mainJoueur.prendreCarte(this.mainJoueur
					.calculerPositionCarteValeur(valeur));
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




	


