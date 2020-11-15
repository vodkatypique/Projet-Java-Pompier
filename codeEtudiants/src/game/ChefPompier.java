package game;

import java.util.ArrayList;

public class ChefPompier {
	ArrayList<Robot> robots;
	ArrayList<Incendie> incendies;

	public ChefPompier(ArrayList<Robot> robots) {
		this.setRobots(robots);
		this.setIncendies(incendies);

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
					temps = robot.tempsPlusCoursChemin(aEteindre.getPosition());// TODO temps du plus court chemin
					continue;
				}
				double nouveauTemps = robot.tempsPlusCoursChemin(aEteindre.getPosition());
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
