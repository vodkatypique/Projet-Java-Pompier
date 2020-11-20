package game;

import java.util.ArrayList;

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
	 * @return the double
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
	 * Temp remplissage double.
	 *
	 * @return the double
	 */
	@Override
	double tempRemplissage() {
		// en minute
		return 30;
	}

	/**
	 * Peut remplir boolean.
	 *
	 * @param carte the carte
	 * @return the boolean
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
	 * Duree deversement unitaire double.
	 *
	 * @return the double
	 */
	@Override
	public double dureeDeversementUnitaire() {
		return 30.0 / getReservoirMax();
	}

	/**
	 * Gets vitesse default.
	 *
	 * @return the vitesse default
	 */
	@Override
	public double getVitesseDefault() {
		return 100.0;
	}

	/**
	 * Peut atteindre boolean.
	 *
	 * @param position the position
	 * @return the boolean
	 */
	@Override
	boolean peutAtteindre(Case position) {
		return true;
	}


}
