package game;

public class Roue extends Robot {
    public Roue(Case position) {
    	this(position, 80);

    }

    public Roue(Case position, double vitesse) {
    	super(position, vitesse);
    	setResevoir(getReservoirMax());
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