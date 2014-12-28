package fr.utt.lo02.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Fenêtre permettant de configurer une partie
 *
 */
public class FenetreConfiguration extends JFrame  {

	private static final long serialVersionUID = 1L;

	public FenetreConfiguration() {

		this.setTitle("Nouvelle partie");
		this.setSize(700, 500);
		this.getContentPane().setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);

		JPanel topPanel = new JPanel();
		JButton button = new JButton("Ok");
		
		this.getContentPane().add(topPanel, BorderLayout.CENTER);
		this.getContentPane().add(button, BorderLayout.SOUTH);
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		
	}

	
}
