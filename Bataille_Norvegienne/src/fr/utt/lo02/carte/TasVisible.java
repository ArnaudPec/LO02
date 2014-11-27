package fr.utt.lo02.carte;

import java.util.LinkedList;

public class TasVisible extends Tas {

	
	/**
	 * Constructeur de TasVisible. 
	 */
	public TasVisible() {
		this.listeCartes = new LinkedList<Carte>();
	}
		
	
	/**
	 * Méthode pour prendre la première carte du Tas et la supprimer.
	 * @return la première carte de la liste.
	 */
	public Carte prendreCarte(){
		return this.listeCartes.pollFirst();
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
		
		for (int i = 0; i < this.listeCartes.size(); i++) {
			
			numCarte+= "  n°" + i + "    ";
			bordureDessus+= " —————️ " + "  ";
			dessus+= "|"+ this.listeCartes.get(i).getCouleurAffichage() + "    |  ";
			milieu+= "|  " + this.listeCartes.get(i).getValeurAffichage() + "  |  ";
			bas+= "|    "+ this.listeCartes.get(i).getCouleurAffichage() + "|  ";
			bordureDessous+= " —————️ " + "  ";		
		}
		
		resultat += numCarte + "\n" + bordureDessus + "\n" + dessus+ "\n" + milieu + "\n" + bas + "\n" + bordureDessous + "\n";
				
		return resultat;
	}
	

}
