package fr.utt.lo02.carte;

import java.util.Iterator;
import java.util.LinkedList;

public class TasVisible extends Tas {

	
	/**
	 * Constructeur de TasVisible. 
	 */
	public TasVisible() {
		super.listeCartes = new LinkedList<Carte>();
	}
		
	
	/**
	 * Méthode pour prendre la première carte du Tas et la supprimer.
	 * @return la première carte de la liste.
	 */
	public Carte prendreCarte(){
		return super.listeCartes.pollFirst();
	}
	
	/**
	 * Edit : j'ai ajouté cette méthode pour faciliter l'échanger de carte, pour pouvoir échanger à une position donnée
	 * @param position
	 * @return la première carte de la liste.
	 */
	public Carte prendreCarte(int position)
	{

		Carte carte = super.listeCartes.get(position);
		this.listeCartes.remove(position);
		
		return carte;
	}

	/**
	 * Méthode qui permet de savoir si le TasVisible contient des cartes spéciales
	 * @return un booleen qui indique la présence ou non d'une carte spéciale
	 */
	public boolean contenirCarteSpeciale(){
		Iterator<Carte> it = this.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.estSpeciale())return true;
		}
		return false;
	}
	
	
	/**
	 * Méthode qui permet de récupérer une carte spéciale contenue dans le TasVisible. Elle retourne en priorité les cartes spéciales As et Huit
	 * @return une carte spéciale, en priorité un As, ou un huit ou null s'il n'y a plus de carte spéciale
	 */
	public Carte prendreCarteSpeciale() {
		Iterator<Carte> it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.getValeur()==14) {  // c'est un as	
				it.remove();
				return carte;
			}
		}
		
		it = super.listeCartes.iterator();
		
		while (it.hasNext()) {
			Carte carte = (Carte) it.next(); 
			if(carte.getValeur()==8) { // c'est un huit
				it.remove();
				return carte; 
			}
		}
		
		it = super.listeCartes.iterator();
		while (it.hasNext()) {
			Carte carte = (Carte) it.next();
			if(carte.estSpeciale()){ // c'est une carte spéciale
				it.remove();
				return carte; 	
			}
		}
		return null;
		
	}
	@Override
	public String toString(){
		
		String resultat = "Tas Visible \n\n";
		String numCarte = "";
		String bordureDessus ="";
		String dessus = "";
		String milieu = "";
		String bas = "";
		String bordureDessous = "";
		
		for (int i = 0; i < super.listeCartes.size(); i++) {
			
			numCarte+= "  n°" + i + "    ";
			bordureDessus+= " —————️ " + "  ";
			dessus+= "|"+ super.listeCartes.get(i).getCouleurAffichage() + "    |  ";
			milieu+= "|  " + super.listeCartes.get(i).getValeurAffichage() + "  |  ";
			bas+= "|    "+ super.listeCartes.get(i).getCouleurAffichage() + "|  ";
			bordureDessous+= " —————️ " + "  ";		
		}
		
		resultat += numCarte + "\n" + bordureDessus + "\n" + dessus+ "\n" + milieu + "\n" + bas + "\n" + bordureDessous + "\n";
				
		return resultat;
	}
	

}
