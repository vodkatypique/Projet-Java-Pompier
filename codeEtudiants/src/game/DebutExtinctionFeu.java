package game;

public class DebutExtinctionFeu extends EvenementDebutAbstrait {
	private DonneesSimulation donneesSimulation;
	private double volumeDeverse;
	
	public DebutExtinctionFeu(long date, Robot robot, double volumeDeverse, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(date, robot, simulateur);
		// this.setDateFin((long) (date + (robot.dureeDeversement(volumeDeverse)/60)));
		this.setDate((long) (date + (robot.dureeDeversement(volumeDeverse)/60)));
		this.donneesSimulation=donneesSimulation;
		this.volumeDeverse=volumeDeverse;
	}
	
	public DebutExtinctionFeu(long date, Robot robot, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(date, robot, simulateur);
		// on calcule le temps pour l'extinction en minute(mesure globale) et associe ce temps � la date actuelle
		// pour calculer la date de fin de l'�v�nement.
		/*int intensite = donneesSimulation.getIncendie(this.getRobot().getPosition()).getIntensite();
		if(robot.getReservoir() <= intensite)
			this.volumeDeverse= robot.getReservoir();
		else 
			this.volumeDeverse = intensite;*/
		//this.setDateFin((long) (date + (robot.dureeDeversement(volumeDeverse)/60)));
		this.volumeDeverse= robot.getReservoir();
		this.setDate((long) (date + (robot.dureeDeversement(this.volumeDeverse)/60)));
		this.donneesSimulation=donneesSimulation;
		System.out.println("Date fin Extinction ::: " + this.getDate());
		
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
				//donneesSimulation.getIncendies().remove(incendie);
			}
			this.getRobot().getOccupationRobot().setOccupationGenerale(false);
	
		
	}

}
