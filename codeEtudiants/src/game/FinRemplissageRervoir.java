package game;

public class FinRemplissageRervoir extends EvenementRobotAbstrait {//TODO factorisation de tout les évènement robot
	private double volumeAjoute;
	public FinRemplissageRervoir(long date,Robot robot, double volumeAjoute) {
		super(date,robot);
		this.volumeAjoute=volumeAjoute;
	}
	@Override
	void execute() {
		this.getRobot().occupationRobot.estOccupe=Boolean.FALSE;
		this.getRobot().setResevoir(this.getRobot().getReservoir()+this.volumeAjoute);

	}

}
