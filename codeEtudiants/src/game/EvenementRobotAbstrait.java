package game;

public abstract class EvenementRobotAbstrait extends Evenement {
	private Robot robot;
	
	public EvenementRobotAbstrait(long date,Robot robot) {
		super(date, robot);
		this.robot = robot;
	}
	
	public EvenementRobotAbstrait(Robot robot) {
		super(robot);
		this.robot = robot;
	}
	
	public Robot getRobot() {
		return this.robot;
	}
	
	
}
