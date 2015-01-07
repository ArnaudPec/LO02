package fr.utt.lo02.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.utt.lo02.carte.Carte;
import fr.utt.lo02.joueur.Humain;
import fr.utt.lo02.partie.Controleur;

public class EchangerCarteFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage matriceCartes;
	
	private Controleur controleur;
	private Humain joueur;
	private LinkedList<Carte> carteJoueur;
	
	private JScrollPane jsp;
	private JPanel cartes;
	
	private JButton envoyer;
	private JLabel jlabel;
	private Container container;
	
	public EchangerCarteFrame(Humain joueur, Controleur controleur, BufferedImage matriceCartes){
		
		this.matriceCartes = matriceCartes;
		this.controleur = controleur;
		this.joueur = joueur;
		
		this.setTitle("Choisir Cartes");
		this.setPreferredSize(new Dimension(600,300));
		this.setVisible(true);
		
		this.container = this.getContentPane();
		this.envoyer = new JButton("Envoyer");
		this.jlabel = new JLabel("Veuillez choisir les 3 cartes qui composeront votre main");
		
		this.envoyer.addActionListener(this);
	
		this.dessiner();
		
		this.pack();
		
		this.setLocationRelativeTo(null);

		
	}
	
	private void ajouterCartesJoueur(){
		this.cartes = new JPanel();
		
		this.carteJoueur = new LinkedList<Carte>();
		this.carteJoueur.addAll(this.joueur.getMainJoueur().getListeCartes());
		this.carteJoueur.addAll(this.joueur.getTasVisible().getListeCartes());
		
		this.cartes.setBackground(new Color(0,90,50));
		for (Iterator<Carte> iterator = this.joueur.getMainJoueur().getListeCartes().iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			this.cartes.add(new CartePanel(carte, this.matriceCartes, true, 0.5, this.controleur));
		}
		
		for (Iterator<Carte> iterator = this.joueur.getTasVisible().getListeCartes().iterator(); iterator.hasNext();) {
			Carte carte = (Carte) iterator.next();
			this.cartes.add(new CartePanel(carte, this.matriceCartes, true, 0.5, this.controleur));
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.envoyer){
			this.actionEnvoyer();
		}
	}
	
	public void actionEnvoyer() {
		
		boolean envoyer = this.controleur.envoyerSelectionEchange();
		if(!envoyer) {
			JOptionPane.showMessageDialog(this, "Mauvaise sélection ! Recommencez", "Alerte", JOptionPane.WARNING_MESSAGE );
		}
		
		else {
			this.controleur.getVueGraphique().dessinerJeu();
			this.controleur.envoyerTasVisibleEchange(this.carteJoueur);
			
			System.out.println(this.joueur.getMainJoueur());
			System.out.println(this.joueur.getTasVisible());
			this.dispose();
		}
	}
	
	public void dessiner(){
		this.ajouterCartesJoueur();
		this.jsp = new JScrollPane(this.cartes);
		this.jsp.setBackground(new Color(0,90,50));
		
		this.container.add(this.jlabel, BorderLayout.NORTH);
		this.container.add(this.jsp, BorderLayout.CENTER);
		this.container.add(this.envoyer, BorderLayout.SOUTH);
	}
}
