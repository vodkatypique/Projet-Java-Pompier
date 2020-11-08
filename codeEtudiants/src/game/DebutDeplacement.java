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
		this.setDateFin((long) (distance/(vitesse/60)));//
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
