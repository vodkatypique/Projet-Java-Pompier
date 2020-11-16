package game;

import java.util.ArrayList;

public class ChefPompier {
	private ArrayList<Robot> robots;
	private ArrayList<Incendie> incendies;
	private Carte carte;

	public ChefPompier(ArrayList<Robot> robots, Carte ca, ArrayList<Incendie> incendies) {
		this.setRobots(robots);
		this.setIncendies(incendies);
		this.carte = ca;
		this.boucleExtinction();

	}

	public void setRobots(ArrayList<Robot> robots) {
		this.robots = robots;
	}

	public void setIncendies(ArrayList<Incendie> incendies) {
		this.incendies = incendies;
	}

	void boucleExtinction() {
		while (!this.incendies.isEmpty()) {
			Incendie aEteindre = null;
			for (Incendie incendie : this.incendies) {
				aEteindre = incendie;
				break;
			}
			if (aEteindre == null) {
				break;
			}
			Robot robotLePlusRapide = null;
			double temps = Double.MAX_VALUE;
			for (Robot robot : this.robots) {
				if (robot.getOccupationRobot().getEstOccupe()) {
					continue;
				}
				if (robotLePlusRapide == null) {
					robotLePlusRapide = robot;
					temps = new PlusCourtChemin(robot, aEteindre.getPosition(), carte).getTempsOptim();// TODO temps du plus court chemin
					System.out.println(temps);
					continue;
				}
				double nouveauTemps = new PlusCourtChemin(robot, aEteindre.getPosition(), carte).getTempsOptim();
				if (temps > nouveauTemps) {
					robotLePlusRapide = robot;
					temps = nouveauTemps;
				}
			}
			if (robotLePlusRapide == null) {
				continue;
			}
			//TODO On a le robot qui va éteindre l'incendie donc il faut le faire eteindre l'incendie
			
		}
	}
}
