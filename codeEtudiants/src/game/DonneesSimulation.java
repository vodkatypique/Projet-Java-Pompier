package game;

import java.util.ArrayList;

public class DonneesSimulation {
    private ArrayList<Incendie> incendies;
    private Carte carte;
    private ArrayList<Robot> robots;

    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    public void setIncendies(ArrayList<Incendie> incendies) {
        this.incendies = incendies;
    }

    public void initIncendies() {
        this.incendies = new ArrayList<Incendie>();
    }

    public void initRobots() {
        this.robots = new ArrayList<Robot>();
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }
}
