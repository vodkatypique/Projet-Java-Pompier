package game;

/**
 * The type Evenement robot abstrait.
 */
public abstract class EvenementRobotAbstrait extends Evenement {
	private Robot robot;
	private Simulateur simulateur;

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
	 * Instantiates a new Evenement robot abstrait.
	 *
	 * @param robot the robot
	 */
	public EvenementRobotAbstrait(Robot robot,Simulateur simulateur) {
		super(robot);
		this.robot = robot;
		this.simulateur=simulateur;
	}
	/**
	 * Gets robot.
	 *
	 * @return the robot
	 */
	public Robot getRobot() {
		return this.robot;
	}

	public Simulateur getSimulateur() {
		return simulateur;
	}


}
