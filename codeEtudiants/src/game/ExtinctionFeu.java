package game;

/**
 * Evenement de debut d'extinction de feu
 */
public class ExtinctionFeu extends EvenementRobotAbstrait {
	private DonneesSimulation donneesSimulation;
	private double volumeDeverse;

	/**
	 * Instantiates a new Debut extinction feu.
	 *
	 * @param robot             the robot
	 * @param intensiteIncendie the intensite of incendie
	 * @param simulateur        the simulateur
	 * @param donneesSimulation the donneesSimulation
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
	 * @param donneesSimulation the donneesSimulation
	 */
	public ExtinctionFeu(Robot robot, Simulateur simulateur, DonneesSimulation donneesSimulation) {
		super(robot, simulateur);
		this.volumeDeverse = robot.getReservoir();
		this.setDate((long) (this.getDate() + (robot.dureeDeversement(volumeDeverse) / 60)));
		this.donneesSimulation = donneesSimulation;
	}

	/**
	 * Execute l'evenement de debut d'extinction
	 * evidemment, le contenu du reservoir est decrementé pour le  robot
	 * On peut se permettre de le faire pour tout les type de robot, car certains on un reservoir plein
	 * avec la valeur Maximale geré par le programme.
	 */
	@Override
	public void execute() {

		Incendie incendie = this.donneesSimulation.getIncendie(this.getRobot().getPosition());
		if (incendie == null) {
			this.getRobot().setOccupationGenerale(false);
			return;
		}
		if (this.getRobot().getReservoir() <= incendie.getIntensite())
			this.volumeDeverse= this.getRobot().getReservoir();
		else
			this.volumeDeverse = incendie.getIntensite();
		incendie.decrementeIntensite(this.volumeDeverse);
		this.getRobot().setResevoir(this.getRobot().getReservoir()-this.volumeDeverse);
		if (incendie.getIntensite() <= 0) {
			System.out.println("Incendie eteint!!!!!!!!!! ");
			donneesSimulation.getIncendies().remove(incendie);
		}


	}

}
