
import game.DonneesSimulation;
import game.Incendie;
import game.Robot;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestLecteurDonnees {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }

        try {
            DonneesSimulation donneesSimulation = new DonneesSimulation();
            LecteurDonnees.creeDonnees(args[0], donneesSimulation);

            for (Robot robot : donneesSimulation.getRobots()
            ) {
                System.out.println(robot);
            }
            System.out.println("\n\n");

            for (Incendie incendie : donneesSimulation.getIncendies()
            ) {
                System.out.println(incendie);
            }
            System.out.println("\n\n");


            System.out.println(donneesSimulation.getCarte().getTailleCases());
            for (int i = 0; i < donneesSimulation.getCarte().getNbLignes(); i++) {
                for (int j = 0; j < donneesSimulation.getCarte().getNbColonnes(); j++) {
                    System.out.println(donneesSimulation.getCarte().getCase(i, j));
                }
            }

            GUISimulator gui = new GUISimulator(
                    donneesSimulation.getCarte().getNbLignes() * 20,
                    donneesSimulation.getCarte().getNbLignes() * 20,
                    Color.BLACK);
            // crée l'invader, en l'associant à la fenêtre graphique précédente
            Simulateur sim = new Simulateur(gui, donneesSimulation);

        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
    }

}

