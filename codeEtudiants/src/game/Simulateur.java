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
	private int offset;
	private ArrayList<Evenement> evenements=new ArrayList<Evenement>();

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
		this.dateSimulation=0;
		this.offset=50;
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
								new gui.Rectangle(j * tailleCase+this.offset, i * tailleCase, Color.GREEN, Color.GREEN, tailleCase));
						break;                                  
					case TERRAIN_LIBRE:                         
						gui.addGraphicalElement(                
								new gui.Rectangle(j * tailleCase+this.offset, i * tailleCase, Color.WHITE, Color.WHITE, tailleCase));
						break;
					case HABITAT:
						gui.addGraphicalElement(
								new gui.Rectangle(j * tailleCase+this.offset, i * tailleCase, Color.CYAN, Color.CYAN, tailleCase));
						break;
					case ROCHE:
						gui.addGraphicalElement(new gui.Rectangle(j * tailleCase+this.offset, i * tailleCase, Color.DARK_GRAY,
								Color.DARK_GRAY, tailleCase));
						break;
					case EAU:
						gui.addGraphicalElement(
								new gui.Rectangle(j * tailleCase+this.offset, i * tailleCase, Color.BLUE, Color.BLUE, tailleCase));
						break;
				}
			}
			// cptY++;
		}

		for (Robot robot : donneesSimulation.getRobots()) {
			gui.addGraphicalElement(new gui.Oval(robot.getPosition().getColonne() * tailleCase+this.offset,
					robot.getPosition().getLigne() * tailleCase, Color.BLACK, Color.YELLOW, tailleCase));
		}

		for (Incendie incendie : donneesSimulation.getIncendies()) {
			if(incendie.getIntensite() > 0) {
				gui.addGraphicalElement(new gui.Rectangle(incendie.getPosition().getColonne() * tailleCase+this.offset,
						incendie.getPosition().getLigne() * tailleCase, Color.RED, Color.RED, tailleCase));
				gui.addGraphicalElement(new gui.Text(incendie.getPosition().getColonne() * tailleCase+this.offset,
						incendie.getPosition().getLigne() * tailleCase, Color.WHITE, ""+incendie.getIntensite()));
				
			}
			
		}

	}

	void incrememteDate() {
		++this.dateSimulation;
		System.err.println(this.dateSimulation);

		ArrayList<Evenement> copieEvenement=new ArrayList<Evenement>();//pour éviter concurentModificationException
		for (Evenement evenement : this.evenements) {
			copieEvenement.add(evenement);
		}
		for (Evenement evenement : copieEvenement) {
			if(this.dateSimulation == evenement.getDate()) {
				evenement.execute();
				//this.evenements.remove(evenement);	
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
		for(Incendie inc: this.donneesSimulation.getIncendies())
			inc.resetIntensite();
		
		this.draw(this.donneesSimulation);

	}

}
