package game;

import java.util.ArrayList;

/**
 * The type Chef pompier.
 */
public class ChefPompier {
	private ArrayList<Robot> robots;

	/**
	 * Instantiates a new Chef pompier.
	 *
	 * @param robots     the robots
	 * @param ca         the ca
	 * @param incendies  the incendies
	 * @param simulateur the simulateur
	 */
	public ChefPompier(ArrayList<Robot> robots, Carte ca, ArrayList<Incendie> incendies, Simulateur simulateur) {
		this.setRobots(robots);
		this.setIncendies(incendies);
		this.carte = ca;
		this.simulateur = simulateur;
	}


	private ArrayList<Incendie> incendies;
	private Carte carte;
	private Simulateur simulateur;

	/**
	 * Gets robots.
	 *
	 * @return the robots
	 */
	public ArrayList<Robot> getRobots() {
		return robots;
	}

	/**
	 * Sets robots.
	 *
	 * @param robots the robots
	 */
	public void setRobots(ArrayList<Robot> robots) {
		this.robots = robots;
	}

	/**
	 * Sets incendies.
	 *
	 * @param incendies the incendies
	 */
	public void setIncendies(ArrayList<Incendie> incendies) {
		this.incendies = incendies;
	}


	/**
	 * Boucle extinction.
	 * temps qu'il y a des incendies a eteindre on envoit le robot qui y sera le plus vite
	 */
	public void boucleExtinction() {
		for (Incendie incendie : this.incendies) {
			ArrayList<PlusCourtChemin> listChemin = new ArrayList<PlusCourtChemin>();
			int index = 0;
			int indexRobotRapide = 0;
			Incendie aEteindre = null;
			aEteindre = incendie;

			Robot robotLePlusRapide = null;
			double temps = Double.MAX_VALUE;

			// on cherche le robot le plus rapide
			for (Robot robot : this.robots) {
				if (robot.getOccupationGenerale()) {
					continue;
				}
				if (robot.getReservoir() == 0) {
					// si le robot est vide, on l'envoi se remplir
					robot.setOccupationGenerale(true);
					PlusCourtChemin directionEau = robot.chercherEau(carte);
					directionEau.deplaceVersCase(this.simulateur);
					this.simulateur.ajouteEvenement(new RemplissageReservoir(robot, this.simulateur), robot);
					continue;
				}
				if (robotLePlusRapide == null) {
					// pour les comparaison, on fixe le robot le plus rapide lors de la premiere  iteration
					robotLePlusRapide = robot;
					PlusCourtChemin plusCourt = new PlusCourtChemin(robot, aEteindre.getPosition(), carte);
					indexRobotRapide = 0;
					listChemin.add(plusCourt);
					index++;
					temps = plusCourt.getTempsOptim();

					continue;
				}
				PlusCourtChemin nouveauPlusCourt = new PlusCourtChemin(robot, aEteindre.getPosition(), carte);
				listChemin.add(nouveauPlusCourt);
				double nouveauTemps = nouveauPlusCourt.getTempsOptim();

				if (temps > nouveauTemps || temps == 0) {
					robotLePlusRapide = robot;
					indexRobotRapide = index;
					temps = nouveauTemps;
				}
				index++;
				System.err.println(temps + " " + nouveauTemps);
			}
			if (robotLePlusRapide == null) {
				continue;
			}
			robotLePlusRapide.setOccupationGenerale(true);
			listChemin.get(indexRobotRapide).deplaceVersCase(this.simulateur);
			simulateur.ajouteEvenement(new ExtinctionFeu(robotLePlusRapide, aEteindre.getIntensite(),this.simulateur, this.simulateur.getDonneesSimulation()),robotLePlusRapide);
			robotLePlusRapide = null;
		}

	}


}
