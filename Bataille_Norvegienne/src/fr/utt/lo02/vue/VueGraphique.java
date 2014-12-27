package fr.utt.lo02.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class VueGraphique implements ActionListener {

	private JFrame window;
	private JMenuBar menuBar;
	private Container container;
	
	private JMenu jmenuPartie;
	private JMenu jmenuAide;
	private JMenuItem itemNouvellePartie;
	private JMenuItem itemQuitter;
	private JMenuItem itemAPropos;
	private JMenuItem itemRegles;
	
	public VueGraphique (){
		
		// Fenêtre
		this.window = new JFrame("Bataille Norvégienne");
		this.window.setVisible(true);
		this.window.setSize(800,600);
		this.window.setResizable(true);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Container
		this.container = this.window.getContentPane();
				
		JButton button = new JButton("1");
		JButton button2 = new JButton("2");
		JButton button3 = new JButton("3");

		this.container.add(this.menuBar = new JMenuBar(), BorderLayout.NORTH);
		this.container.add(button2, BorderLayout.WEST);
		this.container.add(button3, BorderLayout.EAST);
		
		this.container.add(button, BorderLayout.SOUTH);
		
		this.container.setBackground(new Color(254));
		
		// Menu
		this.menuBar.add(this.jmenuPartie = new JMenu("Partie"));
		this.menuBar.add(this.jmenuAide = new JMenu("Aide"));
		this.jmenuPartie.add(this.itemNouvellePartie = new JMenuItem("Nouvelle Partie"));
		this.jmenuPartie.add(this.itemQuitter = new JMenuItem("Quitter"));
		this.jmenuAide.add(this.itemRegles = new JMenuItem("Règles du jeu"));
		this.jmenuAide.add(this.itemAPropos = new JMenuItem("A propos"));
		
		//this.window.pack();
		// Actions
		this.itemQuitter.addActionListener(this);
		this.itemAPropos.addActionListener(this);
		this.itemNouvellePartie.addActionListener(this);
		this.itemRegles.addActionListener(this);

	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.itemQuitter){
			System.exit(0);
		}
		else if (e.getSource() == this.itemAPropos){
			System.out.println("A propos");
			JOptionPane.showMessageDialog(this.window, "Bataille Norvégienne\nPar Charlélie Borella et Arnaud Pecoraro\nAutomne 2014 - UV LO02", "A propos", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == this.itemNouvellePartie){
			System.out.println("Nouvelle Partie");
			this.container.setBackground(new Color(166,66,66));

		}
		else if (e.getSource() == this.itemRegles){
			
			System.out.println("Règles du jeu");
			
			FenetreReglesJeu ed = new FenetreReglesJeu();
			ed.setVisible(true);
			
		}
	}
}
