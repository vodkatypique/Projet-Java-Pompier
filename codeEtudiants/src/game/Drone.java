package game;

public class Drone extends Robot {

	public Drone(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 100);

	}

	public Drone(Drone d) {
		super(d);
	}
	
	public Drone(Case position) {
		super(position);
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
	public boolean peutRemplir(Carte carte) {
		if (carte.getCase(this.getPosition().getLigne(),this.getPosition().getColonne() ).getNature() == NatureTerrain.EAU) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	double dureeDeversementUnitaire() {
		// TODO Auto-generated method stub
		return 30.0/getReservoirMax();
	}


}
