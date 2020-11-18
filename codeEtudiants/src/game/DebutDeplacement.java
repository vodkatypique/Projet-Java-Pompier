package game;


public class DebutDeplacement extends EvenementDebutAbstrait {//TODO début déplacement fin déplacement, vitesse
	Direction direction;
	DonneesSimulation donneesSimulation;
	
	public DebutDeplacement(long date, Direction direction, Robot robot, DonneesSimulation donneesSimulation, Simulateur simulateur) {
		super(date, robot, simulateur);
		this.direction=direction;
		this.donneesSimulation=donneesSimulation;
		double vitesse =this.getRobot().getVitesse(this.getRobot().getPosition().getNature());
		//System.out.println("Vitesse " + vitesse);
		this.donneesSimulation.getCarte();
		double distance=Carte.getDistanceEntreCase();
		long ldateFin;
		if(vitesse == 0)
			ldateFin = this.getDate();
		else
			ldateFin=(long) (distance / ((vitesse*Math.pow(10,3))/60)) + this.getDate();//ceci parce que la vitesse est en km/h
		//this.setDateFin(ldateFin);
		
		this.setDate(ldateFin);
		System.out.println("Date fin Deplacement ::: " + this.getDate() + "  " + this);
	}
	
	public DebutDeplacement(Direction direction, Robot robot, DonneesSimulation donneesSimulation, Simulateur simulateur) {
		// TODO Auto-generated constructor stub
		super(robot, simulateur);
		this.direction=direction;
		this.donneesSimulation=donneesSimulation;
		double vitesse =this.getRobot().getVitesse(this.getRobot().getPosition().getNature());
		System.out.println("Vitesse " + vitesse);
		this.donneesSimulation.getCarte();
		double distance=Carte.getDistanceEntreCase();
		long ldateFin;
		if(vitesse == 0)
			ldateFin = this.getDate();
		else
			ldateFin=(long) (distance / ((vitesse*Math.pow(10,3))/60)) ;//la vitesse est km/h
		//this.setDateFin(ldateFin);
		this.setDate(ldateFin);
		//System.out.println("Date fin Deplacement ::: " + this.getDate() + " " + this);
		
	}
	
	
	@Override
	public void execute() { // il est occupe
		if(this.getRobot().getOccupationRobot().getEstOccupe()) {
			System.out.println("Je dois attendre un peu " + this);
			//this.getSimulateur().ajouteEvenement(new DebutDeplacement(this.getRobot().getOccupationRobot().getDateFin()+1, direction, getRobot(), donneesSimulation, getSimulateur()));
			this.setDate(this.getRobot().getOccupationRobot().getDateFin()+this.getDate());
			return;
		}
		else { // il n'etait pas occupe
			this.getRobot().getOccupationRobot().changeState();
			this.getRobot().getOccupationRobot().setDateFin(this.getDate());
			/*this.getRobot().getOccupationRobot().setDateFin(this.getDateFin());
			this.getSimulateur().ajouteEvenement(new FinDeplacement(this.getDateFin(), getRobot(), direction, donneesSimulation));	*/
			
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
			if (lig > donneesSimulation.getCarte().getNbLignes() - 1 || lig < 0 || col > donneesSimulation.getCarte().getNbLignes() - 1 || col < 0) {
				System.err.println("Erreur, Position invalide apr�s le deplacement");
				System.exit(-1);
				return;
			}

			this.getRobot().setPosition(donneesSimulation.getCarte().getCase(lig, col));
			// System.out.println("Ligne:: " + lig + " Apr�s dedans:: " + this.getRobot().getPosition().getLigne());
			// apr�s avoir deplac� le robot on le met en �tat non occup�.
			this.getRobot().getOccupationRobot().changeState();
		}
	}

}
