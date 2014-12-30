package fr.utt.lo02.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fr.utt.lo02.carte.Carte;


public class MainScrollPane extends ScrollPane {
	
	private static final long serialVersionUID = 1L;
	private JPanel main;

	public MainScrollPane(BufferedImage matriceCarte) throws HeadlessException {
		super();
		
		this.setPreferredSize(new Dimension(800,200));
		this.main = new JPanel();
		
		this.main.add(new CartePanel(new Carte(0, 2), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(3, 3), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(1, 2), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(2, 10), matriceCarte, 0.5));	
		this.main.add(new CartePanel(new Carte(0, 12), matriceCarte, 0.5));	
		this.main.add(new CartePanel(new Carte(0, 3), matriceCarte, 0.5));	
		this.main.add(new CartePanel(new Carte(2, 14), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(0, 2), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(3, 3), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(0, 2), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(0, 3), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(0, 2), matriceCarte, 0.5));
		this.main.add(new CartePanel(new Carte(0, 3), matriceCarte, 0.5));
		
		this.add(this.main);
	}

	@Override
	public void setBackground(Color c) {
		super.setBackground(c);
		this.main.setBackground(c);
	}
	
	

	

}
