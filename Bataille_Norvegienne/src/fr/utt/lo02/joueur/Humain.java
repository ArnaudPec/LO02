package fr.utt.lo02.joueur;

import java.util.Scanner;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.carte.TasCache;
import fr.utt.lo02.carte.TasVisible;

public class Humain extends Joueur {

	public Humain(String nomJoueur, int numJoueur) {
		super(nomJoueur, numJoueur);
		// TODO Auto-generated constructor stub
	}

	
public Carte[] choisirCarteAJouer(){
		
		
		
		System.out.println("choisissez les ou la carte(s) que vous voulez jouer.");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		String[] split = str.split("-");
		
		Carte[] listeCarte = new Carte[split.length];
		
		for(int i = 0; i < split.length; i++) {
			listeCarte[i] = this.mainJoueur.getListeCartes().get(Integer.parseInt(split[i]));
		}
		
		return listeCarte;	
		
	}

}
