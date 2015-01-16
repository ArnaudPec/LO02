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

import fr.utt.lo02.partie.Controleur;
import fr.utt.lo02.partie.Partie;

/**
 * Classe matérialisant la fenêtre principale du jeu la Bataille Norvégienne.
 *
 */
public class VueGraphique extends JFrame implements Observer, ActionListener {

	private static final long serialVersionUID = 1L;
	protected Partie partie;
	protected Controleur controleur;
	
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
	
	private JButton envoyer;
	private Color tapisColor;
	private boolean running;
	
	public static final String[] Strategies = { "Aléatoire", "Offensive (1 Max)", "Equilibrée"};
	
	public VueGraphique (Partie partie, final Controleur partieControleur){

		this.window = new JFrame("Bataille Norvégienne");
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialiserMatriceCarte();
		this.initialiserFenetre();
		this.partie = partie;
		this.controleur = partieControleur;
		this.running=false;
		this.partie.addObserver(this);
	}
	
	public JButton getEnvoyer(){
		return this.envoyer;
	}
	
	public ImagePanel getImagePanel(){
		return this.container;
	}

	/** Mééthode de gestion des événements, associe un événement (clic) à une action
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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
		else if(e.getSource() ==this.envoyer){
			this.actionEnvoyer();
		}
	}

	/**
	 * Méthode de gestion du bouton envoyer
	 */
	public void actionEnvoyer() {
		
		boolean envoyer = this.controleur.envoyerSelection();
		if(!envoyer) JOptionPane.showMessageDialog(this.window, "Mauvaise sélection ! Recommencez", "Alerte", JOptionPane.WARNING_MESSAGE );
		this.dessinerJeu();
	}

	/**
	 * Méthode permettant l'initialisation de la partie : ajouter les joueurs, dessiner le jeu
	 */
	private void initialiserPartie() {
		this.ajouterJoueurs();
		if(this.partie.getNbJoueurs()>5)this.partie.getPioche().ajouterUnSecondJeuDeCarte();
		this.partie.getPioche().melanger();
		this.partie.getPioche().distribuerCarte(this.partie);
		this.dessinerJeu();	
		this.changerCartes();
		this.itemNouvellePartie.setEnabled(false);
		this.running=true;
		
	}

	/**
	 * Méthode permettant l'ajout et la configuration des joueurs dans la partie
	 */
	public void ajouterJoueurs() {
		
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
			if(strategieChoisie.equals(Strategies[2])) this.partie.creationIA(i+1, 2);
			
			else if (strategieChoisie.equals(Strategies[1]) && !this.partie.verifierPresenceIaOffensive()) this.partie.creationIA(i+1, 1);
			
			else if (strategieChoisie.equals(Strategies[1])) {
				JOptionPane.showMessageDialog(this.window, "Nombre maximum d'IA offensive atteint, une IA aléatoire a été ajoutée à la place.", "Alerte", JOptionPane.WARNING_MESSAGE );
				this.partie.creationIA(i+1, 0);
			}
			else this.partie.creationIA(i+1, 0);
		}
	}

	/**
	 * Méthode permettant graphiquement le choix d'un joueur dans le cas d'une pose d'un as
	 * @return le numéro du joueur choisi
	 */
	public int choisirJoueur(){

		String[] listeJoueur = this.partie.getListeNomsJoueurs();
		String joueurChoisi = new String();
		
		do
		{
			joueurChoisi = (String) JOptionPane.showInputDialog(
					this.window,
					"Veuillez choisir un joueur ",
					"Choisir un joueur", 
					JOptionPane.QUESTION_MESSAGE, 
					null, listeJoueur, 
					listeJoueur[0]);
			
			if(joueurChoisi == this.partie.getHumain().getNom()){
				JOptionPane.showMessageDialog(this.window, 
						"Vous devez désigner un autre joueur !", 
						"Alerte", 
						JOptionPane.WARNING_MESSAGE );
			}
			
		}while(joueurChoisi == this.partie.getHumain().getNom());
		
		return this.partie.getJoueurInt(joueurChoisi);
	}
	
	/**
	 * Méthode permettant l'échange des cartes en début de partie, créé un objet de type EchangerCarteFrame.
	 * @see EchangerCarteFrame
	 */
	public void changerCartes(){
		 int reponse = JOptionPane.showConfirmDialog(null, "Souhaitez vous échanger vos cartes ?", "Echanger cartes", JOptionPane.YES_NO_OPTION);
	        if (reponse == JOptionPane.YES_OPTION) {
				EchangerCarteFrame ec = new EchangerCarteFrame(this.partie.getHumain(), this.controleur, this.matriceCarte);
	        	ec.setLocationRelativeTo(this.window);
	        	this.dessinerJeu();
	        }
	}

	/**
	 *  Méthode de gestion de l'item Apropos. Affichage d'information.
	 */
	public void afficherApropos() {
		
		JEditorPane ep = new JEditorPane(
				"text/html",
				"<b>Bataille Norvégienne</b><br><br>Par Charlélie Borella et Arnaud Pecoraro<br>Automne 2014 - UV LO02");
		
		ep.setEditable(false);
		ep.setPreferredSize(new Dimension(300,100));
		JScrollPane sp = new JScrollPane(ep);
		JOptionPane.showMessageDialog(this.window, sp, "A propos", JOptionPane.INFORMATION_MESSAGE);	
	}

	/**
	 *  Méthode de gestion de l'item Règles. Affichage des règles du jeu.
	 */
	public void afficherRegles() {
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

	/**
	 * Méthode permettant de charger en mémoire la matrice contenant les images des cartes du jeu.
	 * Appelée une seule fois en début de programme. La matrice est stockée pour ne pas ralentir l'éxecution à chaque
	 * affichage de carte.
	 */
	public void initialiserMatriceCarte(){
		try {
			this.matriceCarte = ImageIO.read(new File("ressources/cards.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant l'initialisation de la fenêtre du jeu et des éléments qui y sont contenus
	 */
	public void initialiserFenetre(){
		
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
				if(running) this.itemNouvellePartie.setEnabled(false);
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
				this.envoyer.addActionListener(this);
				
	}

	/**
	 * Méthode permettant de dessiner le contenu du jeu qui évolue à chaque tour. Le tapis, la main du joueur et les
	 * informations de partie
	 */
	public void dessinerJeu(){
		
		this.initialiserFenetre();
		
		this.container.add(this.tapisPanel = new TapisPanel(matriceCarte, this.controleur), BorderLayout.WEST);
		this.container.add(this.infoPartiePanel = new InfoPartiePanel(this.partie), BorderLayout.EAST);
		this.container.add(this.mainPanel = new MainScrollPane(this.matriceCarte, this.partie.getHumain().getMainJoueur(), controleur), BorderLayout.SOUTH);
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

	
	/**
	 * Notification de victoire
	 */
	public void afficherVictoire(){
		JOptionPane.showMessageDialog(this.window, "Vous avez gagné !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	/**
	 * Notification de défaite
	 */
	public void afficherDefaite(){
		JOptionPane.showMessageDialog(this.window, "Vous avez perdu !", "Defaire", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	/**
	 * Notification de prise de tapis
	 */
	public void notifierPriseTapis(){
		JOptionPane.showMessageDialog(this.window, "Vous ne pouvez pas jouer,vous ramassez le tapis !", "Cocogne", JOptionPane.INFORMATION_MESSAGE);
	}
}
