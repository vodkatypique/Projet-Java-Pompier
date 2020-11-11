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
		if(!this.getRobot().peutRemplir(this.getSimulateur().getCarte())) {
			System.err.println("Erreur, le robot ne peut pas se remplir");
			return;
		}
			
		if(this.getRobot().occupationRobot.estOccupe) {
			this.getSimulateur().ajouteEvenement(new DebutRemplissageReservoir(this.getRobot().occupationRobot.dateFin+1, getRobot(), volume, getSimulateur()));
			return;
		}else {
			this.getRobot().occupationRobot.estOccupe=Boolean.TRUE;
			this.getRobot().occupationRobot.dateFin=this.getDateFin();
			this.getSimulateur().ajouteEvenement(new FinRemplissageRervoir(this.getDateFin(), this.getRobot(), this.volume));
		}
	}

}