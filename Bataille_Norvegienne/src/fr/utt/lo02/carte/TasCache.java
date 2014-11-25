package fr.utt.lo02.carte;

import java.util.LinkedList;

public class TasCache extends Tas {

	public TasCache() {
		this.listeCartes = new LinkedList<Carte>();
	}

	public Carte prendreCarte(){
		return this.listeCartes.pollFirst();
	}

}
