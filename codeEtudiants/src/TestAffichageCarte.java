import game.Robot;
import game.*;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestAffichageCarte  {
	public static void main(String[] args) throws FileNotFoundException, DataFormatException {

		if (args.length < 1) {
			System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
			System.exit(1);
		}

		DonneesSimulation donneesSimulation = LecteurDonnees.creeDonnees(args[0]);

		for (Robot r :
				donneesSimulation.getRobots()) {
			System.out.println(r);
		}

		GUISimulator gui = new GUISimulator(
				donneesSimulation.getCarte().getNbLignes() * 20,
				donneesSimulation.getCarte().getNbLignes() * 20,
				Color.BLACK);
		// crée l'invader, en l'associant à la fenêtre graphique précédente
		Simulateur sim = new Simulateur(gui, donneesSimulation);


		sim.ajouteEvenement(new Deplacement(1, Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation));
		sim.start();
	}
}

