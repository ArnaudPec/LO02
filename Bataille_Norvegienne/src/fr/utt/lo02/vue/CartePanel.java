package fr.utt.lo02.vue;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import fr.utt.lo02.carte.Carte;

public class CartePanel extends ImagePanel {
	
	private static final long serialVersionUID = 1L;
	private Carte carte;
	public static final Dimension TAILLE = new Dimension(168, 244);
	
	public CartePanel ( Carte c, BufferedImage matriceCarte, double taille){
		super();
		
		this.carte = c;
		
		BufferedImage bfi;
		
		if(c!=null){ // on cherche une carte donnée
			 bfi= matriceCarte.getSubimage(this.getPositionX(c.getValeur())*TAILLE.width,this.getPositionY(c.getCouleur())*TAILLE.height,TAILLE.width,TAILLE.height);
		}
		else { // on veut la carte retournée
			 bfi = matriceCarte.getSubimage(2*TAILLE.width,4*TAILLE.height,TAILLE.width,TAILLE.height);
		}
		
		super.image = redimCarte(bfi, taille);
		
		if (carte != null ) {this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Vous avez cliqué sur un " + carte.getValeurAffichage());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
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
	
	
	/**
	 * Méthode permettant de redimensionner la carte
	 * @param img 
	 * @param cf
	 * @return
	 */
	public BufferedImage redimCarte(BufferedImage img, double cf)
	 {
	     AffineTransform  tx = new AffineTransform();
	     tx.scale(cf, cf);
	     AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BICUBIC);
	     BufferedImage newImage = new BufferedImage((int)(img.getWidth(null)*cf),(int)(img.getHeight(null)*cf),img.getType());
	 
	     Dimension d = new Dimension();
	     d.setSize(TAILLE.getWidth()*cf, TAILLE.getHeight()*cf);
	     this.setPreferredSize(d);
	     
	     return op.filter(img, newImage);
	 }
	
}