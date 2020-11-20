/*
 * DonneesSimulation
 * 
 * 1.0
 *
 * 20/11/2020
 * 
 * Benjamin Cathelineau, Cl�ment Caffin, Brown Ebouky
 */
package game;

import java.util.ArrayList;

/**
 * The type Donnees simulation, represente l'integralité des données.
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
     * Gets incendies.
     *
     * @return the incendies
     */
    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    /**
     * Get incendie a une case specifié
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
