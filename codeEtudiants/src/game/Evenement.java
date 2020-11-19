package game;

/**
 * The type Evenement.
 */
public abstract class Evenement {
	private long date;
	private Robot robot;

	/**
	 * Instantiates a new Evenement.
	 *
	 * @param date  the date
	 * @param robot the robot
	 */
	public Evenement(long date, Robot robot) {
		this.date = date;
		this.robot = robot;
	}

	/**
	 * Instantiates a new Evenement.
	 *
	 * @param robot the robot
	 */
	public Evenement(Robot robot) {
		this.robot = robot;
	}

	/**
	 * Gets date.
	 *
	 * @return the date
	 */
	public long getDate() {
		return this.date;
	}

	/**
	 * Sets date.
	 *
	 * @param date the date
	 */
	public void setDate(long date) {
		if (this.getDate() == date)
			++date;//toute action prend au moins 1 minutes
		this.date = date;
	}

	/**
	 * Gets robot.
	 *
	 * @return the robot
	 */
	public Robot getRobot() {
		return this.robot;
	}

	/**
	 * Execute.
	 */
	public abstract void execute();

}
