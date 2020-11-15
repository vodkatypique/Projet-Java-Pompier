package game;

public abstract class Robot {
	private Case position;
	private double vitesse;
	private double reservoir;
	protected double volumeIntervention;
	private OccupationRobot occupationRobot;

	public Robot(Case position, int vitesse) {
		this.position = position;
		setVitesse(vitesse);
		setResevoir(getReservoirMax());
		occupationRobot = new OccupationRobot(Boolean.FALSE, 0);
	}

	public long dureeRemplissageReservoir(double volume) {// retourne la duree du remplissage
		long dureeRemplissage = (long) ((volume / getReservoirMax()) * tempRemplissage());
		return dureeRemplissage;
	}

	public double dureeDeversement(double vol) {// en seconde
		return vol / vitesseDeversement();
	}
	public double tempsPlusCoursChemin(Case position) {
		return 0.0;//TODO faire le plus cours chemin
	}
	public boolean peutRemplir(Carte carte) {
		Boolean peutRemplir = Boolean.FALSE;
		int lig = this.position.getLigne();
		int col = this.position.getColonne();
		if (carte.getCase(--lig, col).getNature() == NatureTerrain.EAU) {
			peutRemplir = Boolean.TRUE;
		}
		if (carte.getCase(++lig, col).getNature() == NatureTerrain.EAU) {
			peutRemplir = Boolean.TRUE;
		}
		if (carte.getCase(lig, --col).getNature() == NatureTerrain.EAU) {
			peutRemplir = Boolean.TRUE;
		}
		if (carte.getCase(lig, ++col).getNature() == NatureTerrain.EAU) {
			peutRemplir = Boolean.TRUE;
		}
		return peutRemplir;

	}

	abstract double getVitesseMax();// justification :
									// https://stackoverflow.com/questions/11896955/force-subclasses-to-include-constant-in-abstract-java-class

	abstract double getReservoirMax();

	abstract double tempRemplissage();// pour la totalité du reservoir

	abstract double vitesseDeversement();// en litre par seconde

	public Case getPosition() {
		return this.position;
	}

	public void setPosition(Case localisation) {
		this.position = localisation;
	}

	public void setResevoir(double resevoir) {
		if (resevoir < 0) {
			System.err.println("Erreur, Reservoir négatif ");
			this.reservoir = 0;
		} else if (resevoir > getReservoirMax()) {
			System.err.println("Erreur, resevoir trop grand");
			this.reservoir = getReservoirMax();
		} else {
			this.reservoir = resevoir;
		}
	}

	public void setVitesse(double vitesse) {
		if (vitesse < 0) {
			System.err.println("Erreur, Vitesse négative ");
			this.vitesse = 0;
		} else if (vitesse > getVitesseMax()) {
			System.err.println("Erreur, Vitesse trop grande");
			this.vitesse = getVitesseMax();
		} else {
			this.vitesse = vitesse;
		}
	}

	public double getVitesse(NatureTerrain nature) {
		return this.vitesse;
	}

	@Override
	public String toString() {
		return "Robot{" + "position=" + position + ", type='" + getClass().getName() + '\'' + '}';
	}

	public double getReservoir() {
		return reservoir;
	}
	
	public OccupationRobot getOccupationRobot() {
		return this.occupationRobot;
	}
}
