package game;

public abstract class EvenementRobotAbstrait extends Evenement {
	private Robot robot;
	
	public EvenementRobotAbstrait(long date,Robot robot) {
		super(date);
		this.robot = robot;
	}
	
	public Robot getRobot() {
		return robot;
	}
	
	
}
