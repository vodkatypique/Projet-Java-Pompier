package game;

public class FinDeplacement extends EvenementRobotAbstrait {
	private Direction direction;
	private DonneesSimulation donneesSimulation;
	public FinDeplacement(long date, Robot robot,Direction direction, DonneesSimulation donneesSimulation) {
		super(date, robot);
		this.direction=direction;
		this.donneesSimulation=donneesSimulation;
	}

	@Override
	void execute() {
		this.getRobot().occupationRobot.estOccupe=Boolean.FALSE;
		//this.getSimulateur().ajouteEvenement(new Fin);
		int lig=this.getRobot().getPosition().getLigne();
		int col=this.getRobot().getPosition().getColonne();
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
		if(lig>donneesSimulation.getCarte().getNbLignes()|| lig<0||col>donneesSimulation.getCarte().getNbLignes()||col<0) {
			System.err.println("Erreur, Position invalide après un déplacement");
			System.exit(-1);
			return;
		}
		
		this.getRobot().setPosition(donneesSimulation.getCarte().getCase(lig, col));
	}

}
