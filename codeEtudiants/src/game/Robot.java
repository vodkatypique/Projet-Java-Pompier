package game;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;

public abstract class Robot {
	private Case position;
	private double vitesse;
	private double reservoir;
	protected double volumeIntervention;
	OccupationRobot occupationRobot=new OccupationRobot(Boolean.FALSE,0);
	public Robot(Case position, int vitesse) {
		this.position = position;
		setVitesse(vitesse);
		setResevoir(getReservoirMax());
	}

	long dureeRemplissageReservoir(double volume) {// retourne la duree du remplissage
		long dureeRemplissage = (long) ((volume / getReservoirMax()) * tempRemplissage());
		return dureeRemplissage;
	}
	public double dureeDeversement(double vol) {//en seconde
		return vol/vitesseDeversement();
	}

	abstract double getVitesseMax();//justification : https://stackoverflow.com/questions/11896955/force-subclasses-to-include-constant-in-abstract-java-class
	abstract double getReservoirMax();
	abstract double tempRemplissage();//pour la totalité du reservoir
	abstract double vitesseDeversement();//en litre par seconde
	
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


	@Override
	public String toString() {
		return "Robot{" +
				"position=" + position +
				", type='" + getClass().getName() + '\'' +
				'}';
	}

	public double getReservoir() {
		return reservoir;
	}
}
