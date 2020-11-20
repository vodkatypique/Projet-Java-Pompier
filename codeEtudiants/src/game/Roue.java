package game;

import java.awt.Color;

import gui.GUISimulator;
import gui.GraphicalElement;

/**
 * The type Roue.
 */
public class Roue extends Robot {
	/**
	 * Instantiates a new Roue.
	 *
	 * @param position the position
	 */
	public Roue(Case position) {
		super(position);
	}
	
	/**
	 * Instatiates a new Roue with position and vitesse
	 * @param position
	 * @param vitesse
	 */
	public Roue(Case position, Integer vitesse) {
		super(position, vitesse);


	}

	/**
	 * Gets vitesse max
	 * non specifié, donc consideré sans limite
	 *
	 * @return the vitesse max
	 */
	@Override
	public double getVitesseMax() {
		// Pas de vitesse max dans le sujet
		return Double.MAX_VALUE;
	}

	/**
	 * Gets reservoir max.
	 *
	 * @return the reservoir max
	 */
	@Override
	public double getReservoirMax() {
		return 5000;
	}

	/**
	 * Temp remplissage
	 *
	 * @return duree du remplissage
	 */
	@Override
	public double tempRemplissage() {
		return 10;
	}

	/**
	 * Duree deversement unitaire double.
	 *
	 * @return the temps de deversement unitaire
	 */
	@Override
	public double dureeDeversementUnitaire() {
		return 5.0 / 100;
	}

	/**
	 * Gets vitesse default.
	 *
	 * @return the vitesse default
	 */
	@Override
	public double getVitesseDefault() {
		return 80.0;
	}

	/**
	 * Peut atteindre une case specifié, uniquement un terrain libre ou un habitat
	 *
	 * @param position the position
	 * @return vrai si terrain_libre ou habitat, faux sinon
	 */
	@Override
	public boolean peutAtteindre(Case position) {
		return position.getNature() == NatureTerrain.TERRAIN_LIBRE || position.getNature() == NatureTerrain.HABITAT;
	}

	@Override
	public GraphicalElement draw(GUISimulator gui, int n) {
		// TODO Auto-generated method stub
		int tailleCase = (int) (RESOLUTION / n);
		return new gui.Oval(this.getPosition().getColonne() * tailleCase + OFFSET_GAUCHE,
				this.getPosition().getLigne() * tailleCase + OFFSET_HAUT, Color.BLACK, Color.BLACK, tailleCase);
	}
}