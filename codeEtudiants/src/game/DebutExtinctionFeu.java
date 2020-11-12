package game;

public class DebutExtinctionFeu extends EvenementDebutAbstrait {
	private DonneesSimulation donneesSimulation;
	private double volumeDeverse;
	
	public DebutExtinctionFeu(long date, Robot robot, double volumeDeverse, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(date, robot, simulateur);
		this.setDateFin((long) (date + (robot.dureeDeversement(volumeDeverse)/60)));
		this.donneesSimulation=donneesSimulation;
		this.volumeDeverse=volumeDeverse;
	}
	
	public DebutExtinctionFeu(long date, Robot robot, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(date, robot, simulateur);
		this.setDateFin((long) (date + (robot.dureeDeversement(volumeDeverse)/60)));
		this.donneesSimulation=donneesSimulation;
		this.volumeDeverse= robot.getReservoir();
	}
	
	@Override
	public void execute() {
		if(this.getRobot().getOccupationRobot().getEstOccupe()) {
			this.getSimulateur().ajouteEvenement(new DebutExtinctionFeu(this.getRobot().getOccupationRobot().getDateFin()+1, getRobot(), volumeDeverse, getSimulateur(), donneesSimulation));
			return;
		}
		else {
			this.getRobot().getOccupationRobot().changeState();
			this.getRobot().getOccupationRobot().setDateFin(this.getDateFin());
			this.getSimulateur().ajouteEvenement(new FinExtinctionFeu(getDateFin(), getRobot(), donneesSimulation, volumeDeverse));	
		}	
		
	}

}
