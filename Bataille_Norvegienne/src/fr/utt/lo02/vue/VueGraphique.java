package fr.utt.lo02.vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Container
		this.container = this.window.getContentPane();
		this.container.add(this.menuBar= new JMenuBar(), BorderLayout.NORTH);
		
		// Menu
		this.menuBar.add(this.jmenuPartie = new JMenu("Partie"));
		this.menuBar.add(this.jmenuAide = new JMenu("Aide"));
		this.jmenuPartie.add(this.itemNouvellePartie = new JMenuItem("Nouvelle Partie"));
		this.jmenuPartie.add(this.itemQuitter = new JMenuItem("Quitter"));
		this.jmenuAide.add(this.itemRegles = new JMenuItem("Règles du jeu"));
		this.jmenuAide.add(this.itemAPropos = new JMenuItem("A propos"));
		
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
			JOptionPane.showMessageDialog(this.window, "Message", "Titre", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == this.itemNouvellePartie){
			System.out.println("Nouvelle Partie");
		}
		else if (e.getSource() == this.itemRegles){
			
			System.out.println("Règles du jeu");
			JTextArea ja = new JTextArea(5, 40);
			Charset charset = Charset.forName("US-ASCII");
			Path p = Paths.get("/home/arnaud/workspace/projetLO02/LO02/Bataille_Norvegienne/regles");
			
			try (BufferedReader reader = Files.newBufferedReader( p , charset)) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			        System.out.println(line);
			    }
			} catch (IOException x) {
			    System.err.format("IOException: %s%n", x);
			}
			
			
			ja.setEditable(false);
			ja.setText("lorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\nlorem ipsum dolores\n");			
			JOptionPane.showMessageDialog(this.window, ja, "Titre", JOptionPane.INFORMATION_MESSAGE);

			
//			FenetreReglesJeu ed = new FenetreReglesJeu();
//			ed.setVisible(true);
			
		}
	}
}
