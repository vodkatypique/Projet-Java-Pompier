package game;


/**
 * The type Evenement Debut remplissage reservoir.
 */
public class RemplissageReservoir extends EvenementRobotAbstrait {

    /**
     * Instantiates a new Debut remplissage reservoir.
     *
     * @param robot      the robot
     * @param simulateur the simulateur
     */
    public RemplissageReservoir(Robot robot, Simulateur simulateur) {
        super(robot, simulateur);
        this.setDate(this.getDate() + this.getRobot().dureeRemplissageReservoir(this.getRobot().getReservoirMax()));
    }

    /**
     * Execute l'evenement de debut de remplissage
     */
    @Override
    public void execute() {
        if (!this.getRobot().peutRemplir(this.getSimulateur().getCarte())) {
            System.err.println("Erreur, le robot ne peut pas se remplir");
            return;
        }
        this.getRobot().setResevoir(this.getRobot().getReservoirMax());
        this.getRobot().setOccupationGenerale(false);

    }

}