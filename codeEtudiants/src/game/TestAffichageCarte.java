package game;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import io.LecteurDonnees;

import gui.GUISimulator;

public class TestAffichageCarte  {
	public static void main(String[] args) throws FileNotFoundException, DataFormatException {
		DonneesSimulation donneesSimulation=new DonneesSimulation();
		LecteurDonnees.creeDonnees("/user/0/.base/cathelib/home/git/java/projetjava/codeEtudiants/cartes/carteSujet.map", donneesSimulation);
		GUISimulator gui = new GUISimulator(donneesSimulation.getCarte().getNbLignes() *20, donneesSimulation.getCarte().getNbLignes() *20, Color.BLACK);
		new Simulateur(gui, donneesSimulation);
		}
	

}
