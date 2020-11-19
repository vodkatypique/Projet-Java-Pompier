package game;


public class DebutRemplissageReservoir extends EvenementDebutAbstrait {
	private double volume;
	
	public DebutRemplissageReservoir(long date,Robot robot,double volume, Simulateur simulateur) {
		super(date,robot,simulateur);
		//this.setDateFin(date + this.getRobot().dureeRemplissageReservoir(volume));
		this.setDate(date + this.getRobot().dureeRemplissageReservoir(volume));
		this.volume=volume;
		System.out.println("Date fin Remplissage ::: " + this.getDate());
	}
	
	public DebutRemplissageReservoir(long date, Robot robot, Simulateur simulateur) {
		super(date, robot, simulateur);
		this.volume = robot.getReservoirMax();
		//this.setDateFin(date + this.getRobot().dureeRemplissageReservoir(this.volume));
		this.setDate(date + this.getRobot().dureeRemplissageReservoir(this.volume));
		System.out.println("Date fin Remplissage ::: " + this.getDate());
	}
	
	public DebutRemplissageReservoir(Robot robot, Simulateur simulateur) {
		super(robot, simulateur);
		this.volume = robot.getReservoirMax();
		//this.setDateFin(this.getRobot().dureeRemplissageReservoir(this.volume));
		this.setDate(this.getRobot().dureeRemplissageReservoir(this.volume));
	}
	
	@Override
	public void execute() {   
		if(!this.getRobot().peutRemplir(this.getSimulateur().getCarte())) {
			System.err.println("Erreur, le robot ne peut pas se remplir");
			return;
		}
			

		this.getRobot().getOccupationRobot().setDateFin(this.getDate());
		//this.getSimulateur().ajouteEvenement(new FinRemplissageRervoir(this.getDateFin(), this.getRobot(), this.volume));
		this.getRobot().setResevoir(this.volume);
		this.getRobot().getOccupationRobot().setOccupationGenerale(false);

	}

	@Override
	public void updateDateFin(long date) {
		// TODO Auto-generated method stub
		double volumeActuel = this.getRobot().getReservoir();
		long duree = this.getRobot().dureeRemplissageReservoir(this.getRobot().getReservoirMax() - volumeActuel);
		this.setDate(duree + date);
		
	}

}