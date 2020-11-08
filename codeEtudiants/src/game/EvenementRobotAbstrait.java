package game;

public abstract class EvenementRobotAbstrait extends Evenement {
	private Robot robot;
	public EvenementRobotAbstrait(long date,Robot robot) {
		super(date);
		this.setRobot(robot);
	}
	public Robot getRobot() {
		return robot;
	}
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
}
