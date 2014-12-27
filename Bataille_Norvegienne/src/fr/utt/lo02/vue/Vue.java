package fr.utt.lo02.vue;

import java.util.Observable;
import java.util.Observer;

public class Vue implements Observer {
	//private VueGraphique vueGraphique;
	
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		VueGraphique vg = new VueGraphique();
	}

	public void update(Observable o, Object arg) {
		
	}

}
