package game;

import java.awt.Color;
import java.util.ArrayList;

import gui.GUISimulator;
import gui.GraphicalElement;

/**
 * The type Drone.
 */
public class Drone extends Robot {
	/**
	 * Instantiates a new Drone.
	 *
	 * @param position the position
	 */
	public Drone(Case position) {
		super(position);
	}
	
	/**
	 * Instantiates a new Drone with position and vitesse
	 * @param position
	 * @param vitesse
	 */
	public Drone(Case position, Integer vitesse) {
		super(position, vitesse);
	}

	/**
	 * Duree deversement
	 *
	 * @param vol the vol
	 * @return the duree du deversement du reservoir
	 */
	@Override
	public double dureeDeversement(double vol) {
		return 30;
	}

	/**
	 * Gets vitesse max.
	 *
	 * @return the vitesse max
	 */
	@Override
	double getVitesseMax() {
		return 150;
	}

	/**
	 * Gets reservoir max.
	 *
	 * @return the reservoir max
	 */
	@Override
	double getReservoirMax() {
		return 10000;
	}

	/**
	 * Temp remplissage
	 *
	 * @return le temps de remplissage
	 */
	@Override
	double tempRemplissage() {
		// en minute
		return 30;
	}

	/**
	 * Dit si on Peut remplir le drone a la position courante
	 *
	 * @param carte the carte
	 * @return vrai si on est sur de l'eau, faux sinon
	 */
	@Override
	public boolean peutRemplir(Carte carte) {
		if (carte.getCase(this.getPosition().getLigne(), this.getPosition().getColonne()).getNature() == NatureTerrain.EAU) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * renvoit le plus court chemin permettant d'aller remplir le drone
	 *
	 * /!\ VOIR la meme methode dans la Classe Robot, qui partage les memes remarques.
	 *
	 * @param carte the carte
	 * @return the plus court chemin
	 */
	@Override
	public PlusCourtChemin chercherEau(Carte carte) {
		Double tempsLePlusRapide = Double.MAX_VALUE;
		ArrayList<PlusCourtChemin> listeChemin = new ArrayList<PlusCourtChemin>();
		for (Case[] cases : carte.getPlateau()) {
			for (Case endroit : cases) {
				if (endroit.getNature().equals(NatureTerrain.EAU)) {
					PlusCourtChemin chemin = new PlusCourtChemin(this, endroit, carte);
					double temps = chemin.getTempsOptim();
					if (temps < tempsLePlusRapide) {
						tempsLePlusRapide = temps;
						listeChemin.add(chemin);
					}
				}
			}
		}
		return listeChemin.get(listeChemin.size() - 1);
	}

	/**
	 * Get Duree deversement unitaire.
	 *
	 * @return la duree de deversement unitaire
	 */
	@Override
	public double dureeDeversementUnitaire() {
		return 30.0 / getReservoirMax();
	}

	/**
	 * Gets vitesse par default.
	 *
	 * @return the vitesse par default
	 */
	@Override
	public double getVitesseDefault() {
		return 100.0;
	}

	/**
	 * Peut atteindre, toujours vraie, le drone peut aller partout
	 *
	 * @param position the position
	 * @return toujours vrai
	 */
	@Override
	boolean peutAtteindre(Case position) {
		return true;
	}
	
	@Override
	public GraphicalElement draw(GUISimulator gui, int n) {
		// TODO Auto-generated method stub
		int tailleCase = (int) (RESOLUTION / n);
		return new gui.Oval(this.getPosition().getColonne() * tailleCase + OFFSET_GAUCHE,
				this.getPosition().getLigne() * tailleCase + OFFSET_HAUT, Color.BLACK, Color.magenta, tailleCase);
	}


}
