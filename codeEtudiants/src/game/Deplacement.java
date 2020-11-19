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
	 * Instantiates a new Déplacement.
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
	 * Execute.
	 */
	@Override
	public void execute() {
		this.getRobot().getOccupationRobot().setDateFin(this.getDate());
			/*this.getRobot().getOccupationRobot().setDateFin(this.getDateFin());
			this.getSimulateur().ajouteEvenement(new FinDeplacement(this.getDateFin(), getRobot(), direction, donneesSimulation));	*/

		//this.getSimulateur().ajouteEvenement(new Fin);
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
			System.err.println("Erreur, Position invalide aprï¿½s le deplacement");
			System.exit(-1);
			return;
		}

		this.getRobot().setPosition(donneesSimulation.getCarte().getCase(lig, col));
		// //System.out.println("Ligne:: " + lig + " Aprï¿½s dedans:: " + this.getRobot().getPosition().getLigne());
		// aprï¿½s avoir deplacï¿½ le robot on le met en ï¿½tat non occupï¿½.
	}


}
