package game;

public class Roue extends Robot {
	public Roue(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 80);


	}

	@Override
	public void deverserEau(int vol) {
		// TODO Auto-generated method stub
	}


	@Override
	double getVitesseMax() {
		// Pas de vitesse max dans le sujet
		return Double.MAX_VALUE;
	}

	@Override
	double getReservoirMax() {
		// TODO Auto-generated method stub
		return 5000;
	}

	@Override
	double tempRemplissage() {
		// TODO Auto-generated method stub
		return 10;
	}
}