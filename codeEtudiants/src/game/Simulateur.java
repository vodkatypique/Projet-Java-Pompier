/*
 * Simulateur
 * 
 * 1.0
 *
 * 20/11/2020
 * 
 * Benjamin Cathelineau, Cl?ment Caffin, Brown Ebouky
 */
package game;

import gui.GUISimulator;
import gui.Simulable;


import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.zip.DataFormatException;

import io.LecteurDonnees;

/**
 * The type Simulateur.
 */
public class Simulateur implements Simulable {
	/**
	 * L'interface graphique associ?e
	 */
	private GUISimulator gui;
	private DonneesSimulation donneesSimulation;
	private long dateSimulation;
	private String cheminDonnees;
	private Boolean joueScenario;
	private Hashtable<Robot, ArrayList<Evenement>> evenements = new Hashtable<Robot, ArrayList<Evenement>>();

	/**
	 * The Chef pompier.
	 */
	ChefPompier chefPompier;

	/**
	 * Constructeur pour la simulation
	 *
	 * @param gui     l'interface graphique associée, dans laquelle se fera le dessin et qui enverra les messages via les m?thodes h?rit?es de Simulable.
	 * @param cheminDonnees le chemin du syst?me de fichier vers les donn?es
	 */
	public Simulateur(GUISimulator gui, String cheminDonnees) {
		this.gui = gui;
		this.dateSimulation = 0;
		this.cheminDonnees=cheminDonnees;
		this.joueScenario=Boolean.FALSE;
		lectureDonnee();
		this.setChefPompier(new ChefPompier(this.donneesSimulation.getRobots(), this.donneesSimulation.getCarte(), this.donneesSimulation.getIncendies(), this));
	}
	/** Constructeur pour les sc?narios
	 * @param gui     l'interface graphique associée, dans laquelle se fera le dessin et qui enverra les messages via les m?thodes h?rit?es de Simulable.
	 * @param donneesSimulation les donn?es de la simulation d?j? remplie avec les ?v?nement
	 */
	public Simulateur(GUISimulator gui,DonneesSimulation donneesSimulation) {
		this.gui = gui;
		this.dateSimulation = 0;
		this.joueScenario=Boolean.TRUE;
		this.donneesSimulation=donneesSimulation;

	}
	private void lectureDonnee() {
		try {
			this.donneesSimulation=LecteurDonnees.creeDonnees(this.cheminDonnees);
		} catch (FileNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
				| InvocationTargetException | DataFormatException e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Gets date simulation.
	 *
	 * @return the date simulation
	 */
	public long getDateSimulation() {
		return dateSimulation;
	}

	/**
	 * Gets evenements.
	 *
	 * @return the evenements
	 */
	public Hashtable<Robot, ArrayList<Evenement>> getEvenements() {
		return evenements;
	}

	/**
	 * Ajoute evenement.
	 *
	 * @param evenement the evenement
	 * @param robot     the robot
	 */
	public void ajouteEvenement(Evenement evenement, Robot robot) {
		if (evenement.getDate() < 0) {
			System.err.println("erreur, date n?gative");
		}


		ArrayList<Evenement> listeEvenement = this.evenements.get(robot);
		if(listeEvenement==null){
			listeEvenement=new ArrayList<Evenement>();
			this.evenements.put(robot, listeEvenement);
		}

		if (listeEvenement.size() == 0) {
			evenement.setDate(this.getDateSimulation() + 1);
			listeEvenement.add(evenement);
		} else {
			long dateLastEvent = listeEvenement.get(listeEvenement.size() - 1).getDate();
			evenement.setDate(evenement.getDate() + dateLastEvent);
			listeEvenement.add(evenement);
		}

	}

	/**
	 * Start.
	 */
	public void start() {
		
		draw(this.donneesSimulation);
	}

	private void draw(DonneesSimulation donneesSimulation) {
		gui.setSimulable(this);
		gui.reset(); // clear the window

		for (int i = 0; i < donneesSimulation.getCarte().getNbLignes(); i++) {
			for (int j = 0; j < donneesSimulation.getCarte().getNbColonnes(); j++) {
				gui.addGraphicalElement(donneesSimulation.getCarte().getCase(i, j).draw(gui, this.getCarte().getNbLignes()));
			}
		}

		for (Incendie incendie : donneesSimulation.getIncendies()) {
			gui.addGraphicalElement(incendie.draw(gui, this.getCarte().getNbLignes()));
		}
		for (Robot robot : donneesSimulation.getRobots()) {
			gui.addGraphicalElement(robot.draw(gui, this.getCarte().getNbLignes()));
		}


	}

	/**
	 * Incrememte date.
	 */
	void incrememteDate() {
		if (this.chefPompier != null&&(!this.joueScenario)) {
			chefPompier.boucleExtinction();
		}
		
		++this.dateSimulation;
		System.err.println(this.dateSimulation);
		for (Robot robot : this.donneesSimulation.getRobots()) {
			ArrayList<Evenement> listeEvenement=this.evenements.get(robot);
			if(listeEvenement==null) {
				continue;
			}
			if(listeEvenement.isEmpty()) {//TODO ?a marche mais c'est pas clean, au pire on peut laisser
				robot.setOccupationGenerale(false);
			}
			ArrayList<Evenement> nouvelListeEvenement=new ArrayList<Evenement>();
			for (Evenement evenement : listeEvenement) {
				nouvelListeEvenement.add(evenement);
			}
			for (Evenement evenement : nouvelListeEvenement) {
				if (this.dateSimulation == evenement.getDate()) {
					evenement.execute();
					listeEvenement.remove(evenement);

				}
			}
		}

		this.draw(this.donneesSimulation);
		if(this.simulationTerminee()) {
			System.out.println("BRAVO tout les incendies sont ?teints par les robots");
			//System.exit(0);
		}
	}

	/**
	 * Gets carte.
	 *
	 * @return the carte
	 */
	public Carte getCarte() {
		return this.donneesSimulation.getCarte();

	}

	/**
	 * Next.
	 */
	@Override
	public void next() {
		incrememteDate();

	}

	/**
	 * Gets donnees simulation.
	 *
	 * @return the donnees simulation
	 */
	public DonneesSimulation getDonneesSimulation() {
		return this.donneesSimulation;
	}

	/**
	 * Simulation terminee boolean.
	 *
	 * @return the boolean
	 */
	public boolean simulationTerminee() {
		return this.donneesSimulation.getIncendies().isEmpty();
	}

	/**
	 * Restart.
	 */
	@Override
	public void restart() {
		this.dateSimulation = 0;
		for (Robot robot : this.donneesSimulation.getRobots()) {
			robot.resetReservoir();
			robot.resetPosition(this.donneesSimulation);
			this.evenements.remove(robot);
		}
		lectureDonnee();
		this.setChefPompier(new ChefPompier(this.donneesSimulation.getRobots(), this.donneesSimulation.getCarte(), this.donneesSimulation.getIncendies(), this));
		this.draw(this.donneesSimulation);

	}

	/**
	 * Sets chef pompier.
	 *
	 * @param chefPompier the chef pompier
	 */
	public void setChefPompier(ChefPompier chefPompier) {
		this.chefPompier = chefPompier;
	}
}
