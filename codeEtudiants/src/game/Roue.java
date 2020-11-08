package game;

public class Roue extends Robot {
	public Roue(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 80);


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
	double vitesseDeversement() {
		return 100/5;
	}
}