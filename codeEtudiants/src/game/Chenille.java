package game;

/**
 * The type Chenille.
 */
//vitesseMax=80
public class Chenille extends Robot {


	/**
	 * Instantiates a new Chenille.
	 *
	 * @param position the position
	 */
	public Chenille(Case position) {
		super(position);
	}

	/**
	 * Instantiates a new Chenille with position and vitesse
	 * @param position
	 * @param vitesse
	 */
	public Chenille(Case position, Integer vitesse) {
		super(position, vitesse);

	}
	/**
	 * Gets vitesse.
	 * si on est sur de la foret, 2 fois moins vite
	 *
	 * @param nature the nature
	 * @return the vitesse
	 */
	@Override
	public double getVitesse(NatureTerrain nature) {
		if (nature == NatureTerrain.FORET) {
			return super.getVitesse(nature) / 2;
		}
		return super.getVitesse(nature);
	}


	/**
	 * Gets vitesse max.
	 *
	 * @return the vitesse max
	 */
	@Override
	double getVitesseMax() {
		return 80;
	}

	/**
	 * Gets reservoir max.
	 *
	 * @return the capacité du reservoir max
	 */
	@Override
	double getReservoirMax() {
		return 2000;
	}

	/**
	 * Temp remplissage double.
	 *
	 * @return temps du remplissage
	 */
	@Override
	double tempRemplissage() {
		return 5;
	}

	/**
	 * Duree deversement unitaire double.
	 *
	 * @return temps de deversement unitaire
	 */
	@Override
	double dureeDeversementUnitaire() {
		return 8.0 / 100;
	}

	/**
	 * Gets vitesse default.
	 *
	 * @return the vitesse par default
	 */
	@Override
	double getVitesseDefault() {
		return 60.0;
	}

	/**
	 * Peut atteindre une case, vraie pour tout sauf EAU et ROCHE.
	 *
	 * @param position the position
	 * @return vrai si on peut atteindre la case, faux sinon
	 */
	@Override
	boolean peutAtteindre(Case position) {
		return position.getNature() != NatureTerrain.EAU && position.getNature() != NatureTerrain.ROCHE;
	}


}