package game;


/**
 * The type Debut remplissage reservoir.
 */
public class RemplissageReservoir extends EvenementRobotAbstrait {
    private double volume;





    /**
     * Instantiates a new Debut remplissage reservoir.
     *
     * @param robot      the robot
     * @param simulateur the simulateur
     */
    public RemplissageReservoir(Robot robot, Simulateur simulateur) {
        super(robot, simulateur);
        this.volume = robot.getReservoirMax() - robot.getReservoir();
        this.setDate(this.getDate() + this.getRobot().dureeRemplissageReservoir(this.volume));
    }

    /**
     * Execute.
     */
    @Override
    public void execute() {
        if (!this.getRobot().peutRemplir(this.getSimulateur().getCarte())) {
            System.err.println("Erreur, le robot ne peut pas se remplir");
            return;
        }
        this.getRobot().setResevoir(this.getRobot().getReservoir() + this.volume);
        this.getRobot().getOccupationRobot().setOccupationGenerale(false);

    }

}