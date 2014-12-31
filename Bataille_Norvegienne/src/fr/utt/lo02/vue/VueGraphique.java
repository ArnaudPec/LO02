package fr.utt.lo02.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

	private MainScrollPane mainPanel;
	private JPanel infoPartiePanel;
	private JPanel tapisPanel;
	private BufferedImage matriceCarte;
	
	public static final String[] Strategies = { "Aléatoire", "Offensive", "Equilibrée"};

	public VueGraphique (){

		this.window = new JFrame("Bataille Norvégienne");
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialiserMatriceCarte();
		this.initialiserFenetre();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.itemQuitter){
			System.exit(0);
		}
		else if (e.getSource() == this.itemAPropos){
			System.out.println("A propos");
			this.afficherApropos();
		}
		else if (e.getSource() == this.itemNouvellePartie){
			System.out.println("Nouvelle Partie");
			this.initialiserPartie();
		}
		else if (e.getSource() == this.itemRegles){

			System.out.println("Règles du jeu");
			this.afficherRegles();
			
		}
	}

	private void initialiserPartie() {
		
		this.ajouterJoueurs();
		this.dessinerJeu();	
		
		//test
		//this.choisirJoueur();
	}

	private void ajouterJoueurs() {
		
		int nbJoueur = Integer.parseInt(JOptionPane.showInputDialog("Nombre total de joueurs ?"));
		int nbJoueurHumain = Integer.parseInt(JOptionPane.showInputDialog("Nombre de joueurs humains ?"));
		int nbJoueurIA = nbJoueur-nbJoueurHumain;
		System.out.println(nbJoueurIA);
		
		for (int i = 0; i < nbJoueurHumain; i++) {
			@SuppressWarnings("unused")
			String nom = JOptionPane.showInputDialog("Veuillez entrer le nom du joueur " +i);
		}
		
		for (int i = 0; i < nbJoueurIA; i++) {
			@SuppressWarnings("unused")
			String strategieChoisie = (String) JOptionPane.showInputDialog(
					this.window,
					"Veuillez choisir une stratégie pour le joueur IA " + i,
					"Stratégie", JOptionPane.QUESTION_MESSAGE, null,
					Strategies, Strategies[0]);
		}
		

	}

	private void choisirJoueur() {
		
		@SuppressWarnings("unused")
		String strategieChoisie = (String) JOptionPane.showInputDialog(
				this.window,
				"Veuillez choisir un joueur ",
				"Choisir un joueur", JOptionPane.QUESTION_MESSAGE, null,
				Strategies, Strategies[0]);
			
		
	}

	private void afficherApropos() {
		
		JEditorPane ep = new JEditorPane(
				"text/html",
				"<b>Bataille Norvégienne</b><br><br>Par Charlélie Borella et Arnaud Pecoraro<br>Automne 2014 - UV LO02");
		
		ep.setEditable(false);
		ep.setPreferredSize(new Dimension(300,100));
		JScrollPane sp = new JScrollPane(ep);
		JOptionPane.showMessageDialog(this.window, sp, "A propos", JOptionPane.INFORMATION_MESSAGE);		
	}

	private void afficherRegles() {
		File f = new File("ressources/regles.html");
		
		try {
			URL url = new URL("file://" + f.getAbsolutePath());
			JEditorPane ep = new JEditorPane(url);
			ep.setEditable(false);
			ep.setPreferredSize(new Dimension(700,500));
			JScrollPane sp = new JScrollPane(ep);
			JOptionPane.showMessageDialog(this.window, sp, "Règles du jeu", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}

	private void initialiserMatriceCarte(){
		try {
			this.matriceCarte = ImageIO.read(new File("ressources/cards.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initialiserFenetre(){
		
				// Fenêtre
				this.window.setResizable(true);
				//this.window.setPreferredSize(new Dimension(800,600));

				// Container

				this.container = new ImagePanel(new BorderLayout());  
				this.window.setContentPane(this.container);
				this.container.setBackground(new Color(0,90,50));

				// Menu
				this.menuBar = new JMenuBar();
				this.menuBar.add(this.jmenuPartie = new JMenu("Partie"));
				this.menuBar.add(this.jmenuAide = new JMenu("Aide"));
				this.jmenuPartie.add(this.itemNouvellePartie = new JMenuItem("Nouvelle Partie"));
				this.jmenuPartie.add(this.itemQuitter = new JMenuItem("Quitter"));
				this.jmenuAide.add(this.itemRegles = new JMenuItem("Règles du jeu"));
				this.jmenuAide.add(this.itemAPropos = new JMenuItem("A propos"));

				this.container.add(this.menuBar, BorderLayout.NORTH);
				
				//this.window.pack();
				this.window.setSize(new Dimension(800,600));
				this.window.setLocationRelativeTo(null);	
				
				// Actions
				this.itemQuitter.addActionListener(this);
				this.itemAPropos.addActionListener(this);
				this.itemNouvellePartie.addActionListener(this);
				this.itemRegles.addActionListener(this);
				
	}

	private void dessinerJeu(){
		
		this.initialiserFenetre();

		this.container.add(this.tapisPanel = new TapisPanel(matriceCarte), BorderLayout.CENTER);
		this.container.add(this.infoPartiePanel = new InfoPartiePanel(), BorderLayout.EAST);
		this.container.add(this.mainPanel = new MainScrollPane(this.matriceCarte), BorderLayout.SOUTH);

		
		//Panels
		this.mainPanel.setBackground(new Color(0,90,50));
		this.infoPartiePanel.setBackground(new Color(0,90,50));
		this.tapisPanel.setBackground(new Color(0,90,50));

		this.window.pack();
	}
	
}
