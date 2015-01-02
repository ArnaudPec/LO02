package fr.utt.lo02.vue;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.utt.lo02.carte.Carte;

public class TapisPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private CartePanel derniereCarte;
	private CartePanel dessinPioche;
	
	public TapisPanel(BufferedImage matriceCarte){
		
		this.setPreferredSize(new Dimension(500,320));
		this.dessinPioche = new CartePanel(null, matriceCarte, 0.7);
		this.derniereCarte = new CartePanel(null, matriceCarte, 0.7);
		this.add(this.derniereCarte);	
		this.add(this.dessinPioche);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
