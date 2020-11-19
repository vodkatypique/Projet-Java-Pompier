package game;

import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Simulateur implements Simulable {
	/**
	 * L'interface graphique associee
	 */
	private GUISimulator gui;
	private DonneesSimulation donneesSimulation;
	private int tailleCase;
	private long dateSimulation;
	private int offsetGauche;
	private int offsetHaut;
	private Hashtable<Robot, ArrayList<Evenement>> evenements;
	private ChefPompier chefPompier;
	
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
		this.evenements=new Hashtable<Robot, ArrayList<Evenement>>();
		this.chefPompier = null;
	}

	
	
	public void ajouteEvenement(Evenement evenement) {
		// on peut aussi utiliser un hashmap si on veut rendre l'execution parallele cad donner la possibilite a deux robots
		// de pouvoir se deplacer en meme temps
		// a ce moment on recupere la date de fin du dernier evenement qui fait intervenir ce robot pour donner la date de fin du nouvel evenement
		/*if(evenement.getDate()<this.dateSimulation) {
			System.err.println("Erreur, evenement dans le pass�");
			return;
		}*/
		Robot robot = evenement.getRobot();
		ArrayList<Evenement> listeEvenement=this.evenements.get(robot);
		
		if(listeEvenement == null){
			listeEvenement=new ArrayList<Evenement>();
			listeEvenement.add(evenement);
			this.evenements.put(robot, listeEvenement);
			return;
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
		for(Map.Entry<Robot, ArrayList<Evenement>> event: this.evenements.entrySet()) {
			//Robot robot = event.getKey();
			for(int i = 0; i < event.getValue().size(); i++) {
				Evenement evenement =  event.getValue().get(i);
				if(this.dateSimulation == evenement.getDate()) {
					evenement.execute();
					if(i < event.getValue().size()-1) { // nous ne sommes pas au dernier evenement de l'array
						Evenement suiv = event.getValue().get(i+1);
						suiv.updateDateFin(this.dateSimulation);
					}
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
		//return this.donneesSimulation.getIncendies().isEmpty();
		//return this.evenements.isEmpty();
		for(Map.Entry<Robot, ArrayList<Evenement>> event: this.evenements.entrySet()) {
			//Robot robot = event.getKey();
			for(Evenement evenement: event.getValue()) {
				if(evenement.getDate() > this.dateSimulation) {
					return false;
				}
			}
		}
		return true;
	}
	
	public long getDateSimulation() {
		return dateSimulation;
	}

	public Hashtable<Robot, ArrayList<Evenement>> getEvenements() {
		return evenements;
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
