package game;

public class Drone extends Robot {

	public Drone(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 100);

	}


	@Override
	public void deverserEau(int vol) {
		//TODO derverser
	}

	@Override
	double getVitesseMax() {
		// TODO Auto-generated method stub
		return 150;
	}

	@Override
	double getReservoirMax() {
		// TODO Auto-generated method stub
		return 10000;
	}

	@Override
	double tempRemplissage() {
		// en minute
		return 30;
	}


}
