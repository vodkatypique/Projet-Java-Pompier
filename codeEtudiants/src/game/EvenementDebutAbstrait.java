package game;

public abstract class EvenementDebutAbstrait extends EvenementRobotAbstrait {
	private Simulateur simulateur;
	
	public EvenementDebutAbstrait(long date, Robot robot, Simulateur simulateur) {
		super(date, robot);
		this.simulateur=simulateur;
	}
	
	public EvenementDebutAbstrait(Robot robot, Simulateur simulateur) {
		super(robot);
		this.simulateur = simulateur;
	}
	

	

	
	public Simulateur getSimulateur() {
		return simulateur;
	}
	
	public void setSimulateur(Simulateur simulateur) {
		this.simulateur = simulateur;
	}

}
