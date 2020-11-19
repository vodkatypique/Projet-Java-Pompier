package game;

import java.util.ArrayList;
import java.util.LinkedList;

// import java.util.ArrayList;

/**
 * The type Plus court chemin.
 */
public class PlusCourtChemin {
	// private ArrayList<Sommet> sommets;
	private LinkedList<Sommet> chemin;
	private Carte carte;
	private Case goal;
	private Sommet depart;
	private double tempsOptim;
	private Robot robot;

	/**
	 * Instantiates a new Plus court chemin.
	 *
	 * @param robot the robot
	 * @param ca    the ca
	 * @param carte the carte
	 */
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
	/**
	 * Implémentation de dijkstra
	 *
	 * 
	 * 
	 * 
	 */
	private void plusCourtChemin() {
		ArrayList<Sommet> ouverts = new ArrayList<>();
		ArrayList<Sommet> fermes = new ArrayList<>();
		ouverts.add(this.depart);

		while (ouverts.size() != 0) {
			Sommet courant = getSommetProche(ouverts);
			ouverts.remove(courant);
			if (courant.getPosition().equals(goal)) {
				fermes.add(courant);
				constitueChemin(courant);
				this.tempsOptim = courant.getTemps();
				break;
			}
			for(Sommet s: courant.getVoisins(this.carte, this.robot)) {

				double distance = this.carte.getDistanceEntreCase();
				// calcul du temps pour le robot de se deplacer de sa case vers une case voisine
				double temp = distance / ((this.robot.getVitesse(s.getPosition().getNature()) * Math.pow(10, 3)) / 60);
				updateTemps(courant, s, temp, ouverts, fermes);
			}

			fermes.add(courant);
		}

		if(this.chemin.size() == 0)
			System.err.println("Pas de plus cours chemin jusqu'à cette destination");

	}
	/**
	 * 
	 * @param pere
	 * @param enfant
	 * @param temps
	 * @param ouverts
	 * @param fermes
	 */
	private void updateTemps(Sommet pere, Sommet enfant, double temps, ArrayList<Sommet> ouverts, ArrayList<Sommet> fermes) {
		double temp;
		Sommet enfantExact;
		if(fermes.contains(enfant)) {
			enfantExact = fermes.get(fermes.indexOf(enfant));
			temp = enfantExact.getTemps();
			if (temp > pere.getTemps() + temps) {
				enfantExact.setTemps(pere.getTemps() + temps);
				enfantExact.setParent(pere);
				ouverts.add(enfantExact);
				fermes.remove(enfantExact);
				return;
			}
			// dans le cas contraire on ne fait rien
			return;
		}
		if(ouverts.contains(enfant)) {
			enfantExact = ouverts.get(ouverts.indexOf(enfant));
			temp = enfantExact.getTemps();
			if (temp > pere.getTemps() + temps) {
				enfantExact.setTemps(pere.getTemps() + temps);
				enfantExact.setParent(pere);
				return;
			}
			// dans le cas contraire on ne fait rien
			return;
		}
		if (enfant.getTemps() > pere.getTemps() + temps) {
			enfant.setTemps(pere.getTemps() + temps);
			enfant.setParent(pere);
		}
		ouverts.add(enfant);
		//System.out.println("voisin position " + enfant.getPosition() + " parent " + enfant.getParent().getPosition());
	}

	/**
	 * Deplace vers case.
	 *
	 * @param sim le Simulateur
	 */
	public void deplaceVersCase(Simulateur sim) {
		if (this.chemin.size() == 0) {
			// on sort de la fonction s'il n'ya pas de chemin optim
			return;
		}
		//int i = 1;
		Case temp = this.chemin.pop().getPosition();

		LinkedList<Sommet> s = this.chemin;
		while (s.size() >= 1) {
			// on prend l'ï¿½lï¿½ment suivant de la liste
			Sommet suivant= s.pop();
			Case suiv = suivant.getPosition();
			sim.ajouteEvenement(new Deplacement( temp.getDirection(suiv.getLigne(), suiv.getColonne()), this.robot,sim.getDonneesSimulation(),sim),this.robot);
			//i++;
			temp = suiv;
		}
	}

	private void constitueChemin(Sommet s) {
		// on constitue la liste chainee qui a les diffï¿½rents sommets ï¿½ parcourir connaissant les parents
		Sommet temp = s;
		while (temp != null) {
			this.chemin.addFirst(temp);
			temp = temp.getParent();
		}

	}

	private  Sommet getSommetProche(ArrayList<Sommet> sommets) {
		Sommet optim = null;
		double tempsMin = Double.MAX_VALUE;
		for (Sommet s : sommets) {
			double t = s.getTemps();
			if (t < tempsMin) {
				tempsMin = t;
				optim = s;
			}
		}
		return optim;
	}

	/**
	 * Gets temps optim.
	 *
	 * @return the temps optim
	 */
	public double getTempsOptim() {
		return this.tempsOptim;
	}


}
