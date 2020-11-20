/*
 * Test
 * 
 * 1.0
 *
 * 20/11/2020
 * 
 * Benjamin Cathelineau, Cl�ment Caffin, Brown Ebouky
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
	 * La classe de test contient les tests ainsi que la m�thode pour lancer la simulation normalement
	 *
	 * @param args le nom de fichier doit �tre en args[0]
	 * @throws FileNotFoundException si le fichier n'est pas trouv�
	 * @throws DataFormatException si les donn�es sont mal format�es dans le dossier
	 */
	public static void main(String[] args) throws FileNotFoundException, DataFormatException {

		if (args.length < 1) {
			System.err.println("Syntaxe: java tests.TestLecteurDonnees <nomDeFichier>");
			System.exit(1);
		}

		DonneesSimulation donneesSimulation;


			GUISimulator gui = new GUISimulator(
					 500,
					 500,
					Color.BLACK);

			//scenario0(gui, donneesSimulation);
			//scenario1(gui, donneesSimulation);
			//verifiePlusCourtChemin(gui, donneesSimulation);
			lanceSimulation(gui, args[0]);
			//scenario0(gui, args[0]);





	}
	private static void lanceSimulation(GUISimulator gui, String chemineDonnees) {

		Simulateur sim = new Simulateur(gui, chemineDonnees,Boolean.FALSE);
		sim.start();
	}
	
	private static void scenario0(GUISimulator gui, DonneesSimulation donneesSimulation, String chemineDonnees) {
		Simulateur sim = new Simulateur(gui, chemineDonnees,Boolean.TRUE);
		sim.ajouteEvenement(new Deplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));

		sim.ajouteEvenement(new Deplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement( Direction.SUD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement( Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));
		sim.ajouteEvenement(new Deplacement( Direction.EST, donneesSimulation.getRobots().get(0), donneesSimulation, sim),donneesSimulation.getRobots().get(0));

		sim.start();
	}


	private static void scenario1(GUISimulator gui, DonneesSimulation donnees, String chemineDonnees) {

		Simulateur sim = new Simulateur(gui, chemineDonnees, Boolean.TRUE);
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

	private static void verifiePlusCourtChemin(GUISimulator gui, DonneesSimulation donnees, String chemineDonnees) {
		Simulateur sim = new Simulateur(gui, chemineDonnees, Boolean.TRUE);

		PlusCourtChemin chemin = new PlusCourtChemin(sim.getDonneesSimulation().getRobots().get(0), sim.getCarte().getCase(6, 7), sim.getCarte());
		chemin.deplaceVersCase(sim);
	}


}
