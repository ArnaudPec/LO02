package fr.utt.lo02.carte;

import java.util.LinkedList;

public class MainJoueur extends Tas{

	public MainJoueur() {
		this.listeCartes = new LinkedList<Carte>();
	}
	
<<<<<<< HEAD
	public Carte prendreCarte(int position){
=======
	/**
	 * 
	 * Tu pourrais m'expliquer l'objectif de cette méthode stp ?
	 * Je ne comprends pas ce qu'elle fait : elle prend une carte à quel objet ?
	 * En lisant le code j'ai l'impression qu'il s'agit plutôt de poser une carte de la main puisqu'elle retourne une carte et que tu spécifies la position de la carte à "poser"
	 * 
	 * @param position
	 * @return
	 */
	public Carte prendreCarte(int position)
	{
>>>>>>> FETCH_HEAD
		Carte carte = this.listeCartes.get(position);
		this.listeCartes.remove(position);
		
		return carte;
	}
	
	public Carte getCarte(int position){
		return this.listeCartes.get(position);
	}
	
	@Override
	public String toString(){
		
		String resultat = "Main \n\n";
		String numCarte = "";
		String bordureDessus ="";
		String dessus = "";
		String milieu = "";
		String bas = "";
		String bordureDessous = "";
		
		for (int i = 0; i < this.listeCartes.size(); i++) {
			
			numCarte+= "  n°" + i + "    ";
			bordureDessus+= " —————️ " + "  ";
			dessus+= "|"+ this.listeCartes.get(i).getCouleurAffichage() + "    |  ";
			milieu+= "|  " + this.listeCartes.get(i).getValeurAffichage() + "  |  ";
			bas+= "|    "+ this.listeCartes.get(i).getCouleurAffichage() + "|  ";
			bordureDessous+= " —————️ " + "  ";		
		}
		
		resultat += numCarte + "\n" + bordureDessus + "\n" + dessus+ "\n" + milieu + "\n" + bas + "\n" + bordureDessous + "\n";
				
		return resultat;
	}
	
	public static void main(String[] args) {
		
		MainJoueur main = new MainJoueur();
		
		Carte carte = new Carte(0,2);
		Carte carte2 = new Carte(1,11);
		Carte carte3 = new Carte(2,12);
		Carte carte4 = new Carte(3,14);
		main.listeCartes.add(carte);
		main.listeCartes.add(carte2);
		main.listeCartes.add(carte3);
		main.listeCartes.add(carte4);

		System.out.println(main.toString());
		
	}
	
}
