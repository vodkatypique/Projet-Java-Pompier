package game;


public class DebutDeplacement extends EvenementDebutAbstrait {//TODO début déplacement fin déplacement, vitesse
	Direction direction;
	DonneesSimulation donneesSimulation;
	public DebutDeplacement(long date, Direction direction, Robot robot, DonneesSimulation donneesSimulation, Simulateur simulateur) {
		super(date, robot, simulateur);
		this.direction=direction;
		this.donneesSimulation=donneesSimulation;
		double vitesse =this.getRobot().getVitesse(this.getRobot().getPosition().getNature());
		this.donneesSimulation.getCarte();
		double distance=Carte.getDistanceEntreCase();
		long ldateFin=(long) (distance/((vitesse*Math.pow(10,3))/60))+this.date;
		if(ldateFin==date)//le déplacement prend au moment 1 minute
			++ldateFin;
		this.setDateFin(ldateFin);//la vitesse est km/h
	}
	
	@Override
	void execute() {
		if(this.getRobot().occupationRobot.estOccupe) {
			this.getSimulateur().ajouteEvenement(new DebutDeplacement(this.getRobot().occupationRobot.dateFin+1, direction, getRobot(), donneesSimulation, getSimulateur()));
			return;
		}else {
			this.getRobot().occupationRobot.estOccupe=Boolean.TRUE;
			this.getRobot().occupationRobot.dateFin=this.getDateFin();
			this.getSimulateur().ajouteEvenement(new FinDeplacement(this.getDateFin(), getRobot(), direction, donneesSimulation));	
		}
	}

}
