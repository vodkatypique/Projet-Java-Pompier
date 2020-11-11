package game;

public class FinExtinctionFeu extends EvenementRobotAbstrait {
	DonneesSimulation donneesSimulation;
	double volume;
	public FinExtinctionFeu(long date, Robot robot,DonneesSimulation donneesSimulation, double volume) {
		super(date, robot);
		this.donneesSimulation=donneesSimulation;
		this.volume=volume;
	}

	@Override
	void execute() {
		this.getRobot().occupationRobot.estOccupe=Boolean.FALSE;
		Incendie incendie=this.donneesSimulation.getIncendie(this.getRobot().getPosition());
		incendie.decrementeIntensite(this.volume);
		if(incendie.getIntensite()<=0) {
			donneesSimulation.getIncendies().remove(incendie);
		}
		
	}

}
