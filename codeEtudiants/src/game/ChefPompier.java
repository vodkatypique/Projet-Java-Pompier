package game;

import java.util.ArrayList;

public class ChefPompier {
	private ArrayList<Robot> robots;
	private ArrayList<Incendie> incendies;
	private Carte carte;
	private Simulateur simulateur;

	public ChefPompier(ArrayList<Robot> robots, Carte ca, ArrayList<Incendie> incendies, Simulateur simulateur) {
		this.setRobots(robots);
		this.setIncendies(incendies);
		this.carte = ca;
		this.simulateur = simulateur;
	}

	public void setRobots(ArrayList<Robot> robots) {
		this.robots = robots;
	}

	public void setIncendies(ArrayList<Incendie> incendies) {
		this.incendies = incendies;
	}

	public void boucleExtinction() {
		for (Incendie incendie : this.incendies) {
			ArrayList<PlusCourtChemin> listChemin = new ArrayList<PlusCourtChemin>();
			int index = 0;
			int indexRobotRapide = 0;
			Incendie aEteindre = null;
			aEteindre = incendie;

			Robot robotLePlusRapide = null;
			double temps = Double.MAX_VALUE;
			for (Robot robot : this.robots) {
				if (robot.getOccupationRobot().getEstOccupe() && robot.getOccupationRobot().isOccupationGenerale()) {
					continue;
				}
				if (robotLePlusRapide == null) {
					robotLePlusRapide = robot;
					PlusCourtChemin plusCourt = new PlusCourtChemin(robot, aEteindre.getPosition(), carte);// TODO temps du plus court chemin
					indexRobotRapide = 0;
					listChemin.add(plusCourt);
					index++;
					temps = plusCourt.getTempsOptim();
					continue;
				}
				PlusCourtChemin nouveauPlusCourt = new PlusCourtChemin(robot, aEteindre.getPosition(), carte);
				listChemin.add(nouveauPlusCourt);
				double nouveauTemps = nouveauPlusCourt.getTempsOptim();

				if (temps > nouveauTemps) {
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

			robotLePlusRapide.getOccupationRobot().setOccupationGenerale(true);
			listChemin.get(indexRobotRapide).deplaceVersCase(this.simulateur);
			simulateur.ajouteEvenement(new DebutExtinctionFeu(robotLePlusRapide, this.simulateur, this.simulateur.getDonneesSimulation()));
			robotLePlusRapide = null;
		}

	}


}
