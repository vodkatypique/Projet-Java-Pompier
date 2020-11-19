package game;

import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Hashtable;

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

	public Hashtable<Robot, ArrayList<Evenement>> getEvenements() {
		return evenements;
	}

	private int offsetGauche;
	private int offsetHaut;
	private Hashtable<Robot, ArrayList<Evenement>> evenements=new Hashtable<Robot, ArrayList<Evenement>>();
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
		this.tailleCase = 10;
		this.dateSimulation = 0;
		this.offsetGauche = 30;
		this.offsetHaut=30;
		this.chefPompier = null;
	}

	
	
	public void ajouteEvenement(Evenement evenement, Robot robot) {
		// on peut aussi utiliser un hashmap si on veut rendre l'execution parall�le cad donner la possibilite � deux robots
		// de pouvoir se deplacer en meme temps
		// � ce moment on recup�re la date de fin du dernier evenement qui fait intervenir ce robot pour donner la date de fin du nouvel evenement
		if(evenement.getDate()<0) {
			System.err.println("erreur, date n�gative");
		}

		
		ArrayList<Evenement> listeEvenement=this.evenements.get(robot);
		if(listeEvenement==null){
			listeEvenement=new ArrayList<Evenement>();
			this.evenements.put(robot, listeEvenement);
		}

		if(listeEvenement.size() == 0) {
			evenement.setDate(this.getDateSimulation()+1);
			listeEvenement.add(evenement);
		}
		else {
			long dateLastEvent = listeEvenement.get(listeEvenement.size()-1).getDate();
			evenement.setDate(evenement.getDate() + dateLastEvent);
			listeEvenement.add(evenement);
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

				gui.addGraphicalElement(new gui.Rectangle(incendie.getPosition().getColonne() * tailleCase + this.offsetGauche,
						incendie.getPosition().getLigne() * tailleCase+this.offsetHaut, Color.RED, Color.RED, tailleCase));
				gui.addGraphicalElement(new gui.Text(incendie.getPosition().getColonne() * tailleCase + this.offsetGauche,
						incendie.getPosition().getLigne() * tailleCase +this.offsetHaut, Color.WHITE, "" + incendie.getIntensite()));

			

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
		for (Robot robot : this.chefPompier.getRobots()) {
			ArrayList<Evenement> listeEvenement=this.evenements.get(robot);
			if(listeEvenement.isEmpty()) {
				robot.getOccupationRobot().setOccupationGenerale(false);
			}
			ArrayList<Evenement> nouvelListeEvenement=new ArrayList<Evenement>();
			for (Evenement evenement : listeEvenement) {
				nouvelListeEvenement.add(evenement);
			}
			for(Evenement evenement : nouvelListeEvenement) {
				if (this.dateSimulation == evenement.getDate()) {
					evenement.execute();
					listeEvenement.remove(evenement);

				}
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
		return this.donneesSimulation.getIncendies().isEmpty();
		//return this.evenements.isEmpty();
//		for(Evenement ev: this.evenements)  {
//			if(ev.getDate() > this.dateSimulation) {
//				return false;
//			}
//		}
//		return true;
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
