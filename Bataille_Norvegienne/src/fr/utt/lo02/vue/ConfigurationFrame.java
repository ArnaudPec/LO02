package fr.utt.lo02.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Fenêtre permettant de configurer une partie
 */
public class ConfigurationFrame extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel;
	
	public ConfigurationFrame() {

		this.setTitle("Nouvelle partie");
		this.setSize(new Dimension(400, 300));
		jPanel = (JPanel) this.getContentPane();
		this.setLocationRelativeTo(null);

		
		JButton button = new JButton("Ok");
		
		this.jPanel.add(button, BorderLayout.SOUTH);
		
		//this.pack();
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		
	}

	
}
