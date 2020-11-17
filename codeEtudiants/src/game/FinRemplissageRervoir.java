package game;

public class FinRemplissageRervoir extends EvenementRobotAbstrait {//TODO factorisation de tout les évènement robot
	private double volumeAjoute;
	
	public FinRemplissageRervoir(long date,Robot robot, double volumeAjoute) {
		super(date,robot);
		this.volumeAjoute = volumeAjoute;
	}
	
	@Override
	public void execute() {
		this.getRobot().setResevoir(this.getRobot().getReservoir() + this.volumeAjoute);
		this.getRobot().getOccupationRobot().changeState();
	}
	
	public long getDateFin() {
		return this.getDate();
	}

}
