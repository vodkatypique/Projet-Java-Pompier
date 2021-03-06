package game;


/**
 * The type Debut deplacement.
 */
public class Deplacement extends EvenementRobotAbstrait {
	/**
	 * The Direction.
	 */
	Direction direction;
	/**
	 * The Donnees simulation.
	 */
	DonneesSimulation donneesSimulation;
	/**
	 * Instantiates a new Deplacement.
	 *
	 * @param direction         the direction
	 * @param robot             the robot
	 * @param donneesSimulation the donnees simulation
	 * @param simulateur        the simulateur
	 */
	public Deplacement(Direction direction, Robot robot, DonneesSimulation donneesSimulation, Simulateur simulateur) {
		super(robot, simulateur);
		this.direction = direction;
		this.donneesSimulation = donneesSimulation;
		double vitesse = this.getRobot().getVitesse(this.getRobot().getPosition().getNature());
		this.donneesSimulation.getCarte();
		double distance = this.donneesSimulation.getCarte().getDistanceEntreCase();
		long ldateFin;
		if (vitesse == 0)
			ldateFin = this.getDate();
		else
			ldateFin = (long) (distance / ((vitesse * Math.pow(10, 3)) / 60));//la vitesse est km/h
		this.setDate(ldateFin);

	}


	/**
	 * Execute le deplacement en faisant bouger le robot sur la carte avec le deplacement courant
	 */
	@Override
	public void execute() {

		int lig = this.getRobot().getPosition().getLigne();
		int col = this.getRobot().getPosition().getColonne();
		switch (this.direction) {
			case EST:
				col++;
				break;
			case SUD:
				lig++;
				break;
			case OUEST:
				col--;
				break;
			case NORD:
				lig--;
				break;
			default:
				break;
		}
		if (lig > donneesSimulation.getCarte().getNbLignes() - 1 || lig < 0 || col > donneesSimulation.getCarte().getNbLignes() - 1 || col < 0) {
			System.err.println("Erreur, Position invalide apres le deplacement");
			System.exit(-1);
			return;
		}

		this.getRobot().setPosition(donneesSimulation.getCarte().getCase(lig, col));
		
	}


}
