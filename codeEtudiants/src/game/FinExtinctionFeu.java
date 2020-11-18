package game;

public class FinExtinctionFeu extends EvenementRobotAbstrait {
	private DonneesSimulation donneesSimulation;
	private double volume;
	public FinExtinctionFeu(long date, Robot robot,DonneesSimulation donneesSimulation, double volume) {
		super(date, robot);
		this.donneesSimulation=donneesSimulation;
		this.volume=volume;
	}

	@Override
	public void execute() {
		
		Incendie incendie=this.donneesSimulation.getIncendie(this.getRobot().getPosition());
		incendie.decrementeIntensite(this.volume);
		// vu que ceci l'extinction est en relation avec un robot particulier on doit diminuer la 
		// quantité de volume dont il dispose quand on diminue l'intensité de l'incendie
		if(incendie.getIntensite()<=0) {
			System.out.println("Incendie éteinte!!!!!!!!!! Congrats!!");
			donneesSimulation.getIncendies().remove(incendie);
		}
	}
	
	public long getDateFin() {
		return this.getDate();
	}

}
