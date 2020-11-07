package game;

import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Simulateur implements Simulable {
	/**
	 * L'interface graphique associée
	 */
	private GUISimulator gui;
	private DonneesSimulation donneesSimulation;
	private int tailleCase;
	private long dateSimulation;
	private ArrayList<Evenement> evenements=new ArrayList<Evenement>();

	/**
	 * Crée un Invader et le dessine.
	 *
	 * @param gui   l'interface graphique associée, dans laquelle se fera le dessin
	 *              et qui enverra les messages via les méthodes héritées de
	 *              Simulable.
	 * @param color la couleur de l'invader
	 */
	public Simulateur(GUISimulator gui, DonneesSimulation donneesSimulation) {
		this.gui = gui;
		this.donneesSimulation = donneesSimulation;
		this.tailleCase = 20;
		this.dateSimulation=0;
	}
	public void ajouteEvenement(Evenement evenement) {
		if(evenement.date<this.dateSimulation) {
			System.err.println("Erreur, evenement dans le passé");
			return;
		}
		this.evenements.add(evenement);
	}
	public void start() {
		draw();
	}
	private void draw() {
		gui.setSimulable(this);
		gui.reset(); // clear the window

		int cptX = 0;
		int cptY = 0;

		for (int i = 0; i < donneesSimulation.getCarte().getNbLignes(); i++) {
			for (int j = 0; j < donneesSimulation.getCarte().getNbColonnes(); j++) {
				switch (donneesSimulation.getCarte().getCase(i, j).getNature()) {
				case FORET:
					gui.addGraphicalElement(
							new gui.Rectangle(j * tailleCase, i * tailleCase, Color.GREEN, Color.GREEN, tailleCase));
					break;
				case TERRAIN_LIBRE:
					gui.addGraphicalElement(
							new gui.Rectangle(j * tailleCase, i * tailleCase, Color.WHITE, Color.WHITE, tailleCase));
					break;
				case HABITAT:
					gui.addGraphicalElement(
							new gui.Rectangle(j * tailleCase, i * tailleCase, Color.CYAN, Color.CYAN, tailleCase));
					break;
				case ROCHE:
					gui.addGraphicalElement(new gui.Rectangle(j * tailleCase, i * tailleCase, Color.DARK_GRAY,
							Color.DARK_GRAY, tailleCase));
					break;
				case EAU:
					gui.addGraphicalElement(
							new gui.Rectangle(j * tailleCase, i * tailleCase, Color.BLUE, Color.BLUE, tailleCase));
					break;
				}
			}
			cptY++;
		}

		for (Robot robot : donneesSimulation.getRobots()) {
			gui.addGraphicalElement(new gui.Oval(robot.getPosition().getColonne() * tailleCase,
					robot.getPosition().getLigne() * tailleCase, Color.YELLOW, Color.YELLOW, tailleCase));
		}

		for (Incendie incendie : donneesSimulation.getIncendies()) {
			gui.addGraphicalElement(new gui.Rectangle(incendie.getPosition().getColonne() * tailleCase,
					incendie.getPosition().getLigne() * tailleCase, Color.RED, Color.RED, tailleCase));
		}

	}

	void incrememteDate() {
		++this.dateSimulation;
		Iterator<Evenement> itr=this.evenements.iterator();
		while(itr.hasNext()) {//Execution des évènement
			Evenement evenement=itr.next();
			if(evenement.date<=this.dateSimulation) {
				evenement.execute();
				itr.remove();
			}
		}
		this.draw();
	}

	@Override
	public void next() {
		System.out.println("d");
		incrememteDate();
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

}
