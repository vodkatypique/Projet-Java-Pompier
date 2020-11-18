package game;

import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;
//import java.util.Iterator;

public class Simulateur implements Simulable {
	/**
	 * L'interface graphique associée
	 */
	private GUISimulator gui;
	private DonneesSimulation donneesSimulation;
	private int tailleCase;
	private long dateSimulation;
	public long getDateSimulation() {
		return dateSimulation;
	}

	private int offsetGauche;
	private int offsetHaut;
	private ArrayList<Evenement> evenements = new ArrayList<Evenement>();
	ChefPompier chefPompier;

	/**
	 * Crée un Invader et le dessine.
	 *
	 * @param gui   l'interface graphique associée, dans laquelle se fera le dessin
	 *              et qui enverra les messages via les méthodes héritées de
	 *              Simulable.
	 * @param color la couleur de l'invader
	 */
	public Simulateur(GUISimulator gui, DonneesSimulation donnees) {
		this.gui = gui;
		this.donneesSimulation = donnees;
		this.tailleCase = 50;
		this.dateSimulation = 0;
		this.offsetGauche = 50;
		this.offsetHaut=50;
		this.chefPompier = null;
	}

	
	
	public void ajouteEvenement(Evenement evenement) {
		// on peut aussi utiliser un hashmap si on veut rendre l'execution parall�le cad donner la possibilite � deux robots
		// de pouvoir se deplacer en meme temps
		// � ce moment on recup�re la date de fin du dernier evenement qui fait intervenir ce robot pour donner la date de fin du nouvel evenement
		if(evenement.getDate()<this.dateSimulation) {
			System.err.println("Erreur, evenement dans le passé");
			return;
		}
		if(this.evenements.size() == 0)
			this.evenements.add(evenement);
		else {
			long dateLastEvent = this.evenements.get(this.evenements.size()-1).getDate();
			evenement.setDate(evenement.getDate() + dateLastEvent);
			this.evenements.add(evenement);
		}
	}
	
	public void start() {
		draw(this.donneesSimulation);
	}
	
	private void draw(DonneesSimulation donneesSimulation) {
		gui.setSimulable(this);
		gui.reset(); // clear the window
		System.out.println("-------------------------");
		// int cptX = 0;
		// int cptY = 0;

		for (int i = 0; i < donneesSimulation.getCarte().getNbLignes(); i++) {
			for (int j = 0; j < donneesSimulation.getCarte().getNbColonnes(); j++) {
				switch (donneesSimulation.getCarte().getCase(i, j).getNature()) {
					case FORET:
						gui.addGraphicalElement(
								new gui.Rectangle(j * tailleCase+this.offsetGauche, i * tailleCase+this.offsetHaut, Color.GREEN, Color.GREEN, tailleCase));
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
			// cptY++;
		}

		for (Incendie incendie : donneesSimulation.getIncendies()) {
			if (incendie.getIntensite() > 0) {
				gui.addGraphicalElement(new gui.Rectangle(incendie.getPosition().getColonne() * tailleCase + this.offsetGauche,
						incendie.getPosition().getLigne() * tailleCase+this.offsetHaut, Color.RED, Color.RED, tailleCase));
				gui.addGraphicalElement(new gui.Text(incendie.getPosition().getColonne() * tailleCase + this.offsetGauche,
						incendie.getPosition().getLigne() * tailleCase +this.offsetHaut, Color.WHITE, "" + incendie.getIntensite()));

			}

		}
		for (Robot robot : donneesSimulation.getRobots()) {
			gui.addGraphicalElement(new gui.Oval(robot.getPosition().getColonne() * tailleCase + this.offsetGauche,
					robot.getPosition().getLigne() * tailleCase +this.offsetHaut, Color.BLACK, Color.YELLOW, tailleCase));
		}


	}

	void incrememteDate() {
		if (this.chefPompier != null) {
			chefPompier.boucleExtinction();
		}

		++this.dateSimulation;
		System.err.println(this.dateSimulation);

		ArrayList<Evenement> copieEvenement = new ArrayList<Evenement>();//pour éviter concurentModificationException
		for (Evenement evenement : this.evenements) {
			copieEvenement.add(evenement);
		}
		for (Evenement evenement : copieEvenement) {
			if (this.dateSimulation == evenement.getDate()) {
				evenement.execute();
				this.evenements.remove(evenement);	
			}
		}
		this.draw(this.donneesSimulation);
	}
	public Carte getCarte() {
		return this.donneesSimulation.getCarte();
		
	}
	@Override
	public void next() {
		incrememteDate();

	}
	
	public DonneesSimulation getDonneesSimulation() {
		return this.donneesSimulation;
	}
	
	public boolean simulationTerminee() {
		//return this.evenements.isEmpty();
		for(Evenement ev: this.evenements)  {
			if(ev.getDate() > this.dateSimulation) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		this.dateSimulation = 0;
		for(Robot robot: this.donneesSimulation.getRobots()) {
			robot.resetReservoir();
			robot.resetPosition(this.donneesSimulation);
		}
		for (Incendie inc : this.donneesSimulation.getIncendies())
			inc.resetIntensite();

		this.draw(this.donneesSimulation);

	}

	public void setChefPompier(ChefPompier chefPompier) {
		this.chefPompier = chefPompier;
	}
}
