package fr.utt.lo02.joueur;

import java.util.ArrayList;
import java.util.Iterator;
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
	 * L'Ia offensive privilégie la pose de cartes spéciales. On tente tout
	 * d'abord de poser des as, puis des huits, puis des cartes spéciales. En
	 * dernier recours, si on ne dispose pas de carte spéciale on pose
	 * aléatoirement une carte.
	 * 
	 * Pour chaque carte posée, on évalue le nombre maximum de carte de cette
	 * valeur, et on pose toutes les cartes correspondantes. De cette manière on
	 * s'arrure de poser le maximum de carte à chaque fois.
	 * 
	 * @return une liste de carte
	 * 
	 * @see MainJoueur#contenirCarte(int)
	 * @see MainJoueur#calculerNbOccurenceMemeValeur(Carte)
	 * @see MainJoueur#prendreCarteValeur(int)
	 * 
	 * @see Random
	 * 
	 */

	public Carte[] choisirCarteAJouer(Carte derniereCarte) {

		this.mainJoueur.trierCartesJouables(derniereCarte);
		System.out.println("Main Jouable" + this.mainJoueur);
		Carte[] listeCartes;
		int as = 14, huit = 8;

		if (this.mainJoueur.contenirCarte(as)) { // on récupère le(s) as

			listeCartes = new Carte[this.mainJoueur.calculerNbOccurenceMemeValeur(as)]; // on initialise le// tableau avec le nombre d'as présents
			for (int i = 0; i < listeCartes.length; i++) {
				listeCartes[i] = this.mainJoueur.prendreCarteValeur(as); // on prend tous les as trouvés
			}
		}

		else if (this.mainJoueur.contenirCarte(huit)) { // on pose le(s) huit(s)
			listeCartes = new Carte[this.mainJoueur.calculerNbOccurenceMemeValeur(huit)];
			for (int i = 0; i < listeCartes.length; i++) {
				listeCartes[i] = this.mainJoueur.prendreCarteValeur(huit);
			}
		}

		else if (this.mainJoueur.contenirCarteSpeciale()) { // on pose une carte spéciale
			Carte carte = this.mainJoueur.prendreCarteSpeciale();
			listeCartes = new Carte[this.mainJoueur
					.calculerNbOccurenceMemeValeur(carte) + 1];// le +1
																// correspond à
																// la carte déjà
																// prise ligne
																// précédente
			System.out.println(this.mainJoueur
					.calculerNbOccurenceMemeValeur(carte));
			listeCartes[0] = carte;
			for (int i = 1; i < listeCartes.length; i++) {
				listeCartes[i] = this.mainJoueur.prendreCarteValeur(carte
						.getValeur());
			}
		}

		else { // s'il n'y a pas de cartes spéciales on tire une carte au hasard
			Random rand = new Random();
			int valeur = this.mainJoueur.getCarte(
					rand.nextInt(this.mainJoueur.getListeCartes().size()))
					.getValeur();
			listeCartes = new Carte[this.mainJoueur
					.calculerNbOccurenceMemeValeur(valeur)];
			for (int i = 0; i < listeCartes.length; i++) {
				listeCartes[i] = this.mainJoueur.prendreCarteValeur(valeur);

			}

		}
		System.out.println("Choix : \n");
		for (int i = 0; i < listeCartes.length; i++) {
			System.out.println(listeCartes[i]);
		}

		this.mainJoueur.fusionner();

		return listeCartes;
	}

	/**
	 * Méthode permettant au joueur IaOffensif d'échanger ses cartes en début de
	 * partie. Ce dernier va tenter de récupérer en priorité des cartes
	 * spéciales. Parmi ces dernières, l'as (envoyer le tapis à un joueur) et le
	 * huit(faire passer son tour) seront privilégiées On n'échange pas les
	 * cartes spéciales déjà présentes dans la main.
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
		while (this.tasVisible.contenirCarteSpeciale()
				&& this.mainJoueur.contenirCarteNonSpeciale()
				&& nbChangement > 0) {
			this.mainJoueur
					.ajouterCarte(this.tasVisible.prendreCarteSpeciale());
			this.tasVisible.ajouterCarte(this.mainJoueur
					.prendreCarteNonSpeciale());
			nbChangement--;
		}
	}

	/** 
	 * Méthode permettant de choisir un joueur dans le cas de la pose d'un as. Il ne doit pas se désigner lui même.
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
