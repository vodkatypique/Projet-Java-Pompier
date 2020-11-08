package game;

public class DebutExtinctionFeu extends EvenementDebutAbstrait {
	private DonneesSimulation donneesSimulation;
	private double volumeDeverse;
	public DebutExtinctionFeu(long date, Robot robot, double volumeDeverse, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(date, robot, simulateur);
		this.setDateFin((long) (date+robot.dureeDeversement(volumeDeverse)));
		this.donneesSimulation=donneesSimulation;
		this.volumeDeverse=volumeDeverse;
		// TODO Auto-generated constructor stub
	}
	@Override
	void execute() {
		// TODO Auto-generated method stub
		this.getRobot().occupe=Boolean.TRUE;
		this.getSimulateur().ajouteEvenement(new FinExtinctionFeu(getDateFin(), getRobot(), donneesSimulation, volumeDeverse));
	}

}
