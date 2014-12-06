package fr.utt.lo02.partie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.Pioche;
import fr.utt.lo02.carte.Tapis;
import fr.utt.lo02.joueur.Humain;
import fr.utt.lo02.joueur.IaAleatoire;
import fr.utt.lo02.joueur.IaOffensive;
import fr.utt.lo02.joueur.Joueur;

public class Partie {

	private int nbJoueurs;
	private int joueurCourant;
	private ArrayList<Joueur> listeJoueurs;
	private Pioche pioche;
	private Tapis tapis;
	private static Partie instancePartie;

	private Partie() {
		this.listeJoueurs = new ArrayList<Joueur>();
		this.pioche = Pioche.getInstancePioche();
		this.tapis = Tapis.getInstanceTapis();
		this.joueurCourant = 0;
	}

	public static Partie getInstancePartie() {

		Partie instance;

		if (instancePartie == null) {
			instance = new Partie();
			instancePartie = instance;
		} else {
			instance = instancePartie;
		}
		return instance;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public int getJoueurCourant() {
		return this.joueurCourant;
	}

	public Joueur getJoueur(int position) {
		Joueur joueur = null;
		try {
			joueur = this.listeJoueurs.get(position);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return joueur;
	}

	public void setJoueurCourant(int joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public ArrayList<Joueur> getListeJoueurs() {
		return this.listeJoueurs;
	}

	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public Pioche getPioche() {
		return this.pioche;
	}

	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}

	public Tapis getTapis() {
		return this.tapis;
	}

	public void setTapis(Tapis tapis) {
		this.tapis = tapis;
	}

	public static void setInstancePartie(Partie instancePartie) {
		Partie.instancePartie = instancePartie;
	}

	/**
	 * Methode permettant de récupérer le joueur suivant
	 * 
	 * @return retourne le joueur suivant.
	 */
	public Joueur getJoueurSuivant() {
		return this.listeJoueurs.get((joueurCourant + 1)%this.nbJoueurs);
	}

	public void ajouterJoueur(Joueur joueur) {
		this.listeJoueurs.add(joueur);
		this.nbJoueurs++;
	}

	public void interfaceAjouterJoueur() {
		boolean conditionIA = false;
		boolean conditionJ = false;

		int nbJoueursHumain = 0;
		int nbJoueursIA = 0;

		int niveau = 0;

		Scanner sc = new Scanner(System.in);

		System.out.println("Combien de joueur humain voulez vous ajouter");
		String demandeNbJoueurs = sc.nextLine();

		nbJoueursHumain = Integer.parseInt(demandeNbJoueurs);

		do {
			if (nbJoueursHumain > 0 && nbJoueursHumain <= 11) {
				conditionJ = true;
			}
		} while (!conditionJ);

		System.out.println("Voulez vous ajouter des IA ?(Oui/Non)");
		String demandeIA = sc.nextLine();

		if ("OUI".equals(demandeIA.toUpperCase())) {

			System.out.println("Combien d'IA voulez vous ajouter ?");
			// String demandeNbIA = sc.nextLine();

			// nbJoueursIA= Integer.parseInt(demandeNbIA);
			nbJoueursIA = sc.nextInt();
			do {
				if (nbJoueursHumain + nbJoueursIA > 0
						&& nbJoueursHumain + nbJoueursIA <= 11) { // boucle
																	// infinie
					System.out.println("ici");
					// conditionJ = true;
					conditionIA = true;
				}
			} while (!conditionIA);

			System.out
					.println("Quel niveau d'IA désirez vous (0=aléatoire, 1=offensive)");
			// String niveauIA = sc.nextLine();
			// niveau = Integer.parseInt(niveauIA);
			niveau = sc.nextInt();
		} else {
			if (nbJoueursHumain < 2) {
				System.out
						.println("Vous ne pouvez pas lancer de partie seul, une IA a été crée ");
				nbJoueursIA = 1;
				niveau = 0;
			}
		}
		creationJoueur(nbJoueursHumain);
		creationIA(nbJoueursIA, niveau);

	}

	public void creationJoueur(int nbJoueur) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < nbJoueur; i++) {
			System.out.println("Entrez le nom du joueur");
			String nomJoueur = sc.nextLine();
			Humain joueur = new Humain(nomJoueur, this.nbJoueurs);
			ajouterJoueur(joueur);
		}

	}

	public void creationIA(int nbIA, int niveau) {

		for (int i = 0; i < nbIA; i++) {
			if (niveau == 0) {
				IaAleatoire iaAleatoire = new IaAleatoire("ia" + i,
						this.nbJoueurs);
				ajouterJoueur(iaAleatoire);
				// this.nbJoueurs++; Ces lignes font planter l'exécution
			}
			if (niveau == 1) {
				IaOffensive iaOffensive = new IaOffensive("ia" + i,
						this.nbJoueurs);
				ajouterJoueur(iaOffensive);
				// this.nbJoueurs++;
			}

		}

	}

	/**
	 * Méthode qui retourne si la partie est gagnée en vérifiant que le joueur
	 * courant a gagné ou non.
	 * 
	 * @return [true=la partie est gagnée, false=la partie continue]
	 */
	public boolean estGagnee() {
		return this.listeJoueurs.get(joueurCourant).estGagnant();
	}

