package game;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

public class TestAffichageCarte implements Simulable {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
		// TODO Auto-generated method stub
		DonneesSimulation d=new DonneesSimulation();
		d.initRobots();
		d.addRobot(new Chenille(new Case(5, 5, NatureTerrain.EAU)));
		d.setCarte(new Carte(5, 5, 20));
		new Simulateur(gui, d);
		}
	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

}
