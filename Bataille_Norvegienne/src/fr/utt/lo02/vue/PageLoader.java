package fr.utt.lo02.vue;

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

/**
 * Sert à charger les pages web... Provisoire...
 *
 */
public class PageLoader implements Runnable {
	private JEditorPane html;
	private URL url;
	private Cursor cursor;

	PageLoader(JEditorPane html, URL url, Cursor cursor) {
		this.html = html;
		this.url = url;
		this.cursor = cursor;
	}

	public void run() {
		if (url == null) {
			html.setCursor(cursor);
			Container parent = html.getParent();
			parent.repaint();
		} else {
			Document doc = html.getDocument();
			try {
				html.setPage(url);
			} catch (IOException ioe) {
				html.setDocument(doc);
			} finally {
				url = null;
				SwingUtilities.invokeLater(this);
			}
		}
	}
}