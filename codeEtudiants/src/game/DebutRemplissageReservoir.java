package game;

public class DebutRemplissageReservoir extends EvenementDebutAbstrait {
	double volume;
	public DebutRemplissageReservoir(long date,Robot robot,double volume, Simulateur simulateur) {
		super(date,robot,simulateur);
		this.setDateFin(this.getRobot().dureeRemplissageReservoir(volume));
		this.volume=volume;
	}
	@Override
	public void execute() {
		if(!this.getRobot().peutRemplir(this.getSimulateur().getCarte())) {
			System.err.println("Erreur, le robot ne peut pas se remplir");
			return;
		}
			
		if(this.getRobot().getOccupationRobot().getEstOccupe()) {
			this.getSimulateur().ajouteEvenement(new DebutRemplissageReservoir(this.getRobot().getOccupationRobot().getDateFin()+1, getRobot(), volume, getSimulateur()));
			return;
		}else {
			this.getRobot().getOccupationRobot().changeState();
			this.getRobot().getOccupationRobot().setDateFin(this.getDateFin());
			this.getSimulateur().ajouteEvenement(new FinRemplissageRervoir(this.getDateFin(), this.getRobot(), this.volume));
		}
	}

}