package fr.utt.lo02.vue;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.utt.lo02.partie.Controleur;

/**
 * Classe héritant de JPanel, @see JPanel 
 * Sa spécificité est de pouvoir afficher une image en tant que background.
 *
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	

	protected BufferedImage image;
	protected Controleur partieControleur;
	
	public ImagePanel(Controleur partieControleur) {
		super();
		this.partieControleur = partieControleur;
	}

	public ImagePanel(String path) {
		try {
			this.image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImagePanel(LayoutManager arg0, String path) {
		super(arg0);
		try {
			this.image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImagePanel(LayoutManager arg0) {
		super(arg0);
		
	}

	/**
	 * Méthode permettant d'afficher une image en fond de composant
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.image, 0, 0, null);
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
