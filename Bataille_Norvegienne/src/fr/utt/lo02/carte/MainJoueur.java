package fr.utt.lo02.carte;

import java.util.LinkedList;

public class MainJoueur extends Tas{

	public MainJoueur() {
		this.listeCartes = new LinkedList<Carte>();
	}
	
	/**
	 * 
	 * Tu pourrais m'expliquer l'objectif de cette méthode stp ?
	 * Je ne comprends pas ce qu'elle fait : elle prend une carte à quel objet ?
	 * En lisant le code j'ai l'impression qu'il s'agit plutôt de poser une carte de la main puisqu'elle retourne une carte et que tu spécifies la position de la carte à "poser"
	 * 
	 * @param position
	 * @return
	 */
	public Carte prendreCarte(int position)
	{
		Carte carte = this.listeCartes.get(position);
		this.listeCartes.remove(position);
		
		return carte;
	}
	
	@Override
	public String toString()
	{
		String resultat = "";
		
		for (int i = 0; i < this.listeCartes.size(); i++) {
			
			resultat+= this.listeCartes.toString() + " ";
			
		}
		return resultat;
	}
	
}
