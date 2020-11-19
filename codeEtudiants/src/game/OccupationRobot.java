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
	 * @param dateFin the date fin
	 */
	public OccupationRobot(long dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Gets occupation generale.
	 *
	 * @return the occupation generale
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
	 * @return the date fin
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
