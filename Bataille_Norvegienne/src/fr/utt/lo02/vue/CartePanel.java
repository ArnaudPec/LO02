package fr.utt.lo02.vue;

import java.awt.Dimension;
import java.awt.image.BufferedImage;


import fr.utt.lo02.carte.Carte;

public class CartePanel extends ImagePanel {
	
	private static final long serialVersionUID = 1L;
	public static final Dimension TAILLE = new Dimension(168, 244);

	
	public CartePanel ( Carte c, BufferedImage matriceCarte){
		super();
		this.setPreferredSize(TAILLE);
		super.image = matriceCarte.getSubimage(this.getPositionX(c.getValeur())*TAILLE.width,this.getPositionY(c.getCouleur())*TAILLE.height,TAILLE.width,TAILLE.height);
	}
	
	public CartePanel (BufferedImage matriceCarte){
		super();
		this.setPreferredSize(TAILLE);
		super.image = matriceCarte.getSubimage(2*TAILLE.width,4*TAILLE.height,TAILLE.width,TAILLE.height);
	}
	
	/**
	 * Méthode permettant de récupérer la position en x du coin supérieur gauche de la carte
	 * @param valeur de la carte
	 * @return la position (colonne)
	 */
	private int getPositionX(int valeur){
		if(valeur == 14) valeur = 0;
		else valeur -=1;
		return valeur;
	}
	
	/**
	 * Méthode permettant de récupérer la position en y du coin supérieur gauche de la carte
	 * @param couleur de la carte
	 * @return la position (ligne)
	 */
	private int getPositionY(int couleur){
		int ligne = 0;
		switch (couleur) {
		case 0:
			ligne = 2;
			break;
		case 1:
			ligne = 3;
			break;
		case 2:
			ligne = 0;
			break;
		case 3:
			ligne = 1;
			break;
		
		}
		
		return ligne;
	}
}
