package game;

public class DebutRemplissageReservoir extends EvenementDebutAbstrait {
	double volume;
	public DebutRemplissageReservoir(long date,Robot robot,double volume, Simulateur simulateur) {
		super(date,robot,simulateur);
		this.setDateFin(this.getRobot().dureeRemplissageReservoir(volume));
		this.volume=volume;
	}
	@Override
	void execute() {
		this.getRobot().occupe=Boolean.TRUE;
		this.getSimulateur().ajouteEvenement(new FinRemplissageRervoir(this.getDateFin(), this.getRobot(), this.volume));
	}

}