package game;

import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.*;

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
	}
	
	private void plusCourtChemin() {
		ArrayList<Sommet> ouverts = new ArrayList<>();
		ArrayList<Sommet> fermes = new ArrayList<>();
		
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
			for(Sommet s: courant.getVoisins(this.carte)) {
				if(!fermes.contains(s)) {
					double distance = Carte.getDistanceEntreCase();
					// calcul du temps pour le robot de se deplacer de sa case vers une case voisine
					double temp = distance / ((this.robot.getVitesse(s.getPosition().getNature())*Math.pow(10,3))/60);
					if(courant.getTemps() + temp < s.getTemps()) {
						// le parent du voisin c'est le sommet courant
						s.setParent(courant);
						s.setTemps(temp + courant.getTemps());
					}
					ouverts.add(s);
				}
			}
			
			fermes.add(courant);
		}
		
		if(this.chemin.size() == 0)
			System.err.println("Pas de plus cours chemin jusqu'à cette destination");
		
	}
	
	public void deplaceVersCase(Simulateur sim) {
		plusCourtChemin();
		if(this.chemin.size() == 0) {
			// on sort de la fonction s'il n'ya pas de chemin optim
			return;
		}
		int i = 1;
		Case temp = this.chemin.pop().getPosition();
		LinkedList<Sommet> s = this.chemin;
		while(s.size() >= 1) {
			// on prend l'élément suivant de la liste
			Case suiv = s.pop().getPosition();
			sim.ajouteEvenement(new DebutDeplacement(i, temp.getDirection(suiv.getLigne(), suiv.getColonne()), this.robot, sim.getDonneesSimulation(), sim));
			i++;
			temp = suiv;
		}
	}
	
	private void constitueChemin(Sommet s) {
		// on constitue la liste chainee qui a les différents sommets à parcourir connaissant les parents
		Sommet temp = s;
		while(temp != null) {
			this.chemin.addFirst(s);
			temp = temp.getParent();
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
		plusCourtChemin();
		return this.tempsOptim;
	}
	
	
}
