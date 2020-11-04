package game;

import java.util.ArrayList;

public class DonneesSimulation {
    private ArrayList<Incendie> incendies;
    private Carte carte;
    private ArrayList<Robot> robots;

    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    public void initIncendies() {
        this.incendies = new ArrayList<Incendie>();
    }
    
    public void initRobots() {
        this.robots = new ArrayList<Robot>();
    }
    public void addRobot(Robot robot) {
    	this.robots.add(robot);
	}
    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }
}
