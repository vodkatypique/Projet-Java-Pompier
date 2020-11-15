package game;

public class Roue extends Robot {
	public Roue(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 80);


	}

	public Roue(Roue roue) {
		super(roue);
	}

	public Roue(Case position) {
		super(position);
	}
	
	@Override
	double getVitesseMax() {
		// Pas de vitesse max dans le sujet
		return Double.MAX_VALUE;
	}

	@Override
	double getReservoirMax() {
		return 5000;
	}

	@Override
	double tempRemplissage() {
		return 10;
	}

	@Override
	double dureeDeversementUnitaire() {
		return 5.0/100;
	}
}