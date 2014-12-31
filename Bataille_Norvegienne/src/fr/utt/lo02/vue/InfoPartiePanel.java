package fr.utt.lo02.vue;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InfoPartiePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private JEditorPane ep;
	private JScrollPane sp;
	
	public InfoPartiePanel(){
		String texte = new String();
		texte = "Etat de la partie :  \n\nListe des Joueurs : \n \nBob main : 3 tc : 2 tv : 0 \n \nJean main : 3 tc : 3 tv : 3";
		
		this.ep = new JEditorPane("text", texte);
		this.ep.setPreferredSize(new Dimension(200, 200));

		this.ep.setEditable(false);
		
		this.sp = new JScrollPane(ep);
		
		this.add(sp);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
