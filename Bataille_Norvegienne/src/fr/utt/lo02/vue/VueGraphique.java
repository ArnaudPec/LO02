package fr.utt.lo02.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.utt.lo02.carte.Carte;

public class VueGraphique implements ActionListener {

	private JFrame window;
	private JMenuBar menuBar;
	private ImagePanel container;
	
	private JMenu jmenuPartie;
	private JMenu jmenuAide;
	private JMenuItem itemNouvellePartie;
	private JMenuItem itemQuitter;
	private JMenuItem itemAPropos;
	private JMenuItem itemRegles;
	
	private JPanel mainPanel;
	private JPanel tapisPanel;
	private JPanel infoPartiePanel;
	private BufferedImage matriceCarte;
	
	

	public VueGraphique (){
		
		// Fenêtre
		this.window = new JFrame("Bataille Norvégienne");
		this.window.setVisible(true);
	//	this.window.setSize(800,600);
		this.window.setPreferredSize(new Dimension(800,600));
		this.initialiserMatriceCarte();

		this.window.setResizable(true);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Container
		this.container =  new ImagePanel(new BorderLayout());  
		this.window.setContentPane(this.container);  
		this.container.add(this.menuBar = new JMenuBar(), BorderLayout.NORTH);
		this.container.add(this.tapisPanel = new JPanel(true), BorderLayout.WEST);
		this.container.add(this.infoPartiePanel = new JPanel(true), BorderLayout.EAST);
		this.container.add(this.mainPanel = new JPanel(true), BorderLayout.SOUTH);
		
//		this.tapisPanel.setPreferredSize(new Dimension(500, 200));
//		this.mainPanel.setPreferredSize(new Dimension(800, 200));
//		this.infoPartiePanel.setPreferredSize(new Dimension(300, 200));
		
		// Menu
		this.menuBar.add(this.jmenuPartie = new JMenu("Partie"));
		this.menuBar.add(this.jmenuAide = new JMenu("Aide"));
		this.jmenuPartie.add(this.itemNouvellePartie = new JMenuItem("Nouvelle Partie"));
		this.jmenuPartie.add(this.itemQuitter = new JMenuItem("Quitter"));
		this.jmenuAide.add(this.itemRegles = new JMenuItem("Règles du jeu"));
		this.jmenuAide.add(this.itemAPropos = new JMenuItem("A propos"));
		
		//Panels
		this.tapisPanel.setBackground(new Color(255,0,0));
		this.mainPanel.setBackground(new Color(0,255,0));
		this.infoPartiePanel.setBackground(new Color(255,0,255));
		
		this.tapisPanel.add(new CartePanel (this.matriceCarte),BorderLayout.CENTER);
		this.infoPartiePanel.add(new CartePanel(new Carte(1, 12), this.matriceCarte), BorderLayout.CENTER);
		this.mainPanel.add(new CartePanel(new Carte(0, 13), this.matriceCarte), BorderLayout.CENTER);
		this.mainPanel.add(new CartePanel(new Carte(1, 14), this.matriceCarte), BorderLayout.CENTER);
		this.mainPanel.add(new CartePanel(new Carte(2, 9), this.matriceCarte), BorderLayout.CENTER);
		this.mainPanel.add(new CartePanel(new Carte(3, 10), this.matriceCarte), BorderLayout.CENTER);

		
		this.window.pack();
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
			JEditorPane ep = new JEditorPane("text/html","<b>Bataille Norvégienne</b><br><br>Par Charlélie Borella et Arnaud Pecoraro<br>Automne 2014 - UV LO02");
			ep.setEditable(false);
			ep.setPreferredSize(new Dimension(300,100));
			JScrollPane sp = new JScrollPane(ep);
			JOptionPane.showMessageDialog(this.window, sp, "A propos", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == this.itemNouvellePartie){
			System.out.println("Nouvelle Partie");
			
			FenetreConfiguration fc = new FenetreConfiguration();
			fc.setVisible(true);

		}
		else if (e.getSource() == this.itemRegles){
			
			System.out.println("Règles du jeu");
			
			FenetreReglesJeu ed = new FenetreReglesJeu();
			ed.setVisible(true);
			
		}
	}
	
	private void initialiserMatriceCarte(){
		try {
			this.matriceCarte = ImageIO.read(new File("ressources/cards.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
