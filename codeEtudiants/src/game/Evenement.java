package game;

public abstract class Evenement {
	private long date;
	private Robot robot;
	
	public Evenement(long date, Robot robot) {
		this.date=date;
		this.robot = robot;
	}
	
	public Evenement(Robot robot) {
		this.robot = robot;
	}
	
	public long getDate() {
		return this.date;
	}
	
	public Robot getRobot() {
		return this.robot;
	}
	public void setDate(long date) {
		if(this.getDate() == date)
			++date;//toute action prend au moins 1 minutes
		this.date = date;
	}
	
	abstract public long getDateFin();
	public abstract void execute();
	
}
