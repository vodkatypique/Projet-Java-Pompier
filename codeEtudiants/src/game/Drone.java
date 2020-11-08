package game;

public class Drone extends Robot {

	public Drone(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 100);

	}

@Override
	public double dureeDeversement(double vol) {
		return 30;
	}
	@Override
	double getVitesseMax() {
		return 150;
	}

	@Override
	double getReservoirMax() {
		return 10000;
	}

	@Override
	double tempRemplissage() {
		// en minute
		return 30;
	}


	@Override
	double vitesseDeversement() {
		// TODO Auto-generated method stub
		return getReservoirMax()/30;
	}


}
