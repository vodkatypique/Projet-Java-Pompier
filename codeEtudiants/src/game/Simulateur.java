package game;
import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;

public class Simulateur implements Simulable {
	/**
	 * L'interface graphique associée
	 */
	private GUISimulator gui;
	private DonneesSimulation donneesSimulation;
	private int tailleCase;


	/**
	 * Crée un Invader et le dessine.
	 *
	 * @param gui   l'interface graphique associée, dans laquelle se fera le
	 *              dessin et qui enverra les messages via les méthodes héritées de
	 *              Simulable.
	 * @param color la couleur de l'invader
	 */
	public Simulateur(GUISimulator gui, DonneesSimulation donneesSimulation) {
		this.gui = gui;
		this.donneesSimulation = donneesSimulation;
		this.tailleCase = 20;
		gui.setSimulable(this);                // association a la gui!

		draw();
	}


	/**
	 * Dessine l'invader.
	 */
	private void draw() {
		gui.reset();    // clear the window


		int cptX = 0;
		int cptY = 0;

		for (int i = 0; i < donneesSimulation.getCarte().getNbLignes(); i++) {
			for (int j = 0; j < donneesSimulation.getCarte().getNbColonnes(); j++) {
				switch (donneesSimulation.getCarte().getCase(i, j).getNature()) {
					case FORET:
						gui.addGraphicalElement(new gui.Rectangle(j * tailleCase, i * tailleCase, Color.GREEN, Color.GREEN, tailleCase));
						break;
					case TERRAIN_LIBRE:
						gui.addGraphicalElement(new gui.Rectangle(j * tailleCase, i * tailleCase, Color.WHITE, Color.WHITE, tailleCase));
						break;
					case HABITAT:
						gui.addGraphicalElement(new gui.Rectangle(j * tailleCase, i * tailleCase, Color.YELLOW, Color.YELLOW, tailleCase));
						break;
					case ROCHE:
						gui.addGraphicalElement(new gui.Rectangle(j * tailleCase, i * tailleCase, Color.DARK_GRAY, Color.DARK_GRAY, tailleCase));
						break;
					case EAU:
						gui.addGraphicalElement(new gui.Rectangle(j * tailleCase, i * tailleCase, Color.BLUE, Color.BLUE, tailleCase));
						break;
				}
			}
			cptY++;
		}


		for (Robot robot : donneesSimulation.getRobots()
		) {
			gui.addGraphicalElement(new gui.Oval(robot.getPosition().getColonne() * tailleCase, robot.getPosition().getLigne() * tailleCase, Color.YELLOW, Color.YELLOW, tailleCase));
		}

		for (Incendie incendie : donneesSimulation.getIncendies()
		) {
			gui.addGraphicalElement(new gui.Rectangle(incendie.getPosition().getColonne() * tailleCase, incendie.getPosition().getLigne() * tailleCase, Color.RED, Color.RED, tailleCase));
		}

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
