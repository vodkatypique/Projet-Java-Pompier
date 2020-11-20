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
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws FileNotFoundException, DataFormatException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {

		if (args.length < 1) {
			System.err.println("Syntaxe: java tests.TestLecteurDonnees <nomDeFichier>");
			System.exit(1);
		}
		GUISimulator gui = new GUISimulator(
				 500,
				 500,
				Color.BLACK);
		
		//Scenarios de test
		// faire les tests en les considerant individuellement
		//scenario0(gui, args[0]);
		//scenario1(gui, args[0]);
		
		//Vrai simulation qui fait intervenir le chef pompier
		lanceSimulation(gui, args[0]);
			
	}
	private static void lanceSimulation(GUISimulator gui, String cheminDonnees) {

		Simulateur sim = new Simulateur(gui, cheminDonnees); 
		sim.start();
	}
	
	private static void scenario0(GUISimulator gui, String cheminDonnees) throws FileNotFoundException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, DataFormatException {
		DonneesSimulation donneesSimulation=LecteurDonnees.creeDonnees(cheminDonnees);
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


	private static void scenario1(GUISimulator gui, String cheminDonnees) throws FileNotFoundException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, DataFormatException {
		DonneesSimulation donnees=LecteurDonnees.creeDonnees(cheminDonnees);
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



}
