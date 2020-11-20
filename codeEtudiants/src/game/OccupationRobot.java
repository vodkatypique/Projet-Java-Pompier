package game;

/**
 * The type Occupation robot.
 */
public class OccupationRobot {
	private long dateFin;
	private boolean OccupationGenerale;

	/**
	 * Instantiates a new Occupation robot.
	 *
	 * @param dateFin the date fin de l'occupation
	 */
	public OccupationRobot(long dateFin) {
		this.dateFin = dateFin;
	}

	public OccupationRobot() {

	}

	/**
	 * Gets occupation generale, utile pour le calcul du plus court chemin pour savoir si un robot est deja occupé
	 *
	 * @return l'etat d'occupation du robot
	 */
	public boolean getOccupationGenerale() {
		return OccupationGenerale;
	}

	/**
	 * Sets occupation generale.
	 *
	 * @param occupationGenerale the occupation generale
	 */
	public void setOccupationGenerale(boolean occupationGenerale) {
		OccupationGenerale = occupationGenerale;
	}

	/**
	 * Gets date fin.
	 *
	 * @return the date fin de l'occupation du Robot
	 */
	public long getDateFin() {
		return this.dateFin;
	}

	/**
	 * Sets date fin.
	 *
	 * @param date the date
	 */
	public void setDateFin(long date) {
		this.dateFin = date;
	}
}
