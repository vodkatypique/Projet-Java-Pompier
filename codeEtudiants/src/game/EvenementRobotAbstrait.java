package game;

/**
 * The type Evenement robot abstrait.
 */
public abstract class EvenementRobotAbstrait extends Evenement {
	private Robot robot;

	/**
	 * Instantiates a new Evenement robot abstrait.
	 *
	 * @param date  the date
	 * @param robot the robot
	 */
	public EvenementRobotAbstrait(long date, Robot robot) {
		super(date, robot);
		this.robot = robot;
	}

	/**
	 * Instantiates a new Evenement robot abstrait.
	 *
	 * @param robot the robot
	 */
	public EvenementRobotAbstrait(Robot robot) {
		super(robot);
		this.robot = robot;
	}

	/**
	 * Gets robot.
	 *
	 * @return the robot
	 */
	public Robot getRobot() {
		return this.robot;
	}


}
