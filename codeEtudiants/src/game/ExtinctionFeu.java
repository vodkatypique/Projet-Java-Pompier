package game;

/**
 * The type Debut extinction feu.
 */
public class ExtinctionFeu extends EvenementRobotAbstrait {
	private DonneesSimulation donneesSimulation;
	private double volumeDeverse;

	/**
	 * Instantiates a new Debut extinction feu.
	 *
	 * @param robot             the robot
	 * @param intensiteIncendie the intensite incendie
	 * @param simulateur        the simulateur
	 * @param donneesSimulation the donnees simulation
	 */
	public ExtinctionFeu(Robot robot, double intensiteIncendie, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(robot, simulateur);
		if (intensiteIncendie > this.getRobot().getReservoir()) {
			intensiteIncendie = this.getRobot().getReservoir();
		}
		this.setDate((long) ((robot.dureeDeversement(intensiteIncendie) / 60)));

		this.donneesSimulation = donneesSimulation;
	}


	/**
	 * Instantiates a new Debut extinction feu.
	 *
	 * @param robot             the robot
	 * @param simulateur        the simulateur
	 * @param donneesSimulation the donnees simulation
	 */
	public ExtinctionFeu(Robot robot, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(robot, simulateur);
		this.volumeDeverse = robot.getReservoir();
		this.setDate((long) (this.getDate() + (robot.dureeDeversement(volumeDeverse) / 60)));
		this.donneesSimulation = donneesSimulation;
	}

	/**
	 * Execute.
	 */
	@Override
	public void execute() {
		this.getRobot().getOccupationRobot().setDateFin(this.getDate());
		Incendie incendie = this.donneesSimulation.getIncendie(this.getRobot().getPosition());
		if (incendie == null) {
			this.getRobot().getOccupationRobot().setOccupationGenerale(false);
			return;
		}
		if (this.getRobot().getReservoir() <= incendie.getIntensite())
			this.volumeDeverse= this.getRobot().getReservoir();
		else
			this.volumeDeverse = incendie.getIntensite();
		incendie.decrementeIntensite(this.volumeDeverse);
		this.getRobot().setResevoir(this.getRobot().getReservoir()-this.volumeDeverse);
		// vu que ceci l'extinction est en relation avec un robot particulier on doit diminuer la
		// quantité de volume dont il dispose quand on diminue l'intensité de l'incendie
		if (incendie.getIntensite() <= 0) {
			System.out.println("Incendie éteinte!!!!!!!!!! ");
			donneesSimulation.getIncendies().remove(incendie);
		}


	}

}
