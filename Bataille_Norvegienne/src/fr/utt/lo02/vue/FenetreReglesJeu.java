package fr.utt.lo02.vue;

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Classe servant à afficher la page web contenant les règles du jeu... Provisoire...
 *
 */
public class FenetreReglesJeu extends JFrame implements HyperlinkListener {

	private static final long serialVersionUID = 1L;
	private JEditorPane html;

	public FenetreReglesJeu() {

		setTitle("Règles du Jeu");
		setSize(800, 500);
		setBackground(Color.gray);
		getContentPane().setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.CENTER);

		try {
			URL url = new URL(
					"https://fr.wikipedia.org/wiki/Bataille_norv%C3%A9gienne");
			html = new JEditorPane(url);
			html.setEditable(false);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.getViewport().add(html, BorderLayout.CENTER);

			topPanel.add(scrollPane, BorderLayout.CENTER);

			html.addHyperlinkListener(this);
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}

	public void hyperlinkUpdate(HyperlinkEvent event) {
		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			Cursor cursor = html.getCursor();
			Cursor waitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
			html.setCursor(waitCursor);
			SwingUtilities.invokeLater(new PageLoader(html, event.getURL(),
					cursor));
		}
	}
}
