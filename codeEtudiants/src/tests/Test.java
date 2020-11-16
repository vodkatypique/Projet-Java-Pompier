package tests;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.DataFormatException;

import game.DebutDeplacement;
import game.Direction;
import game.DonneesSimulation;
import game.DebutExtinctionFeu;
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
			
			scenario0(gui, donneesSimulation);
			scenario1(gui, donneesSimulation);
			
			
		} catch (FileNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
				| InvocationTargetException | DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
		// crée l'invader, en l'associant à la fenêtre graphique précédente
		}
	
	
	private static void scenario0(GUISimulator gui, DonneesSimulation donneesSimulation) {
		Simulateur sim = new Simulateur(gui, donneesSimulation);


		sim.ajouteEvenement(new DebutDeplacement(1, Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(2, Direction.SUD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(3, Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(4, Direction.EST, donneesSimulation.getRobots().get(0), donneesSimulation, sim));


		sim.start();
	}
	
	
	private static void scenario1(GUISimulator gui, DonneesSimulation donnees) {
		
		Simulateur sim = new Simulateur(gui, donnees);
		Robot robot = donnees.getRobots().get(1);
		sim.ajouteEvenement(new DebutDeplacement(1, Direction.NORD, robot, donnees,sim));
		sim.ajouteEvenement(new DebutExtinctionFeu(2, robot, sim, donnees));
		//sim.ajouteEvenement(new DebutDeplacement(3, Direction.OUEST, donneesSimulation.getRobots().get(1), donneesSimulation,sim));
		//sim.ajouteEvenement(new DebutDeplacement(4, Direction.OUEST, donneesSimulation.getRobots().get(1), donneesSimulation,sim));
		//sim.ajouteEvenement(new DebutExtinctionFeu(5, donneesSimulation.getRobots().get(0), donneesSimulation.getRobots().get(0).getReservoir(), sim, donneesSimulation));
		
		sim.start();
	}
	
	
}
