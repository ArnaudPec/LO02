package fr.utt.lo02.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.JPanel;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.carte.MainJoueur;
import fr.utt.lo02.partie.PartieControleur;


public class MainScrollPane extends ScrollPane {
	
	private static final long serialVersionUID = 1L;
	private JPanel main;
	private MainJoueur mainJoueur;
	private BufferedImage matriceCarte;
	private PartieControleur partieControleur;

	public MainScrollPane(BufferedImage matriceCarte, MainJoueur mainJoueur, PartieControleur partieControleur) throws HeadlessException {
		super();
		this.partieControleur = partieControleur;
		this.mainJoueur = mainJoueur;
		this.matriceCarte = matriceCarte;
		this.setPreferredSize(new Dimension(800,200));
		this.ajouterCartesMainJoueur();
		this.add(this.main);
	}

	@Override
	public void setBackground(Color c) {
		super.setBackground(c);
		this.main.setBackground(c);
	}
	
	private void ajouterCartesMainJoueur(){
		this.main = new JPanel();
		int position = 0;
		for (Iterator<Carte> iterator = this.mainJoueur.getListeCartes().iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			this.main.add(new CartePanel(carte, this.matriceCarte, 0.5, partieControleur, position));
			position++;
		}
	}
}
