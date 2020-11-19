package game;

import java.util.ArrayList;

/**
 * The type Donnees simulation.
 */
public class DonneesSimulation {
    private ArrayList<Incendie> incendies;
    private Carte carte;
    private ArrayList<Robot> robots;

    /**
     * Instantiates a new Donnees simulation.
     *
     * @param incendies the incendies
     * @param robots    the robots
     * @param carte     the carte
     */
    public DonneesSimulation(ArrayList<Incendie> incendies, ArrayList<Robot> robots, Carte carte) {
        this.incendies = incendies;
        this.carte = carte;
        this.robots = robots;
    }

    /**
     * Instantiates a new Donnees simulation.
     *
     * @param donnees the donnees
     */
    public DonneesSimulation(DonneesSimulation donnees) {
        this.incendies = new ArrayList<Incendie>();
        this.robots = new ArrayList<>();
        for (Incendie inc : donnees.getIncendies()) {
            this.incendies.add(new Incendie(inc));
        }
        for (Robot r : donnees.getRobots()) {
    		/*this.robots.add((Robot) Class
            .forName("game." + r.getClass())
            .getConstructor(Robot.class)
            .newInstance(r));*/
            // pourra �tre modifi� pour �tre plus propre
            if(r instanceof Drone) {
                this.robots.add(new Drone((Drone)r));
                continue;
            }
            if(r instanceof Patte) {
                this.robots.add(new Patte((Patte)r));
                continue;
            }
            if (r instanceof Roue) {
                this.robots.add(new Roue((Roue) r));
                continue;
            }
            this.robots.add(new Chenille((Chenille) r));

        }
        this.carte = new Carte(donnees.getCarte());
    }


    /**
     * Gets incendies.
     *
     * @return the incendies
     */
    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    /**
     * Get incendie incendie.
     *
     * @param position the position
     * @return the incendie
     */
    public Incendie getIncendie(Case position) {
        Incendie incendieRetour = null;
        for (Incendie incendie : incendies) {
            if (incendie.getPosition().equals(position)) {
                incendieRetour = incendie;
                break;
            }
        }
        return incendieRetour;
    }

    /**
     * Init incendies.
     */
    public void initIncendies() {
        this.incendies = new ArrayList<Incendie>();
    }

    /**
     * Init robots.
     */
    public void initRobots() {
        this.robots = new ArrayList<Robot>();
    }

    /**
     * Add robot.
     *
     * @param robot the robot
     */
    public void addRobot(Robot robot) {
        this.robots.add(robot);
    }

    /**
     * Gets robots.
     *
     * @return the robots
     */
    public ArrayList<Robot> getRobots() {
        return this.robots;
    }

    /**
     * Gets carte.
     *
     * @return the carte
     */
    public Carte getCarte() {
        return carte;
    }

    /**
     * Sets carte.
     *
     * @param carte the carte
     */
    public void setCarte(Carte carte) {
        this.carte = carte;
    }
}
