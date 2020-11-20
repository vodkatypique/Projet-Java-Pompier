package game;

/**
 * The type Occupation robot.
 */
public class OccupationRobot {
	private long dateFin;
	private boolean OccupationGenerale;


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

}
