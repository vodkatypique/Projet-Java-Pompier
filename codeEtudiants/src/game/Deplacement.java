package game;

public class Deplacement extends Evenement {//TODO début déplacement fin déplacement, vitesse
	private Robot robot;//le robot qui se déplace
	Direction direction;
	DonneesSimulation donneesSimulation;
	public Deplacement(long date, Direction direction, Robot robot, DonneesSimulation donneesSimulation) {
		super(date);
		this.direction=direction;
		this.robot=robot;
		this.donneesSimulation=donneesSimulation;
	}

	@Override
	void execute() {
		//TODO vitesse
		int lig=this.robot.getPosition().getLigne();
		int col=this.robot.getPosition().getColonne();
		switch (this.direction) {
		case EST:
			col++;
			break;
		case SUD:
			lig++;
			break;
		case OUEST:
			col--;
		case NORD:
			lig--;
		default:
			break;
		}
		if(lig>donneesSimulation.getCarte().getNbLignes()|| lig<0||col>donneesSimulation.getCarte().getNbLignes()||col<0) {
			System.err.println("Erreur, Position invalide après un déplacement");
			System.exit(-1);
			return;
		}
		
		this.robot.setPosition(donneesSimulation.getCarte().getCase(lig, col));
		// TODO Auto-generated method stub

	}

}
