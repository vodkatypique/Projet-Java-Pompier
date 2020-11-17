package game;

//vitesseMax=80
public class Chenille extends Robot {
	public Chenille(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 60);
	}

	public Chenille(Chenille c) {
		super(c);
	}
	
	public Chenille(Case position) {
		super(position);
	}
	
	@Override
	public double getVitesse(NatureTerrain nature) {
		if (nature == NatureTerrain.FORET) {
            return super.getVitesse(nature) / 2;
        }
        return super.getVitesse(nature);
    }



	@Override
	double getVitesseMax() {
		return 80;
	}

	@Override
	double getReservoirMax() {
		// TODO Auto-generated method stub
		return 2000;
	}

	@Override
	double tempRemplissage() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	double dureeDeversementUnitaire() {
		return 8.0/100;
	}

	@Override
	double getVitesseDefault() {
		// TODO Auto-generated method stub
		return 60.0;
	}

	@Override
	boolean peutAtteindre(Case position) {
		// TODO Auto-generated method stub
		if(position.getNature() == NatureTerrain.EAU || position.getNature() == NatureTerrain.ROCHE)
			return false;
		return true;
	}




}