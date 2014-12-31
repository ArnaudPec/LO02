package fr.utt.lo02.vue;

import fr.utt.lo02.partie.Partie;

public class VueConsole {

	public VueConsole() {
		Partie partie = Partie.getInstancePartie();
		//Partie partie = getInstancePartie();
		partie.initialisationPartie(partie);
		partie.lancerPartie();
	}

	
}
