package fr.utt.lo02.partie;

import java.util.ArrayList;

import fr.utt.lo02.joueur.Joueur;

public class Partie {

	private int nbJoueurs;
	private ArrayList<Joueur> listeJoueurs;

	public Partie() {
		// TODO Auto-generated constructor stub
	}

	public int getnbJoueurs() 
	{
		return this.nbJoueurs;
	}
	
	public Joueur getJoueur(int position)
	{
		Joueur joueur=null;
		try 
		{
			joueur = this.listeJoueurs.get(position);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return joueur;
		
	}
}
