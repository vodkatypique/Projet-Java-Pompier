package game;

public class DebutExtinctionFeu extends EvenementDebutAbstrait {
	private DonneesSimulation donneesSimulation;
	private double volumeDeverse;
	
	public DebutExtinctionFeu( Robot robot, double intensiteIncendie,Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super( robot, simulateur);
		// this.setDateFin((long) (date + (robot.dureeDeversement(volumeDeverse)/60)));
		if(intensiteIncendie>this.getRobot().getReservoir()) {
			intensiteIncendie=this.getRobot().getReservoir();
		}
		this.setDate((long) ((robot.dureeDeversement(intensiteIncendie)/60)));

		this.donneesSimulation=donneesSimulation;
		this.volumeDeverse=volumeDeverse;
	}
	

	
	public DebutExtinctionFeu(Robot robot, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(robot, simulateur);
		/*int intensite = donneesSimulation.getIncendie(this.getRobot().getPosition()).getIntensite();
		if(robot.getReservoir() <= intensite)
			this.volumeDeverse= robot.getReservoir();
		else 
			this.volumeDeverse = intensite;*/
		this.volumeDeverse= robot.getReservoir();
		this.setDate((long) (this.getDate() + (robot.dureeDeversement(volumeDeverse)/60)));
		this.donneesSimulation=donneesSimulation;
		System.out.println("Date fin Extinction ::: " + this.getDate());
	}
	
	@Override
	public void execute() {
			this.getRobot().getOccupationRobot().setDateFin(this.getDate());
			//this.getSimulateur().ajouteEvenement(new FinExtinctionFeu(this.getDate(), this.getRobot(), this.donneesSimulation, this.volumeDeverse));	
			Incendie incendie=this.donneesSimulation.getIncendie(this.getRobot().getPosition());
			if(incendie==null) {
				this.getRobot().getOccupationRobot().setOccupationGenerale(false);
				return;
			}
			if(this.getRobot().getReservoir() <= incendie.getIntensite())
				this.volumeDeverse= this.getRobot().getReservoir();
			else
				this.volumeDeverse = incendie.getIntensite();
			incendie.decrementeIntensite(this.volumeDeverse);
			this.getRobot().setResevoir(this.getRobot().getReservoir()-this.volumeDeverse);
			// vu que ceci l'extinction est en relation avec un robot particulier on doit diminuer la 
			// quantit� de volume dont il dispose quand on diminue l'intensit� de l'incendie
			if (incendie.getIntensite() <= 0) {
				System.out.println("Incendie �teinte!!!!!!!!!! Congrats!!");
				donneesSimulation.getIncendies().remove(incendie);
			}
	
		
	}

}
