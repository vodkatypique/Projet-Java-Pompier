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
	public double getVitesseMax() {
		// Pas de vitesse max dans le sujet
		return Double.MAX_VALUE;
	}

	@Override
	public double getReservoirMax() {
		return 5000;
	}

	@Override
	public double tempRemplissage() {
		return 10;
	}

	@Override
	public double dureeDeversementUnitaire() {
		return 5.0/100;
	}

	@Override
	public double getVitesseDefault() {
		// TODO Auto-generated method stub
		return 80.0;
	}

	@Override
	public boolean peutAtteindre(Case position) {
        // TODO Auto-generated method stub
        return position.getNature() == NatureTerrain.TERRAIN_LIBRE || position.getNature() == NatureTerrain.HABITAT;
    }
}