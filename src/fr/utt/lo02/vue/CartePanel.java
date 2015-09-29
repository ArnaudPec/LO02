package fr.utt.lo02.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.partie.Controleur;

/**
 * Classe matérialisant graphiquement une carete du jeu la bataille Norvégienne. Elle hérite de la classe ImagePanel.
 * @see ImagePanel
 *
 */
public class CartePanel extends ImagePanel implements Observer {
	
	
	private static final long serialVersionUID = 1L;
	private Carte carte;
	private boolean selectionnee;
	public static final Dimension TAILLE = new Dimension(168, 244);
	private BufferedImage bfi;
	
	public CartePanel ( Carte c, BufferedImage matriceCarte, boolean selectionnable, double taille, final Controleur partieControleur){
		super(partieControleur);
		
		this.carte = c;
		this.selectionnee=false;
		Carte cartePrecedente = partieControleur.getPartie().getTapis().getCarteDuDessus();
		
		if(c!=null && c.estPosable(cartePrecedente)){ // on cherche une carte donnée
			 this.bfi= matriceCarte.getSubimage(this.getPositionX(c.getValeur())*TAILLE.width,
					 this.getPositionY(c.getCouleur())*TAILLE.height,TAILLE.width,TAILLE.height);
		}
		else { // on veut la carte retournée
			 this.bfi = matriceCarte.getSubimage(2*TAILLE.width,4*TAILLE.height,TAILLE.width,TAILLE.height);
		}
		super.image = redimCarte(this.bfi, taille);
		
		if (carte != null && c.estPosable(cartePrecedente) && selectionnable) {this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				System.out.println("Vous avez cliqué sur un " + carte.getValeurAffichage() + " de " +carte.getCouleurAffichage());
				if(!CartePanel.this.selectionnee) {
					CartePanel.this.selectionnee = true;
					CartePanel.this.mettreSurbrillance();
					CartePanel.this.partieControleur.ajouterCarteSelectionne(CartePanel.this.carte);
				}
				else {
					CartePanel.this.selectionnee = false;
					CartePanel.this.partieControleur.supprimerCarteSelectionne(CartePanel.this.carte);
					CartePanel.this.enleverSurbrillance();
				}
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
	 * Méthode permettant de mettre en évidence la sélection de carte
	 */
	protected void mettreSurbrillance() {
		this.bfi = super.image;
		super.image = redimCarte(super.image, 0.5);
		this.setBackground(new Color(0,90,50));
		this.repaint();
		
	}
	/**
	 * Méthode permettant de déselectionner une carte
	 */
	protected void enleverSurbrillance() {
		super.image = this.bfi;
		this.repaint();
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
	
	
	public void setCarte(Carte c){
		this.carte = c;
	}
	
	/**
	 * Méthode permettant de redimensionner la carte pour l'adapter aux différents affichages selon le contexte
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}	
}
