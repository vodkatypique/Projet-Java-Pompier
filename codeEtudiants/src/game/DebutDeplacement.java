package game;


public class DebutDeplacement extends EvenementDebutAbstrait {//TODO début déplacement fin déplacement, vitesse
	Direction direction;
	DonneesSimulation donneesSimulation;
	
	public DebutDeplacement(long date, Direction direction, Robot robot, DonneesSimulation donneesSimulation, Simulateur simulateur) {
		super(date, robot, simulateur);
		this.direction=direction;
		this.donneesSimulation=donneesSimulation;
		double vitesse =this.getRobot().getVitesse(this.getRobot().getPosition().getNature());
		System.out.println("Vitesse " + vitesse);
		this.donneesSimulation.getCarte();
		double distance=Carte.getDistanceEntreCase();
		long ldateFin;
		if(vitesse == 0)
			ldateFin = this.getDate();
		else
			ldateFin=(long) (distance / ((vitesse*Math.pow(10,3))/60)) + this.getDate();//la vitesse est km/h
		this.setDateFin(ldateFin);
	}
	
	@Override
	public void execute() { // il est occupe
		if(this.getRobot().getOccupationRobot().getEstOccupe()) {
			this.getSimulateur().ajouteEvenement(new DebutDeplacement(this.getRobot().getOccupationRobot().getDateFin()+1, direction, getRobot(), donneesSimulation, getSimulateur()));
			return;
		}
		else { // il n'etait pas occupe
			this.getRobot().getOccupationRobot().changeState();
			this.getRobot().getOccupationRobot().setDateFin(this.getDateFin());
			this.getSimulateur().ajouteEvenement(new FinDeplacement(this.getDateFin(), getRobot(), direction, donneesSimulation));	
		}
	}

}
