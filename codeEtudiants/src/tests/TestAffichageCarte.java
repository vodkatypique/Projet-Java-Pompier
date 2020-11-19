package tests;

import game.DonneesSimulation;
import game.Robot;
import game.Simulateur;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.DataFormatException;

/**
 * The type Test affichage carte.
 */
public class TestAffichageCarte {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws FileNotFoundException     the file not found exception
     * @throws DataFormatException       the data format exception
     * @throws IllegalAccessException    the illegal access exception
     * @throws InstantiationException    the instantiation exception
     * @throws IllegalArgumentException  the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     */
    public static void main(String[] args)
            throws FileNotFoundException, DataFormatException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {

        if (args.length < 1) {
            //System.out.println("Syntaxe: java tests.TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }

        DonneesSimulation donneesSimulation = LecteurDonnees.creeDonnees(args[0]);

        for (Robot r :
                donneesSimulation.getRobots()) {
            //System.out.println(r);
        }

        GUISimulator gui = new GUISimulator(
                donneesSimulation.getCarte().getNbLignes() * 20,
                donneesSimulation.getCarte().getNbLignes() * 20,
                Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        Simulateur sim = new Simulateur(gui, donneesSimulation);


		/*sim.ajouteEvenement(new DebutDeplacement(1, Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(1, Direction.SUD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(1, Direction.NORD, donneesSimulation.getRobots().get(0), donneesSimulation, sim));
		sim.ajouteEvenement(new DebutDeplacement(10, Direction.EST, donneesSimulation.getRobots().get(0), donneesSimulation, sim));*/

//        sim.ajouteEvenement(new DebutDeplacement(1, Direction.NORD, donneesSimulation.getRobots().get(1), donneesSimulation, sim));
//        sim.ajouteEvenement(new DebutExtinctionFeu(2, donneesSimulation.getRobots().get(1), 100, sim, donneesSimulation));
        //new ChefPompier(donneesSimulation.getRobots(), donneesSimulation.getCarte(),donneesSimulation.getIncendies());
        //sim.ajouteEvenement(new DebutDeplacement(3, Direction.OUEST, donneesSimulation.getRobots().get(1), donneesSimulation,sim));
        //sim.ajouteEvenement(new DebutDeplacement(4, Direction.OUEST, donneesSimulation.getRobots().get(1), donneesSimulation,sim));
        //sim.ajouteEvenement(new DebutExtinctionFeu(5, donneesSimulation.getRobots().get(0), donneesSimulation.getRobots().get(0).getReservoir(), sim, donneesSimulation));
        sim.start();
    }
}

