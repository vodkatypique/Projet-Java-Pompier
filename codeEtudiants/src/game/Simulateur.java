/*
 * Simulateur
 * 
 * 1.0
 *
 * 20/11/2020
 * 
 * Benjamin Cathelineau, ClÈment Caffin, Brown Ebouky
 */
package game;

import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;


/**
 * The type Simulateur.
 */
public class Simulateur implements Simulable {
	/**
	 * L'interface graphique associÈe
	 */
	private GUISimulator gui;
	private DonneesSimulation donneesSimulation;
	private int tailleCase;
	private long dateSimulation;

	/**
	 * The Chef pompier.
	 */
	ChefPompier chefPompier;

	/**
	 * Le simulateur principal
	 *
	 * @param gui     l'interface graphique associ√©e, dans laquelle se fera le dessin              et qui enverra les messages via les m√©thodes h√©rit√©es de              Simulable.
	 * @param donnees the donnees
	 */
	public Simulateur(GUISimulator gui, DonneesSimulation donnees) {
		this.gui = gui;
		this.donneesSimulation = donnees;
		this.tailleCase = 10;
		this.dateSimulation = 0;
		this.offsetGauche = 30;
		this.offsetHaut = 30;
		this.chefPompier = null;
	}

	private int offsetGauche;
	private int offsetHaut;
	private Hashtable<Robot, ArrayList<Evenement>> evenements = new Hashtable<Robot, ArrayList<Evenement>>();

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
			System.err.println("erreur, date nÔøΩgative");
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
				switch (donneesSimulation.getCarte().getCase(i, j).getNature()) {
					case FORET:
						gui.addGraphicalElement(
								new gui.Rectangle(j * tailleCase + this.offsetGauche, i * tailleCase + this.offsetHaut, Color.GREEN, Color.GREEN, tailleCase));
						break;
					case TERRAIN_LIBRE:
						gui.addGraphicalElement(
								new gui.Rectangle(j * tailleCase+this.offsetGauche, i * tailleCase+this.offsetHaut, Color.WHITE, Color.WHITE, tailleCase));
						break;
					case HABITAT:
						gui.addGraphicalElement(
								new gui.Rectangle(j * tailleCase+this.offsetGauche, i * tailleCase+this.offsetHaut, Color.CYAN, Color.CYAN, tailleCase));
						break;
					case ROCHE:
						gui.addGraphicalElement(new gui.Rectangle(j * tailleCase+this.offsetGauche, i * tailleCase+this.offsetHaut, Color.DARK_GRAY,
								Color.DARK_GRAY, tailleCase));
						break;
					case EAU:
						gui.addGraphicalElement(
								new gui.Rectangle(j * tailleCase + this.offsetGauche, i * tailleCase+this.offsetHaut, Color.BLUE, Color.BLUE, tailleCase));
						break;
				}
			}
		}

		for (Incendie incendie : donneesSimulation.getIncendies()) {

			gui.addGraphicalElement(new gui.Rectangle(incendie.getPosition().getColonne() * tailleCase + this.offsetGauche,
					incendie.getPosition().getLigne() * tailleCase+this.offsetHaut, Color.RED, Color.RED, tailleCase));
			gui.addGraphicalElement(new gui.Text(incendie.getPosition().getColonne() * tailleCase + this.offsetGauche,
					incendie.getPosition().getLigne() * tailleCase + this.offsetHaut, Color.WHITE, "" + incendie.getIntensite()));


		}
		for (Robot robot : donneesSimulation.getRobots()) {
			gui.addGraphicalElement(new gui.Oval(robot.getPosition().getColonne() * tailleCase + this.offsetGauche,
					robot.getPosition().getLigne() * tailleCase + this.offsetHaut, Color.BLACK, Color.YELLOW, tailleCase));
		}


	}

	/**
	 * Incrememte date.
	 */
	void incrememteDate() {
		if (this.chefPompier != null) {
			chefPompier.boucleExtinction();
		}
		
		++this.dateSimulation;
		System.err.println(this.dateSimulation);
		for (Robot robot : this.donneesSimulation.getRobots()) {
			ArrayList<Evenement> listeEvenement=this.evenements.get(robot);
			if(listeEvenement==null) {
				continue;
			}
			if(listeEvenement.isEmpty()) {//TODO Áa marche mais c'est pas clean, au pire on peut laisser
				robot.getOccupationRobot().setOccupationGenerale(false);
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
			System.out.println("BRAVO tout les incendies sont Èteints par les robots");
			System.exit(0);
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
	public void restart() {//TODO marche pas
		this.dateSimulation = 0;
		for (Robot robot : this.donneesSimulation.getRobots()) {
			robot.resetReservoir();
			robot.resetPosition(this.donneesSimulation);
			this.evenements.remove(robot);
		}
		for (Incendie inc : this.donneesSimulation.getIncendies())
			inc.resetIntensite();

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
