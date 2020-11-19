package game;

/**
 * The type Evenement debut abstrait.
 */
public abstract class EvenementDebutAbstrait extends EvenementRobotAbstrait {
	private Simulateur simulateur;

	/**
	 * Instantiates a new Evenement debut abstrait.
	 *
	 * @param date       the date
	 * @param robot      the robot
	 * @param simulateur the simulateur
	 */
	public EvenementDebutAbstrait(long date, Robot robot, Simulateur simulateur) {
		super(date, robot);
		this.simulateur = simulateur;
	}

	/**
	 * Instantiates a new Evenement debut abstrait.
	 *
	 * @param robot      the robot
	 * @param simulateur the simulateur
	 */
	public EvenementDebutAbstrait(Robot robot, Simulateur simulateur) {
		super(robot);
		this.simulateur = simulateur;
	}


	/**
	 * Gets simulateur.
	 *
	 * @return the simulateur
	 */
	public Simulateur getSimulateur() {
		return simulateur;
	}

	/**
	 * Sets simulateur.
	 *
	 * @param simulateur the simulateur
	 */
	public void setSimulateur(Simulateur simulateur) {
		this.simulateur = simulateur;
	}

}
