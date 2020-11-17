package game;

import java.util.ArrayList;
import java.util.LinkedList;

// import java.util.ArrayList;

public class PlusCourtChemin {
	// private ArrayList<Sommet> sommets;
	private LinkedList<Sommet> chemin;
	private Carte carte;
	private Case goal;
	private Sommet depart;
	private double tempsOptim;
	private Robot robot;
	
	public PlusCourtChemin(Robot robot, Case ca, Carte carte) {
		// Initialisation
		// sommets = new ArrayList<Sommet>();
		/*for(int i = 0; i < carte.getNbLignes(); i++) {
			for(int j = 0; j < carte.getNbColonnes(); j++) {
				Case temp = carte.getCase(i, j);
				if(!temp.equals(ca))
					sommets.add(new Sommet(temp, Double.MAX_VALUE));
			}
		}*/
		// sommets.add(new Sommet(ca, 0));
		this.chemin = new LinkedList<>();
		this.depart = new Sommet(robot.getPosition(), 0);
		this.carte = carte;
		this.goal = ca;
		this.robot = robot;
		plusCourtChemin();
	}
	
	private void plusCourtChemin() {
		ArrayList<Sommet> ouverts = new ArrayList<>();
		ArrayList<Sommet> fermes = new ArrayList<>();
		System.out.println("depart position " + depart.getPosition());
		ouverts.add(this.depart);
		
		while(ouverts.size() != 0) {
			Sommet courant = getSommetProche(ouverts);
			ouverts.remove(courant);
			if(courant.getPosition().equals(goal)) {
				fermes.add(courant);
				constitueChemin(courant);
				this.tempsOptim = courant.getTemps();
				break;
			}
			for(Sommet s: courant.getVoisins(this.carte, this.robot)) {
				
				/*if(!fermes.contains(s)) {
					double distance = Carte.getDistanceEntreCase();
					// calcul du temps pour le robot de se deplacer de sa case vers une case voisine
					double temp = distance / ((this.robot.getVitesse(s.getPosition().getNature())*Math.pow(10,3))/60);
					if(courant.getTemps() + temp < s.getTemps()) {
						// le parent du voisin c'est le sommet courant
						s.setParent(courant);
						s.setTemps(temp + courant.getTemps());
					}
					System.out.println("voisin position " + s.getPosition() + " parent " + s.getParent().getPosition());
					ouverts.add(s);
				}*/
				double distance = Carte.getDistanceEntreCase();
				// calcul du temps pour le robot de se deplacer de sa case vers une case voisine
				double temp = distance / ((this.robot.getVitesse(s.getPosition().getNature())*Math.pow(10,3))/60);
				updateTemps(courant, s, temp, ouverts, fermes);
				//ouverts.add(s);
			}
			
			fermes.add(courant);
		}
		//constitueChemin(fermes.get(fermes.indexOf(goal)));
		
		if(this.chemin.size() == 0)
			System.err.println("Pas de plus cours chemin jusqu'� cette destination");
		
	}
	
	private void updateTemps(Sommet pere, Sommet enfant, double temps, ArrayList<Sommet> ouverts, ArrayList<Sommet> fermes) {
		double temp;
		Sommet enfantExact;
		if(fermes.contains(enfant)) {
			enfantExact = fermes.get(fermes.indexOf(enfant));
			temp = enfantExact.getTemps();
			System.out.println("J'�tais deja dans fermes");
			if(temp > pere.getTemps() + temps) {
				enfantExact.setTemps(pere.getTemps() + temps);
				enfantExact.setParent(pere);
				ouverts.add(enfantExact);
				System.out.println("voisin position " + enfantExact.getPosition() + " parent " + enfantExact.getParent().getPosition());
				System.out.println("J'�tais deja dans fermes et je repart dans ouvert");
				fermes.remove(enfantExact);
				return;
			}
			// dans le cas contraire on ne fait rien
			return;
		}
		if(ouverts.contains(enfant)) {
			enfantExact = ouverts.get(ouverts.indexOf(enfant));
			temp = enfantExact.getTemps();
			System.out.println("J'�tais deja dans ouverts");
			if(temp > pere.getTemps() + temps) {
				enfantExact.setTemps(pere.getTemps() + temps);
				enfantExact.setParent(pere);
				System.out.println("voisin position " + enfantExact.getPosition() + " parent " + enfantExact.getParent().getPosition());
				return;
			}
			// dans le cas contraire on ne fait rien
			return;
		}
		if(enfant.getTemps() > pere.getTemps() + temps) {
			enfant.setTemps(pere.getTemps() + temps);
			enfant.setParent(pere);
		}
		ouverts.add(enfant);
		System.out.println("voisin position " + enfant.getPosition() + " parent " + enfant.getParent().getPosition());
	}
	
	public void deplaceVersCase(Simulateur sim) {
		if(this.chemin.size() == 0) {
			// on sort de la fonction s'il n'ya pas de chemin optim
			return;
		}
		//int i = 1;
		Case temp = this.chemin.pop().getPosition();

		LinkedList<Sommet> s = this.chemin;
		while (s.size() >= 1) {
			// on prend l'�l�ment suivant de la liste
			Case suiv = s.pop().getPosition();
			sim.ajouteEvenement(new DebutDeplacement(temp.getDirection(suiv.getLigne(), suiv.getColonne()), this.robot, sim.getDonneesSimulation(), sim));
			//i++;
			temp = suiv;
		}
		sim.ajouteEvenement(new DebutExtinctionFeu(robot, sim, sim.getDonneesSimulation()));
	}
	
	private void constitueChemin(Sommet s) {
		// on constitue la liste chainee qui a les diff�rents sommets � parcourir connaissant les parents
		System.out.println("On a trouve notre goal enfin " + s.getPosition() + "son parent ");// + s.getParent().getPosition());
		Sommet temp = s;
		while (temp != null) {
			this.chemin.addFirst(temp);
			temp = temp.getParent();
		}

		for (Sommet som : this.chemin) {
			System.out.println(som.getPosition());
		}
		
	}
	
	private  Sommet getSommetProche(ArrayList<Sommet> sommets) {
		Sommet optim = null;
	    double tempsMin = Double.MAX_VALUE;
	    for (Sommet s: sommets) {
	        double t = s.getTemps();
	        if (t < tempsMin) {
	            tempsMin = t;
	            optim = s;
	        }
	    }
	    return optim;
	}
	
	public double getTempsOptim() {
		return this.tempsOptim;
	}
	
	
}
