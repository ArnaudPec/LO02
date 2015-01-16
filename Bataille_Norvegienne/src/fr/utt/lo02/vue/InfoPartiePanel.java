package fr.utt.lo02.vue;

import java.awt.Dimension;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.utt.lo02.joueur.Joueur;
import fr.utt.lo02.partie.Partie;

public class InfoPartiePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JEditorPane ep;
	private JScrollPane sp;
	private String infoPartie;
	private Partie partie;
	
	public InfoPartiePanel(Partie partie){
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.partie = partie;
		this.infoPartie = genererInfoPartie();
		this.ep = new JEditorPane("text/html", this.infoPartie);
		this.ep.setPreferredSize(new Dimension(250, 200));
		this.ep.setEditable(false);
		this.sp = new JScrollPane(ep);
		this.add(sp);
	}

	/**
	 * Méthode récupérant les information actuelles sur l'état de la partie et générant un contenu html qui sera affiché
	 * @return un contenu html sous forme de String
	 */
	private String genererInfoPartie(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h3>Etat de la partie</h3><br>");
		sb.append("<TABLE BORDER=\"0\"> <TR>  <TH>Nom</TH> <TH>Main</TH> <TH>Visible</TH> <TH>Cache</TH>  </TR> <TR> ");
		for (Iterator<Joueur> iterator = this.partie.getListeJoueurs().iterator(); iterator.hasNext();) {
			Joueur joueur = (Joueur) iterator.next();
			sb.append("<TH>"+joueur.getNom()+"</TH><TD>"+joueur.getMainJoueur().getListeCartes().size()+"</TD>");
			sb.append("<TD>"+joueur.getTasVisible().getListeCartes().size()+"</TD> <TD>"+joueur.getTasCache().getListeCartes().size()+"</TD> </TR>" );
		}
		sb.append("</TABLE> ");
		sb.append("<br><b>Tapis :</b>  "+this.partie.getTapis().getListeCartes().size());
		sb.append("<br><b>Pioche :</b> "+this.partie.getPioche().getListeCartes().size());
		sb.append("<br><b>Cartes en jeu :</b> "+this.partie.calculerNombreCarteTotal());

		return sb.toString();
	}
}	
