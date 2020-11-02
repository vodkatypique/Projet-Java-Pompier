package game;

public abstract class Robot {
	protected Case position;
	protected double vitesse;
	protected double resevoir;
	protected double volumeIntervention;

	void RemplirReservoir() {

	}

	public Case getPosition() {
		return this.position;
	}

	void setPosition(Case localisation) {
		this.position = localisation;
	}

	public double getVitesse(NatureTerrain nature) {
		return this.vitesse;
	}

	public abstract void deverserEau(int vol);

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public String toString() {
		return "Robot{" +
				"position=" + position +
				", type='" + getClass().getName() + '\'' +
				'}';
	}
}
