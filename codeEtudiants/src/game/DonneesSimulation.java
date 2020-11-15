package game;

import java.util.ArrayList;

public class DonneesSimulation {
    private ArrayList<Incendie> incendies;
    private Carte carte;
    private ArrayList<Robot> robots;

    public DonneesSimulation(ArrayList<Incendie> incendies, ArrayList<Robot> robots, Carte carte) {
        this.incendies = incendies;
        this.carte = carte;
        this.robots = robots;
    }

    public DonneesSimulation(DonneesSimulation donnees) {
    	this.incendies = new ArrayList<Incendie>();
    	this.robots = new ArrayList<>();
    	for(Incendie inc : donnees.getIncendies()) {
    		this.incendies.add(new Incendie(inc));
    	}
    	for(Robot r: donnees.getRobots()) {
    		/*this.robots.add((Robot) Class
            .forName("game." + r.getClass())
            .getConstructor(Robot.class)
            .newInstance(r));*/
    		// pourra être modifié pour être plus propre
    		if(r instanceof Drone) {
    			this.robots.add(new Drone((Drone)r));
    			continue;
    		}
    		if(r instanceof Patte) {
    			this.robots.add(new Patte((Patte)r));
    			continue;
    		}
    		if(r instanceof Roue) {
    			this.robots.add(new Roue((Roue)r));
    			continue;
    		}
    		this.robots.add(new Chenille((Chenille)r));
    			
    	}
    	this.carte = new Carte(donnees.getCarte());
    }
    
    
    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }
    
    public Incendie getIncendie(Case position){
    	Incendie incendieRetour=null;
    	for (Incendie incendie : incendies) {
			if(incendie.getPosition().equals(position)) {
				incendieRetour=incendie;
				break;
			}
		}
    	return incendieRetour;
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
        return this.robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }
}
