package game;

import java.util.ArrayList;

/**
 * The type Robot.
 */
public abstract class Robot {
	private Case position;
	private Case positionInit;
	private double vitesse;
	private double reservoir;
	private double reservoirInit;
	private OccupationRobot occupationRobot;

	/**
	 * Instantiates a new Robot.
	 *
	 * @param position the position
	 * @param vitesse  the vitesse
	 */
	public Robot(Case position, int vitesse) {//TODO cleanup les constructeurs

		this.position = position;
		this.positionInit = new Case(this.position);
		setVitesse(vitesse);
		// au depart son reservoir n'est pas vide, il est considere plein
		setResevoir(getReservoirMax());
		this.reservoirInit = this.reservoir;
		this.occupationRobot = new OccupationRobot(0);
	}

	/**
	 * Instantiates a new Robot.
	 *
	 * @param r the r
	 */
	public Robot(Robot r) {
		this.position = new Case(r.getPosition());
		this.positionInit = new Case(this.position);
		this.vitesse = r.getVitesse(r.getPosition().getNature());
		this.reservoir = r.getReservoir();
		this.reservoirInit = this.reservoir;
		this.occupationRobot = new OccupationRobot(0);

	}

	/**
	 * Instantiates a new Robot.
	 *
	 * @param position the position
	 */
	public Robot(Case position) {
		this.position = new Case(position);
		this.positionInit = new Case(this.position);
		this.occupationRobot = new OccupationRobot(0);
		setVitesse(this.getVitesseDefault());
		setResevoir(getReservoirMax());
		this.reservoirInit = this.reservoir;
	}

	/**
	 * Chercher eau plus court chemin.
	 *
	 * @param carte the carte
	 * @return the plus court chemin
	 */
	public PlusCourtChemin chercherEau(Carte carte) {
		Double tempsLePlusRapide = Double.MAX_VALUE;
		ArrayList<PlusCourtChemin> listeChemin = new ArrayList<PlusCourtChemin>();
		for (Case[] cases : carte.getPlateau()) {
			for (Case endroit : cases) {
				if (endroit.getNature() == NatureTerrain.EAU) {
					for (Direction direction : Direction.values()) {
						Case voisin = carte.getVoisin(endroit, direction);
						if (voisin == null) {
							continue;
						}
						if (voisin.getNature() == NatureTerrain.EAU) {
							continue;
						}
						PlusCourtChemin chemin = new PlusCourtChemin(this, voisin, carte);
						if (chemin.getTempsOptim() != 0) {
							double temps = chemin.getTempsOptim();
							if (temps < tempsLePlusRapide) {
								tempsLePlusRapide = temps;
								listeChemin.add(chemin);
							}
						}
					}
				}
			}
		}
		if (listeChemin.isEmpty()) {
			System.err.println("Erreur, Pas d'eau disponible");
			return null;
		}
		return listeChemin.get(listeChemin.size() - 1);
	}

	/**
	 * Duree remplissage reservoir long.
	 *
	 * @param volume the volume
	 * @return the long
	 */
	public long dureeRemplissageReservoir(double volume) {// retourne la duree du remplissage
		long dureeRemplissage = (long) ((volume / getReservoirMax()) * tempRemplissage());
		return dureeRemplissage;
	}

	/**
	 * Duree deversement double.
	 *
	 * @param vol the vol
	 * @return the double
	 */
	public double dureeDeversement(double vol) {// en seconde
		return vol * dureeDeversementUnitaire();
	}

	/**
	 * Peut remplir boolean.
	 *
	 * @param carte the carte
	 * @return the boolean
	 */
	public boolean peutRemplir(Carte carte) {// TODO rewrite using carte.getvoisin
		Boolean peutRemplir = Boolean.FALSE;
		int lig = this.position.getLigne();
		int col = this.position.getColonne();

		for (Direction d : Direction.values()) {
			Case voisin = carte.getVoisin(new Case(lig, col, NatureTerrain.TERRAIN_LIBRE), d);
			if (voisin != null) {
				if (voisin.getNature() == NatureTerrain.EAU) {
					peutRemplir = Boolean.TRUE;
				}
			}
		}
		return peutRemplir;

	}

	/**
	 * Gets vitesse max.
	 *
	 * @return the vitesse max
	 */
	abstract double getVitesseMax();// justification :
	// https://stackoverflow.com/questions/11896955/force-subclasses-to-include-constant-in-abstract-java-class

	/**
	 * Gets reservoir max.
	 *
	 * @return the reservoir max
	 */
	abstract double getReservoirMax();

	/**
	 * Temp remplissage double.
	 *
	 * @return the double
	 */
	abstract double tempRemplissage();// pour la totalité du reservoir

	/**
	 * Duree deversement unitaire double.
	 *
	 * @return the double
	 */
	abstract double dureeDeversementUnitaire();// le temps en seconde pour le deversement de 1L

	/**
	 * Gets vitesse default.
	 *
	 * @return the vitesse default
	 */
	abstract double getVitesseDefault();

	/**
	 * Peut atteindre boolean.
	 *
	 * @param position the position
	 * @return the boolean
	 */
	abstract boolean peutAtteindre(Case position);

	/**
	 * Gets position.
	 *
	 * @return the position
	 */
	public Case getPosition() {
		return this.position;
	}

	/**
	 * Sets position.
	 *
	 * @param localisation the localisation
	 */
	public void setPosition(Case localisation) {
		this.position = localisation;
	}

	/**
	 * Sets resevoir.
	 *
	 * @param resevoir the resevoir
	 */
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

	/**
	 * Sets vitesse.
	 *
	 * @param vitesse the vitesse
	 */
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

	/**
	 * Gets vitesse.
	 *
	 * @param nature the nature
	 * @return the vitesse
	 */
	public double getVitesse(NatureTerrain nature) {
		return this.vitesse;
	}

	/**
	 * Reset reservoir.
	 */
	public void resetReservoir() {
		this.reservoir = this.reservoirInit;
	}

	/**
	 * Reset position.
	 *
	 * @param donnees the donnees
	 */
	public void resetPosition(DonneesSimulation donnees) {
		Case temp = donnees.getCarte().getCase(this.positionInit.getLigne(), this.positionInit.getColonne());
		this.position = temp;
	}

	/**
	 * To string string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Robot{" + "position=" + position + ", type='" + getClass().getName() + '\'' + '}';
	}

	/**
	 * Gets reservoir.
	 *
	 * @return the reservoir
	 */
	public double getReservoir() {
		return reservoir;
	}

	/**
	 * Gets occupation robot.
	 *
	 * @return the occupation robot
	 */
	public OccupationRobot getOccupationRobot() {
		return this.occupationRobot;
	}

}
