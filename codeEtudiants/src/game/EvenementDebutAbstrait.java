package game;

public abstract class EvenementDebutAbstrait extends EvenementRobotAbstrait {
	private long dateFin;
	private Simulateur simulateur;
	
	public EvenementDebutAbstrait(long date, Robot robot, Simulateur simulateur) {
		super(date, robot);
		this.simulateur=simulateur;
	}
	
	public EvenementDebutAbstrait(Robot robot, Simulateur simulateur) {
		super(robot);
		this.simulateur = simulateur;
	}
	
	public long getDateFin() {
		return this.dateFin;
	}
	
	public void setDateFin(long dateFin) {
		if(this.getDate() == dateFin)
			++dateFin;//toute action prend au moins 1 minutes
		this.dateFin = dateFin;
	}
	
	public Simulateur getSimulateur() {
		return simulateur;
	}
	
	public void setSimulateur(Simulateur simulateur) {
		this.simulateur = simulateur;
	}

}