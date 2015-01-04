package fr.utt.lo02.partie;

import java.util.ArrayList;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.vue.VueGraphique;

public class PartieControleur{

	private Partie partie;
	private VueGraphique vueGraphique=null;
	private ArrayList<Carte> listeCartesSelectionnees;
	
	private ArrayList<Carte> ListeCarte;
	
	public PartieControleur(Partie partie) {
		//this.vueGraphique = vueGraphique;
		this.ListeCarte = new ArrayList<Carte>();
		this.partie = partie;
		this.listeCartesSelectionnees = new ArrayList<Carte>();
	}
	
	public void actionCarteSelectionne(int i){
	}
	
	public Partie getPartie(){
		return this.partie;
	}
	
	public void ajouterCarteSelectionne(Carte c){
		this.listeCartesSelectionnees.add(c);
		System.out.println(this.listeCartesSelectionnees);
	}
	
	public void supprimerCarteSelectionne(Carte c){
		this.listeCartesSelectionnees.remove(c);		
		System.out.println(this.listeCartesSelectionnees);
	}
	
	public boolean envoyerSelection(){
		
		boolean selectionCorrecte = this.verifierSelection();
		if(selectionCorrecte){
			System.out.println(selectionCorrecte +": on envoie");
		}
		return selectionCorrecte;
	}
	
	public boolean verifierSelection(){
		if(this.partie.verifierSelection(this.listeCartesSelectionnees))return true;
		else return false;
	}
}
