package fr.utt.lo02.vue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import fr.utt.lo02.joueur.Joueur;

public class InfoPartiePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private JEditorPane ep;
	private JScrollPane sp;
	private String infoPartie;
	private ArrayList<Joueur> listeJoueur;
	
	public InfoPartiePanel(ArrayList<Joueur> liste){
		
		this.listeJoueur = liste;
		this.infoPartie = genererInfoPartie();
		this.ep = new JEditorPane("text/html", this.infoPartie);
		this.ep.setPreferredSize(new Dimension(250, 200));

		this.ep.setEditable(false);
		
		this.sp = new JScrollPane(ep);
		
		this.add(sp);
	}

	private String genererInfoPartie(){
		StringBuffer sb = new StringBuffer();
		sb.append("<h3>Etat de la partie</h3><br>");
		
//		for (Iterator<Joueur> iterator = this.listeJoueur.iterator(); iterator.hasNext();) {
//			Joueur joueur = (Joueur) iterator.next();
//			sb.append("<b>"+joueur.getNom()+"</b>" +" Main: " +joueur.getMainJoueur().getListeCartes().size() );
//			sb.append("TV : "+joueur.getTasVisible().getListeCartes().size());
//			sb.append("TC : "+joueur.getTasCache().getListeCartes().size() +"<br>");		
//		}	
		
		sb.append("<TABLE BORDER=\"0\"> <TR>  <TH>Nom</TH> <TH>Main</TH> <TH>Visible</TH> <TH>Cache</TH>  </TR> <TR> ");
		for (Iterator<Joueur> iterator = listeJoueur.iterator(); iterator.hasNext();) {
			Joueur joueur = (Joueur) iterator.next();
			sb.append("<TH>"+joueur.getNom()+"</TH><TD>"+joueur.getMainJoueur().getListeCartes().size()+"</TD>");
			sb.append("<TD>"+joueur.getTasVisible().getListeCartes().size()+"</TD> <TD>"+joueur.getTasVisible().getListeCartes().size()+"</TD> </TR>" );
		}
		sb.append("</TABLE> ");
		return sb.toString();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
