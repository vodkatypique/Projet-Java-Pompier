package game;

public class DebutExtinctionFeu extends EvenementDebutAbstrait {
	private DonneesSimulation donneesSimulation;
	private double volumeDeverse;
	public DebutExtinctionFeu(long date, Robot robot, double volumeDeverse, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(date, robot, simulateur);
		this.setDateFin((long) (date+(robot.dureeDeversement(volumeDeverse)/60)));
		this.donneesSimulation=donneesSimulation;
		this.volumeDeverse=volumeDeverse;
	}
	@Override
	void execute() {
		if(this.getRobot().occupationRobot.estOccupe) {
			this.getSimulateur().ajouteEvenement(new DebutExtinctionFeu(this.getRobot().occupationRobot.dateFin+1, getRobot(), volumeDeverse, getSimulateur(), donneesSimulation));
			return;
		}else {
			this.getRobot().occupationRobot.estOccupe=Boolean.TRUE;
			this.getRobot().occupationRobot.dateFin=this.getDateFin();
			this.getSimulateur().ajouteEvenement(new FinExtinctionFeu(getDateFin(), getRobot(), donneesSimulation, volumeDeverse));	
		}	}

}
