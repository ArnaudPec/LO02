package fr.utt.lo02.carte;

import java.util.LinkedList;

public class Tapis extends Tas {

	private static Tapis instanceTapis;

	private Tapis() {
		this.listeCartes = new LinkedList<Carte>();
	}

	
	/**
	 * Méthode pour récupérer une instance de Tapis.
	 * @return une instance de Tapis
	 */	
	public static Tapis getInstanceTapis() {
		Tapis instance;

		if (instanceTapis == null) {
			instance = new Tapis();
			instanceTapis = instance;
		} else {
			instance = instanceTapis;
		}

		return instance;
	}
	
	
	/**
	 * Permet de récupérer la première carte de la liste.
	 * @return Une carte.
	 */
	public Carte carteDuDessus(){
		
		return this.listeCartes.getFirst();
	}
	
	
	/**
	 *Permet de vider le tapis, supprime tout les éléments de la liste. 
	 */
	public void viderTapis(){
		this.listeCartes.clear();
	}
	
	@Override
	public String toString(){
		return "Tapis \n" + carteDuDessus().toString() + "\n"; 
	}

	public static void main(String[] args) {

	}
}
