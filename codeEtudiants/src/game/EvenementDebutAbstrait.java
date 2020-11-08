package game;

public abstract class EvenementDebutAbstrait extends EvenementRobotAbstrait {
	private long dateFin;
	private Simulateur simulateur;
	public EvenementDebutAbstrait(long date, Robot robot, Simulateur simulateur) {
		super(date, robot);
		this.simulateur=simulateur;
	}
	public long getDateFin() {
		return dateFin;
	}
	public void setDateFin(long dateFin) {
		this.dateFin = dateFin;
	}
	public Simulateur getSimulateur() {
		return simulateur;
	}
	public void setSimulateur(Simulateur simulateur) {
		this.simulateur = simulateur;
	}

}
