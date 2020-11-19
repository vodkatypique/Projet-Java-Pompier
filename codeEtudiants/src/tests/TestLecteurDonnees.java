package tests;

import game.DonneesSimulation;
import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.DataFormatException;

/**
 * The type Test lecteur donnees.
 */
public class TestLecteurDonnees {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            //System.out.println("Syntaxe: java tests.TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }

        try {
            DonneesSimulation donneesSimulation = LecteurDonnees.creeDonnees(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException | IllegalArgumentException
                | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

