package fr.utt.lo02.vue;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.partie.PartieControleur;

public class TapisPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private CartePanel derniereCarte;
	private CartePanel dessinPioche;
	
	public TapisPanel(BufferedImage matriceCarte, PartieControleur partieControleur){
		this.setPreferredSize(new Dimension(500,320));
		
		Carte cartePrecedente = partieControleur.getPartie().getTapis().getCarteDuDessus();
		
		this.dessinPioche = new CartePanel(cartePrecedente, matriceCarte, 0.7, partieControleur, 0);

		this.derniereCarte = new CartePanel(null, matriceCarte, 0.7, partieControleur, 0);
		this.add(this.derniereCarte);	
		this.add(this.dessinPioche);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void setDessinPioche(Carte c){
		this.dessinPioche.setCarte(c);
	}

}
