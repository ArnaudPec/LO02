package fr.utt.lo02.carte;

public class Carte {

	/**
	 * Repr√©sente la couleur de la carte [coeur=0, pique=1, trefle=2, carreau=3]
	 */
	private int couleur;
	
	/**
	 * Repr√©sente la valeur de la carte [2=2, .. 10=10, valet=11, dame=12, roi=13, as=14]
	 */
	private int valeur;
	
	
	/**
	 * Repr√©sente la visibilit√© de la carte  [true=retourn√©, false=cach√©]
	 */
	private boolean visibilite;
	
	/**
	 * Constructeur de carte, permet de cr√©er une carte en lui attribuant une couleur et une valeur.
	 * @param couleur de type Integer [coeur=0, pique=1, trefle=2, carreau=3]
	 * @param valeur de type Integer [2=2, .. 10=10, valet=11, dame=12, roi=13, as=14]
	 */
	public Carte(int couleur, int valeur){
		this.couleur = couleur;
		this.valeur = valeur;
		this.visibilite = false;
	}
	
	
	/**
	 * Getter de couleur
	 * @return La couleur de la carte
	 */
	public int getCouleur(){
		return this.couleur;
	}
	
	/**
	 * Getter de valeur
	 * @return La valeur de la carte
	 */
	public int getValeur(){
		return this.getCouleur();
	}
	
	/**
	 * Getter de visibilite
	 * @return Ma visibilite de ma carte [true=retourne, false=cache]
	 */
	public boolean getVisibilite(){
		return this.visibilite;
	}

	/**
	 * Setter de couleur
	 * @param couleur [coeur=0, pique=1, tr√®fle=2, carreau=3]
	 */
	public void setCouleur(int couleur){
		this.couleur = couleur;
	}

	/**
	 * Setter de valeur
	 * @param valeur [2=2, .. 10=10, valet=11, dame=12, roi=13, as=14]
	 */
	public void setValeur(int valeur){
		this.valeur = valeur;
	}
	
	/**
	 * Setter de visibilit√©
	 * @param visibilite [true=retourn√©, false=cach√©]
	 */
	public void setVisibilite(boolean visibilite){
		this.visibilite = visibilite;
	}

	/**
	 * M√©thode permettant de d√©terminer si la carte √† une fonction sp√©ciale
	 * @return Un boolean [true=sp√©ciale, false=pas sp√©ciale]
	 */
	public boolean estSpeciale(){
		boolean resultat = false;
		switch (this.valeur) {
		case 2:
			resultat = true;
			break;
		case 7:
			resultat = true;
			break;
		case 8:
			resultat = true;
			break;
		case 10:
			resultat = true;
			break;
		case 14:
			resultat = true;
			break;
		default:
			resultat = false;
			break;
		}
		return resultat;
	}
	
	
	/**
	 * M√©thode permettant de comparer deux cartes
	 * @param carte 
	 * @return Un boolean [true=sup√©rieur ou √©gale, false=inf√©rieur]
	 */
	public boolean estSuperieurOuEgal(Carte carte){
		boolean resultat = false;
		if(this.valeur >= carte.getValeur())
		{
			resultat = true;
		}
		return resultat;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String 	resultat = " —————️ " + "\n";
				resultat+= "|"+ this.couleur + "    |" + "\n"; 
				resultat+= "|  " + this.valeur + "  |" + "\n";
				resultat+= "|    " + this.couleur + "|" + "\n";
				resultat+=" ————— " + "\n";
		
		return resultat;
	}
	
	public static void main(String[]args){
		Carte carte = new Carte(0,2);
		System.out.println(carte.toString());
		carte.toString();
	}
}


