package game;

public abstract class Robot {
	protected Case position;
	private double vitesse;
	private double reservoir;
	protected double volumeIntervention;

	public Robot(Case position,double vitesse) {
		this.position=position;
		setVitesse(vitesse);
	}
	 void RemplirReservoir() {
		tempRemplissage();//utiliser ça
	}
	abstract double getVitesseMax();//justification : https://stackoverflow.com/questions/11896955/force-subclasses-to-include-constant-in-abstract-java-class
	abstract double getReservoirMax();
	abstract double tempRemplissage();
	public Case getPosition() {
		return this.position;
	}
	void setPosition(Case localisation) {
		this.position = localisation;
	}
	public void setResevoir(double resevoir) {
				if(resevoir<0) {
					System.err.println("Erreur, Reservoir négatif ");
					this.reservoir=0;
				}else if(resevoir>getReservoirMax()) {
					System.err.println("Erreur, resevoir trop grand");
					this.reservoir=getReservoirMax();
				}else {
					this.reservoir=resevoir ;
				}
	}
	void setVitesse(double vitesse) {
		if(vitesse<0) {
			System.err.println("Erreur, Vitesse négative ");
			this.vitesse=0;
		}else if(vitesse>getVitesseMax()) {
			System.err.println("Erreur, Vitesse trop grande");
			this.vitesse=getVitesseMax();
		}else {
			this.vitesse=vitesse;
		}
	}

	public double getVitesse(NatureTerrain nature) {
		return this.vitesse;
	}

	public abstract void deverserEau(int vol);


	@Override
	public String toString() {
		return "Robot{" +
				"position=" + position +
				", type='" + getClass().getName() + '\'' +
				'}';
	}
}
