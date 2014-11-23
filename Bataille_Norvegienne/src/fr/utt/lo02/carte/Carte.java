package fr.utt.lo02.carte;

public class Carte {

	private int couleur;
	private int valeur;
	private boolean visibilite;
	
	
	public Carte(int couleur, int valeur)
	{
		this.couleur = couleur;
		this.valeur = valeur;
		this.visibilite = false;
	}
	
	public int getCouleur()
	{
		return this.couleur;
	}
	
	public int getValeur()
	{
		return this.getCouleur();
	}
	
	public boolean getVisibilite()
	{
		return this.visibilite;
	}

	public void setCouleur(int couleur)
	{
		this.couleur = couleur;
	}

	public void setValeur(int valeur)
	{
		this.valeur = valeur;
	}
	
	public void setVisibilite(boolean visibilie)
	{
		this.visibilite = visibilie;
	}

	public boolean estSpeciale()
	{
		boolean resultat = false;
		switch (this.valeur) {
		case 8:
			resultat = true;
			break;

		default:
			break;
		}
		return resultat;
	}
	
	public boolean estSuperieur(Carte carte)
	{
		
		
	}
	
	
}


