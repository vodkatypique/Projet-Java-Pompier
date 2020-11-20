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
		this.chemin = new LinkedList<>();
		this.depart = new Sommet(robot.getPosition(), 0);
		this.carte = carte;
		this.goal = ca;
		this.robot = robot;
		plusCourtChemin();
	}
	/**
	 * Implémentation de dijkstra
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
			System.err.println("Pas de plus cours chemin jusqu'� cette destination");

	}

	/**
	 * Mise a jour des temps de trajet pour chaque sommet de proche en proche,
	 * utile pour le calcul du temps du trajet au total en fin d'algorithme
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
	 * On ajoute les etapes du trajet dans les evenement de la simulation
	 *
	 * @param sim le Simulateur
	 */
	public void deplaceVersCase(Simulateur sim) {
		if (this.chemin.size() == 0) {
			// on sort de la fonction s'il n'ya pas de chemin optim
			return;
		}
		Case temp = this.chemin.pop().getPosition();

		LinkedList<Sommet> s = this.chemin;
		while (s.size() >= 1) {
			// on prend l'élément suivant de la liste
			Sommet suivant = s.pop();
			Case suiv = suivant.getPosition();
			sim.ajouteEvenement(new Deplacement(temp.getDirection(suiv.getLigne(), suiv.getColonne()), this.robot, sim.getDonneesSimulation(), sim), this.robot);
			temp = suiv;
		}
	}

	/**
	 * on constitue la liste chainee qui a les différents sommets à parcourir connaissant les parents
	 *
	 * @param s sommet de depart de la reconstruction
	 */
	private void constitueChemin(Sommet s) {
		Sommet temp = s;
		while (temp != null) {
			this.chemin.addFirst(temp);
			temp = temp.getParent();
		}

	}

	/**
	 * renvoit le sommet le plus proche -en terme de temps de trajet- d'un sommet donné parmis un ArrayList de sommets
	 *
	 * @param sommets
	 * @return le sommet le plus proche parmis la liste des sommets donné en parametre
	 */
	private Sommet getSommetProche(ArrayList<Sommet> sommets) {
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
	 * Gets temps du plus court chemin calculé
	 *
	 * @return the temps du trajet le plus court
	 */
	public double getTempsOptim() {
		return this.tempsOptim;
	}


}
