/*
 * Test
 * 
 * 1.0
 *
 * 20/11/2020
 * 
 * Benjamin Cathelineau, Clément Caffin, Brown Ebouky
 */
package tests;

import game.Robot;
import game.*;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.DataFormatException;

/**
 * The type Test.
 */
public class Test {
	/**
	 * La classe de test contient les tests ainsi que la méthode pour lancer la simulation normalement
	 *
	 * @param args le nom de fichier doit être en args[0]
	 * @throws FileNotFoundException si le fichier n'est pas trouvé
	 * @throws DataFormatException si les données sont mal formatées dans le dossier
	 */
	public static void main(String[] args) throws FileNotFoundException, DataFormatException {

		if (args.length < 1) {
			System.err.println("Syntaxe: java tests.TestLecteurDonnees <nomDeFichier>");
			System.exit(1);
		}

		DonneesSimulation donneesSimulation;
		try {

			donneesSimulation = LecteurDonnees.creeDonnees(args[0]);
			GUISimulator gui = new GUISimulator(
					donneesSimulation.getCarte().getNbLignes() * 20,
					donneesSimulation.getCarte().getNbLignes() * 20,
					Color.BLACK);

			//scenario0(gui, donneesSimulation);
			//scenario1(gui, donneesSimulation);
			//verifiePlusCourtChemin(gui, donneesSimulation);
			lanceSimulation(gui, donneesSimulation);
			//scenario0(gui, donneesSimulation);


		} catch (FileNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
				| InvocationTargetException | DataFormatException e) {
			e.printStackTrace();
		}


	}
	private static void lanceSimulation(GUISimulator gui, DonneesSimulation donneesSimulation) {

		Simulateur sim = new Simulateur(gui, donneesSimulation);
		ChefPompier chefPompier = new ChefPompier(donneesSimulation.getRobots(), donneesSimulation.getCarte(), donneesSimulation.getIncendies(), sim);
		sim.setChefPompier(chefPompier);

		sim.start();
	}
	
	private static void scenario0(GUISimulator gui, DonneesSimulation donneesSimulation) {
		Simulateur sim = new Simulateur(gui, donneesSimulation);
		sim.ajouteEvenement(new Deplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));

		sim.ajouteEvenement(new Deplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement( Direction.SUD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement( Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement( Direction.EST, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));

		sim.start();
	}


	private static void scenario1(GUISimulator gui, DonneesSimulation donnees) {

		Simulateur sim = new Simulateur(gui, donnees);
		Robot robot = donnees.getRobots().get(1);
		sim.ajouteEvenement(new Deplacement(Direction.NORD, robot, donnees,sim),robot);
		sim.ajouteEvenement(new ExtinctionFeu(robot, sim, donnees),robot);
		sim.ajouteEvenement(new Deplacement(Direction.OUEST, robot, donnees, sim),robot);
		sim.ajouteEvenement(new Deplacement(Direction.OUEST, robot, donnees, sim),robot);
		sim.ajouteEvenement(new RemplissageReservoir(robot, sim),robot);
		sim.ajouteEvenement(new Deplacement(Direction.EST, robot, donnees, sim),robot);
		sim.ajouteEvenement(new Deplacement(Direction.EST, robot, donnees, sim),robot);
		sim.ajouteEvenement(new ExtinctionFeu(robot, sim, donnees),robot);


		sim.start();
	}

	private static void verifiePlusCourtChemin(GUISimulator gui, DonneesSimulation donnees) {
		Simulateur sim = new Simulateur(gui, donnees);

		PlusCourtChemin chemin = new PlusCourtChemin(sim.getDonneesSimulation().getRobots().get(0), sim.getCarte().getCase(6, 7), sim.getCarte());
		chemin.deplaceVersCase(sim);
	}


}
