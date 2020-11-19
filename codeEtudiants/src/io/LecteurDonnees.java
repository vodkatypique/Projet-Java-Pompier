package io;

import game.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;



/**
 * Lecteur de cartes au format spectifié dans le sujet.
 * Les données sur les cases, robots puis incendies sont lues dans le fichier,
 * puis simplement affichées.
 * A noter: pas de vérification sémantique sur les valeurs numériques lues.
 *
 * IMPORTANT:
 *
 * Cette classe ne fait que LIRE les infos et les afficher.
 * A vous de modifier ou d'ajouter des méthodes, inspirées de celles présentes
 * (ou non), qui CREENT les objets au moment adéquat pour construire une
 * instance de la classe game.DonneesSimulation à partir d'un fichier.
 *
 * Vous pouvez par exemple ajouter une méthode qui crée et retourne un objet
 * contenant toutes les données lues:
 *    public static game.DonneesSimulation creeDonnees(String fichierDonnees);
 * Et faire des méthode creeCase(), creeRobot(), ... qui lisent les données,
 * créent les objets adéquats et les ajoutent ds l'instance de
 * game.DonneesSimulation.
 */
public class LecteurDonnees {


    /**
     * Lit et affiche le contenu d'un fichier de donnees (cases,
     * robots et incendies).
     * Ceci est méthode de classe; utilisation:
     * LecteurDonnees.lire(fichierDonnees)
     *
     * @param fichierDonnees    nom du fichier à lire
     * @param donneesSimulation
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     */
   /* public static void lire(String fichierDonnees)
        throws FileNotFoundException, DataFormatException {
        //System.out.println("\n == Lecture du fichier" + fichierDonnees);
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);


        DonneesSimulation donneesSimulation = new DonneesSimulation();
        lecteur.lireCarte(donneesSimulation);
        lecteur.lireIncendies(donneesSimulation);
        lecteur.lireRobots(donneesSimulation);
        scanner.close();
        //System.out.println("\n == Lecture terminee");
    }*/
    public static DonneesSimulation creeDonnees(String fichierDonnees)
            throws FileNotFoundException, DataFormatException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        //System.out.println("\n == Lecture du fichier" + fichierDonnees);
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);


        Carte carte = lecteur.creeCarte();
        ArrayList<Incendie> incendies = lecteur.creeIncendies(carte);
        ArrayList<Robot> robots = lecteur.creeRobots(carte);
        DonneesSimulation donneesSimulation = new DonneesSimulation(incendies, robots, carte);
        scanner.close();


        //System.out.println("\n == Lecture terminee");

        return donneesSimulation;
    }




    // Tout le reste de la classe est prive!

    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     * @param fichierDonnees nom du fichier a lire
     */
    private LecteurDonnees(String fichierDonnees)
        throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Lit et affiche les donnees de la carte.
     *
     * @throws ExceptionFormatDonnees
     */
    private Carte creeCarte() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();


            int tailleCases = scanner.nextInt();    // en m

            Case[][] plateau = new Case[nbLignes][nbColonnes];
            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                	plateau[lig][col] = creeCase(lig, col);
                }
            }
            return new Carte(nbLignes, nbColonnes, tailleCases, plateau);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
    }


    /**
     * Lit et affiche les donnees d'une case.
     */
    private Case creeCase(int lig, int col) throws DataFormatException {
        ignorerCommentaires();
        //System.out.print("game.Case (" + lig + "," + col + "): ");
        String chaineNature = "";
        //		game.NatureTerrain nature;

        try {
            chaineNature = scanner.next();
            // si game.NatureTerrain est un Enum, vous pouvez recuperer la valeur
            // de l'enum a partir d'une String avec:
            //			game.NatureTerrain nature = game.NatureTerrain.valueOf(chaineNature);

            verifieLigneTerminee();

            //System.out.print("nature = " + chaineNature);
            return new Case(lig, col, NatureTerrain.valueOf(chaineNature));

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de case invalide. "
                    + "Attendu: nature altitude [valeur_specifique]");
        }

        ////System.out.println();
    }


    /**
     * Lit et cr�e les donnees des incendies.
     */
    private ArrayList<Incendie> creeIncendies(Carte carte) throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbIncendies = scanner.nextInt();

            ArrayList<Incendie> incendies = new ArrayList<Incendie>();

            ////System.out.println("Nb d'incendies = " + nbIncendies);
            for (int i = 0; i < nbIncendies; i++) {
                incendies.add(creeIncendie(carte));
            }

            return incendies;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme incendie.
     *
     * @param i
     */
    private Incendie creeIncendie(Carte carte) throws DataFormatException {
        ignorerCommentaires();
        //System.out.print("game.Incendie " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            if (intensite <= 0) {
                throw new DataFormatException("nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();

            ////System.out.println("position = (" + lig + "," + col
            //        + ");\t intensite = " + intensite);
            Incendie incendie = new Incendie(carte.getCase(lig, col), intensite);
            return incendie;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
    }


    /**
     * Lit et affiche les donnees des robots.
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     */
    private ArrayList<Robot> creeRobots(Carte carte) throws DataFormatException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        ignorerCommentaires();
        try {
            int nbRobots = scanner.nextInt();
            ArrayList<Robot> robots = new ArrayList<Robot>();

            ////System.out.println("Nb de robots = " + nbRobots);
            for (int i = 0; i < nbRobots; i++) {
                robots.add(creeRobot(carte));
            }

            return robots;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
    }


    /**
     * Lit et cree les donnees du i-eme robot.
     *
     * @param i
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     */
    private Robot creeRobot(Carte carte) throws DataFormatException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        ignorerCommentaires();

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            //System.out.print("position = (" + lig + "," + col + ");");
            String type = scanner.next();

            //System.out.print("\t type = " + type);


            // lecture eventuelle d'une vitesse du robot (entier)
            //System.out.print("; \t vitesse = ");
            String s = scanner.findInLine("(\\d+)");    // 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");
            Robot robot;
            if (type.charAt(type.length() - 1) == 'S') {
                type = type.substring(0, type.length() - 1);
            }
            type = type.toLowerCase();
            type = type.substring(0, 1).toUpperCase() + type.substring(1);
            ////System.out.println(type);
            ////System.out.println(lig + " " + col);
            if (s == null) {
                //System.out.print("valeur par defaut");
                robot = (Robot) Class
                        .forName("game." + type)
                        .getConstructor(Case.class)
                        .newInstance(carte.getCase(lig, col));
            } else {
                int vitesse = Integer.parseInt(s);
                //System.out.print(vitesse);
                robot = (Robot) Class
                        .forName("game." + type)
                        .getConstructor(Case.class, Integer.class)
                        .newInstance(carte.getCase(lig, col), vitesse);
            }
            
            //Integer vitesse = s == null ? null : Integer.parseInt(s);

            //System.out.print(vitesse);
            

            /*robot = (Robot) Class
                    .forName("game." + type)
                    .getConstructor(Case.class, Integer.class)
                    .newInstance(carte.getCase(lig, col), vitesse);*/

/*
                if (type.equals("DRONE")) {
                    robot = new Drone(carte.getCase(lig, col), vitesse);
                } else if (type.equals("ROUES")) {
                    robot = new Roue(carte.getCase(lig, col), vitesse);
                } else if (type.equals("PATTES")) {
                    robot = new Patte(carte.getCase(lig, col), vitesse);
                } else { //chenilles
                    //System.out.println(type);
                    robot = new Chenille(carte.getCase(lig, col), vitesse);
                }*/

            //robots.add(robot);


            verifieLigneTerminee();

            //System.out.println();
            return robot;

        } catch (NoSuchElementException | ClassNotFoundException | NoSuchMethodException e) {
        	e.printStackTrace();
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalAccessException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new InstantiationException();
        } 
        /*catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new Invoc
        }*/
		
    }




    /** Ignore toute (fin de) ligne commencant par '#' */
    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
