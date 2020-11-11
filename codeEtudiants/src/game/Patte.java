package game;

public class Patte extends Robot {
	public Patte(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 30);
	}


	@Override
	void setVitesse(double vitesse) {//la vitesse ne varie pas pour ce type de robot
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
		return 0;
	}
	@Override
	public boolean peutRemplir(Carte carte) {
		// TODO Auto-generated method stub
		return Boolean.FALSE;
	}
	@Override
	double vitesseDeversement() {
		// TODO Auto-generated method stub
		return 10/1;
	}

}