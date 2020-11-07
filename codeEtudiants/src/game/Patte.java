package game;

public class Patte extends Robot {
    public Patte(Case position) {
    	this(position, 30);
    }
    public Patte(Case position, double vitesse) {
	super(position, vitesse);
}


    @Override
    public void deverserEau(int vol) {

    }
@Override
void setVitesse(double vitesse) {
	// TODO Auto-generated method stub
	super.setVitesse(30);
}
    @Override
    public double getVitesse(NatureTerrain nature) {
        if (nature == NatureTerrain.ROCHE) {
            //return super.getVitesse(nature) - 10;
        	return 10;//réduite "A" et non "DE" dans le sujet
        }
        return super.getVitesse(nature);
    }

	@Override
	double getVitesseMax() {
		return 30;
	}
	@Override
	double getReservoirMax() {
		return Double.MAX_VALUE;
	}
	@Override
	double tempRemplissage() {
		// TODO Auto-generated method stub
		return 0;
	}

}