package fr.utt.lo02.partie;

import fr.utt.lo02.vue.VueGraphique;

public class Executable {

	public static void main(String[] args) {

		Partie partie = Partie.getInstancePartie();
		Controleur controleur = new Controleur(partie);
		VueGraphique vueGraphique = new VueGraphique(partie, controleur);
		controleur.addVue(vueGraphique);
	}
	
}
