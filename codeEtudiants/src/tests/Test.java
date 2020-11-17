package tests;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.DataFormatException;

import game.DebutDeplacement;
import game.Direction;
import game.DonneesSimulation;
import game.PlusCourtChemin;
import game.DebutExtinctionFeu;
import game.DebutRemplissageReservoir;
import game.Robot;
import game.Simulateur;
import gui.GUISimulator;
import io.LecteurDonnees;

public class Test {
	public static void main(String[] args) throws FileNotFoundException, DataFormatException {

		if (args.length < 1) {
			System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
			System.exit(1);
		}

		DonneesSimulation donneesSimulation;
		try {
			
			donneesSimulation = LecteurDonnees.creeDonnees(args[0]);
			for (Robot r : donneesSimulation.getRobots()) 
				System.out.println(r);
			GUISimulator gui = new GUISimulator(
					donneesSimulation.getCarte().getNbLignes() * 20,
					donneesSimulation.getCarte().getNbLignes() * 20,
					Color.BLACK);
			
			//scenario0(gui, donneesSimulation);
			//scenario1(gui, donneesSimulation);
			verifiePlusCourtChemin(gui, donneesSimulation);
			
			
		} catch (FileNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
				| InvocationTargetException | DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
		// crée l'invader, en l'associant à la fenêtre graphique précédente
		}
	
	
	private static void scenario0(GUISimulator gui, DonneesSimulation donneesSimulation) {
		Simulateur sim = new Simulateur(gui, donneesSimulation);


		sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		//sim.ajouteEvenement(new DebutDeplacement(5, Direction.SUD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		//sim.ajouteEvenement(new DebutDeplacement(6, Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		//sim.ajouteEvenement(new DebutDeplacement(7, Direction.EST, donneesSimulation.getRobots().get(0), donneesSimulation, sim));


		sim.start();
	}
	 
	
	private static void scenario1(GUISimulator gui, DonneesSimulation donnees) {
		
		Simulateur sim = new Simulateur(gui, donnees);
		Robot robot = donnees.getRobots().get(1);
		sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, robot, donnees,sim));
		sim.ajouteEvenement(new DebutExtinctionFeu(robot, sim, donnees));
		sim.ajouteEvenement(new DebutDeplacement(Direction.OUEST, robot, donnees, sim));
		sim.ajouteEvenement(new DebutDeplacement(Direction.OUEST, robot, donnees, sim));
		sim.ajouteEvenement(new DebutRemplissageReservoir(robot, sim));
		sim.ajouteEvenement(new DebutDeplacement(Direction.EST, robot, donnees, sim));
		sim.ajouteEvenement(new DebutDeplacement(Direction.EST, robot, donnees, sim));
		sim.ajouteEvenement(new DebutExtinctionFeu(robot, sim, donnees));
		
		
		sim.start();
	}
	
	private static void verifiePlusCourtChemin(GUISimulator gui, DonneesSimulation donnees) {
		Simulateur sim = new Simulateur(gui, donnees);
		
		PlusCourtChemin chemin = new PlusCourtChemin(sim.getDonneesSimulation().getRobots().get(0), sim.getCarte().getCase(0, 0), sim.getCarte());
		chemin.deplaceVersCase(sim);
		
	}
	
	
}
