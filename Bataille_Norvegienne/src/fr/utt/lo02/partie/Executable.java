package fr.utt.lo02.partie;

import fr.utt.lo02.vue.VueGraphique;

public class Executable {

	public static void main(String[] args) {

		Partie partie = Partie.getInstancePartie();
		PartieControleur partieControleur = new PartieControleur(partie);
		VueGraphique vueGraphique = new VueGraphique(partie, partieControleur);
		//partieControleur.addVue(vueGraphique);
	}
	
}