	/**
	 * Methode permettant d'initialiser le jeu. -Melanger les cartes
	 * -Distribution des cartes -lancer la boucle de jeu
	 */
	public void initialisationPartie(Partie partie) {

		this.interfaceAjouterJoueur();
		this.pioche.melanger();
		this.pioche.distribuerCarte(partie);
	}

	/**
	 * Méthode permettant de gérer le joueur courant au sein de la boucle de
	 * jeu. Incrémente le joueur s'il y'a un joueur après lui sinon repasse au
	 * premier joueur.
	 */
	public void gestionDuJoueurCourant() {
		if (this.joueurCourant == this.nbJoueurs - 1) {
			this.joueurCourant = 0;
		} else {
			joueurCourant++;
		}
	}

	/**
	 * Méthode permettant de gérer l'action d'un joueur sur la partie pour un
	 * tour
	 * 
	 * @param joueur
	 */
	public void faireJouerJoueur(Joueur joueur) {

		System.out.println("C'est " + joueur.getNom() + " qui joue ! \n"
				+ this.getTapis());

		Carte[] carteJouees = joueur.choisirCarteAJouer(this.getTapis()
				.carteDuDessus());
		this.getTapis().ajouterPlusieursCartes(carteJouees);

		this.fairePiocherJoueur(joueur);

	}

	/**
	 * Méthode permettant de faire piocher un joueur pour compléter sa main. On
	 * pioche tout d'abord dans le tas Pioche puis dans les TasVisible puis
	 * TasCaché. Le nombre de carte piochée dépend du nombre de carte restantes
	 * dans la main du joueur ainsi que du nombre de cartes restantes dans la
	 * pioche. Le mode de piochage pour les TasVisible et TasCaché est différent
	 * de celui de la pioche. Dans TasVisible on pioche les 3 cartes d'un coup
	 * une fois la pioche vidée et la main de nouveau vide. Le piochage dans le
	 * TasCaché s'effectue carte par carte à chaque fois que la main ne contient
	 * plus de carte.
	 * 
	 * @param joueur
	 * 
	 */
	public void fairePiocherJoueur(Joueur joueur /* , int nbCarte */) {

		int nbMax;

		if (!this.pioche.getListeCartes().isEmpty()
				&& joueur.getMainJoueur().getListeCartes().size() < 3) { // On
																			// pioche
																			// jusqu'à
																			// avoir
																			// 3
																			// cartes
			nbMax = 3 - joueur.getMainJoueur().getListeCartes().size();

			if (this.pioche.getListeCartes().size() < nbMax)
				nbMax = this.pioche.getListeCartes().size();

			for (int i = 0; i < nbMax; i++) {
				joueur.getMainJoueur().ajouterCarte(
				this.pioche.prendreCarteDuDessus());
			}
			System.out.println(nbMax + " carte(s) a(ont) été piochée(s) par "
					+ joueur.getNom() + "\n");

		}

		else if (this.pioche.getListeCartes().isEmpty()
				&& joueur.getMainJoueur().getListeCartes().isEmpty()) {
			joueur.getMainJoueur().ajouterPlusieursCartes(
					joueur.getTasVisible().getListeCartes());
			System.out.println(joueur.getNom()
					+ " Vient de prendre les cartes de son TasVisible\n");

		}

		else if (this.pioche.getListeCartes().isEmpty() && joueur.getTasVisible().getListeCartes().isEmpty() && joueur.getMainJoueur().getListeCartes().isEmpty()) 
		{
			joueur.getMainJoueur().ajouterCarte(joueur.getTasCache().prendreCarte());
			System.out.println(joueur.getNom()+ " Vient de prendre une carte de son TasCache\n");
		}

	}

	/**
	 * Methode permettant de lancer la boucle de jeu.
	 */
	public void lancerPartie() {

		System.out.println("La partie démarre\n");
		boolean estDanish = false;
		boolean estGagnee = false;

		ActionSpeciale actionSpeciale = new ActionSpeciale(this, this.joueurCourant);

		while (!estGagnee) {
			// tant que tout le monde peu jouer ..
			while (!(estDanish && estGagnee)) {
				Joueur joueur = this.listeJoueurs.get(joueurCourant); // récupération
																		// du
																		// joueur
																		// courant

				// Si le joueur peu jouer alors on passe au suivant
				if (joueur.peutJouer(this.getTapis().carteDuDessus())) {
					// Permet de choisir les cartes que le joueur veut jouer
					this.faireJouerJoueur(joueur);
					if (this.tapis.carteDuDessus().estSpeciale()) {
						actionSpeciale.appelerBonneMethode();
					}

					System.out.println("La pioche contient "
							+ this.pioche.getListeCartes().size()
							+ " cartes. \n");
				} else // Le Joueur ne pouvant pas jouer récupère le tapis
				{
					System.out.println("Ahah "+ joueur.getNom()+ " tu ne peux pas jouer mécréant, prend toi le tapis dans la face ! \n\n");
					joueur.getMainJoueur().getListeCartes().addAll(this.tapis.prendreTapis());// Je donne le															
				}
				this.gestionDuJoueurCourant();

			}
		}
	}

	public static void main(String[] args) {

		System.out.println("Nouvelle partie de Bataille Norvegienne.");
		Partie partie = getInstancePartie();
		partie.initialisationPartie(partie);
		partie.lancerPartie();

	}

}
