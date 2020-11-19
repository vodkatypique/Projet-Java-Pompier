package tests;

import game.Robot;
import game.*;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.DataFormatException;

public class Test {
	public static void main(String[] args) throws FileNotFoundException, DataFormatException {

		if (args.length < 1) {
			//System.out.println("Syntaxe: java tests.TestLecteurDonnees <nomDeFichier>");
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
			scenario0(gui, donneesSimulation);


		} catch (FileNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
				| InvocationTargetException | DataFormatException e) {
			e.printStackTrace();
		}

		

		
		}
	
	
	private static void scenario0(GUISimulator gui, DonneesSimulation donneesSimulation) {

        //sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
        //sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
        //sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));

        Simulateur sim = new Simulateur(gui, donneesSimulation);
        ChefPompier chefPompier = new ChefPompier(donneesSimulation.getRobots(), donneesSimulation.getCarte(), donneesSimulation.getIncendies(), sim);
        sim.setChefPompier(chefPompier);

        sim.start();
        //sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
        //sim.ajouteEvenement(new DebutDeplacement(5, Direction.SUD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
        //sim.ajouteEvenement(new DebutDeplacement(6, Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
        //sim.ajouteEvenement(new DebutDeplacement(7, Direction.EST, donneesSimulation.getRobots().get(0), donneesSimulation, sim));


    }
	 
	
	private static void scenario1(GUISimulator gui, DonneesSimulation donnees) {
		
		Simulateur sim = new Simulateur(gui, donnees);
		Robot robot = donnees.getRobots().get(1);
		sim.ajouteEvenement(new DebutDeplacement(Direction.NORD, robot, donnees,sim),robot);
		sim.ajouteEvenement(new DebutExtinctionFeu(robot, sim, donnees),robot);
		sim.ajouteEvenement(new DebutDeplacement(Direction.OUEST, robot, donnees, sim),robot);
		sim.ajouteEvenement(new DebutDeplacement(Direction.OUEST, robot, donnees, sim),robot);
		sim.ajouteEvenement(new DebutRemplissageReservoir(robot, sim),robot);
		sim.ajouteEvenement(new DebutDeplacement(Direction.EST, robot, donnees, sim),robot);
		sim.ajouteEvenement(new DebutDeplacement(Direction.EST, robot, donnees, sim),robot);
		sim.ajouteEvenement(new DebutExtinctionFeu(robot, sim, donnees),robot);
		
		
		sim.start();
	}
	
	private static void verifiePlusCourtChemin(GUISimulator gui, DonneesSimulation donnees) {
        Simulateur sim = new Simulateur(gui, donnees);

        PlusCourtChemin chemin = new PlusCourtChemin(sim.getDonneesSimulation().getRobots().get(0), sim.getCarte().getCase(6, 7), sim.getCarte());
        chemin.deplaceVersCase(sim);
    }
	
	
}
