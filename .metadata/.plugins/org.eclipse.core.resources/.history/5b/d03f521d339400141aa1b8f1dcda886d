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
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.joueur.Joueur;
import fr.utt.lo02.partie.Partie;
import fr.utt.lo02.partie.PartieControleur;

public class VueGraphique extends JFrame implements Observer, ActionListener {

	private static final long serialVersionUID = 1L;
	protected Partie partie;
	protected PartieControleur partieControleur;
	
	private JFrame window;
	private JMenuBar menuBar;
	private ImagePanel container;

	private JMenu jmenuPartie;
	private JMenu jmenuAide;
	private JMenuItem itemNouvellePartie;
	private JMenuItem itemQuitter;
	private JMenuItem itemAPropos;
	private JMenuItem itemRegles;

	private JMenuItem itemEnvoyer;
	
	private MainScrollPane mainPanel;
	private JPanel infoPartiePanel;
	private JPanel tapisPanel;
	private BufferedImage matriceCarte;
	
	private JButton envoyer;
	private Color tapisColor;
	
	public static final String[] Strategies = { "Aléatoire", "Offensive (1 Max)", "Equilibrée"};
	
	public VueGraphique (Partie partie, final PartieControleur partieControleur){

		this.window = new JFrame("Bataille Norvégienne");
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialiserMatriceCarte();
		this.initialiserFenetre();
		this.partie = partie;
		this.partieControleur = partieControleur;
		this.partie.addObserver(this);
	}
	
	public JButton getEnvoyer(){
		return this.envoyer;
	}
	
	public ImagePanel getImagePanel(){
		return this.container;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.itemQuitter){
			System.exit(0);
		}
		else if (e.getSource() == this.itemAPropos){
			this.afficherApropos();
		}
		else if (e.getSource() == this.itemNouvellePartie){
			this.initialiserPartie();
		}
		else if (e.getSource() == this.itemRegles){
			this.afficherRegles();
		}
		else if(e.getSource() ==this.envoyer || e.getSource()==this.itemEnvoyer){
			this.actionEnvoyer();
		}
	}

	private void actionEnvoyer() {
		boolean envoyer = this.partieControleur.envoyerSelection();
		if(!envoyer) JOptionPane.showMessageDialog(this.window, "Mauvaise sélection ! Recommencez", "Alerte", JOptionPane.WARNING_MESSAGE );
		this.dessinerJeu();
	}

	private void initialiserPartie() {
		this.ajouterJoueurs();
		if(this.partie.getNbJoueurs()>5)this.partie.getPioche().ajouterUnSecondJeuDeCarte();
		this.partie.getPioche().melanger();
		this.partie.getPioche().distribuerCarte(this.partie);
		
		this.dessinerJeu();	
		this.changerCartes();
	}

	private void ajouterJoueurs() {
		
		int nbJoueur = 0;
		while (nbJoueur <2 || nbJoueur > 11) nbJoueur = Integer.parseInt(JOptionPane.showInputDialog("Nombre total de joueurs ? (max : 11)"));
		
		int nbJoueurIA = nbJoueur -1;		
		
		String nom = JOptionPane.showInputDialog("Veuillez entrer le nom du joueur ");
		this.partie.creationHumain(nom);
		
		
		for (int i = 0; i < nbJoueurIA; i++) {
			String strategieChoisie = (String) JOptionPane.showInputDialog(
					this.window,
					"Veuillez choisir une stratégie pour le joueur IA " + i,
					"Stratégie", JOptionPane.QUESTION_MESSAGE, null,
					Strategies, Strategies[0]);
			if(strategieChoisie.equals(Strategies[2])) this.partie.creationIA(i, 2);
			
			else if (strategieChoisie.equals(Strategies[1]) && !this.partie.verifierPresenceIaOffensive()) this.partie.creationIA(i, 1);
			
			else if (strategieChoisie.equals(Strategies[1])) {
				JOptionPane.showMessageDialog(this.window, "Nombre maximum d'IA offensive atteint, une IA aléatoire a été ajoutée à la place.", "Alerte", JOptionPane.WARNING_MESSAGE );
				this.partie.creationIA(i, 0);
			}
			else this.partie.creationIA(i, 0);
		}
		
	}

	private Joueur choisirJoueur() {

		String[] listeJoueur = this.partie.getListeNomsJoueurs();
		
		String joueurChoisi = (String) JOptionPane.showInputDialog(
				this.window,
				"Veuillez choisir un joueur ",
				"Choisir un joueur", 
				JOptionPane.QUESTION_MESSAGE, 
				null, listeJoueur, 
				listeJoueur[0]);
		
		return this.partie.getJoueurNom(joueurChoisi);
	}
	
	public void changerCartes(){
		 int reponse = JOptionPane.showConfirmDialog(null, "Souhaitez vous échanger vos cartes ?", "Echanger cartes", JOptionPane.YES_NO_OPTION);
	        if (reponse == JOptionPane.YES_OPTION) {
	          JOptionPane.showMessageDialog(null, "Attention à la marche");
	        }
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
				this.tapisColor=new Color(0,90,50);

				// Container

				this.container = new ImagePanel(new BorderLayout());  
				this.window.setContentPane(this.container);
				this.container.setBackground(this.tapisColor);

				// Menu
				this.menuBar = new JMenuBar();
				this.menuBar.add(this.jmenuPartie = new JMenu("Partie"));
				this.menuBar.add(this.jmenuAide = new JMenu("Aide"));
				this.jmenuPartie.add(this.itemNouvellePartie = new JMenuItem("Nouvelle Partie"));
				this.jmenuPartie.add(this.itemQuitter = new JMenuItem("Quitter"));
				this.jmenuAide.add(this.itemRegles = new JMenuItem("Règles du jeu"));
				this.jmenuAide.add(this.itemAPropos = new JMenuItem("A propos"));
				this.menuBar.add(this.itemEnvoyer = new JMenuItem("Envoyer"));
				this.envoyer = new JButton("Envoyer");
				
				this.container.add(this.menuBar, BorderLayout.NORTH);
				
				//this.window.pack();
				this.window.setSize(new Dimension(800,600));
				this.window.setLocationRelativeTo(null);	
				
				// Actions
				this.itemQuitter.addActionListener(this);
				this.itemAPropos.addActionListener(this);
				this.itemNouvellePartie.addActionListener(this);
				this.itemRegles.addActionListener(this);
				this.itemEnvoyer.addActionListener(this);
				this.envoyer.addActionListener(this);
	}

	private void dessinerJeu(){
		
		this.initialiserFenetre();
		
		this.container.add(this.tapisPanel = new TapisPanel(matriceCarte, this.partieControleur), BorderLayout.WEST);
		this.container.add(this.infoPartiePanel = new InfoPartiePanel(this.partie), BorderLayout.EAST);
		this.container.add(this.mainPanel = new MainScrollPane(this.matriceCarte, this.partie.getHumain().getMainJoueur(), partieControleur), BorderLayout.SOUTH);
		this.infoPartiePanel.add(this.envoyer, BorderLayout.CENTER);
		
		//Panels
		this.mainPanel.setBackground(this.tapisColor);
		this.infoPartiePanel.setBackground(this.tapisColor);
		this.tapisPanel.setBackground(this.tapisColor);

		this.window.pack();
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.dessinerJeu();
	}
	
}
