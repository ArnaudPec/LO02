package fr.utt.lo02.partie;

import java.util.ArrayList;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.vue.VueGraphique;

public class PartieControleur{

	private Partie partie;
	private VueGraphique vueGraphique=null;
	private ArrayList<Carte> listeCartesSelecrionnes;
	
	private ArrayList<Carte> ListeCarte;
	
	public PartieControleur(Partie partie) {
		//this.vueGraphique = vueGraphique;
		this.ListeCarte = new ArrayList<Carte>();
		this.partie = partie;
		this.listeCartesSelecrionnes = new ArrayList<Carte>();
	}
	
	public void actionCarteSelectionne(int i){
	}
	
	public Partie getPartie(){
		return this.partie;
	}
	
	public void ajouterCarteSelectionne(Carte c){
		this.listeCartesSelecrionnes.add(c);
		System.out.println(this.listeCartesSelecrionnes);
	}
	
	public void supprimerCarteSelectionne(Carte c){
		this.listeCartesSelecrionnes.remove(c);		
		System.out.println(this.listeCartesSelecrionnes);

	}
	
}
