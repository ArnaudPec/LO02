package fr.utt.lo02.vue;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPartiePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private JTextArea jta;
	
	public InfoPartiePanel(){
		String texte = new String();
		texte = "Etat de la partie :  \n\nListe des Joueurs : \n \nBob main : 3 tc : 2 tv : 0 \n \nJean main : 3 tc : 3 tv : 3";
		
		this.jta = new JTextArea(texte);
		this.jta.setEditable(false);
		this.add(jta);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
