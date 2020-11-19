package game;

/**
 * The type Roue.
 */
public class Roue extends Robot {
	/**
	 * Instantiates a new Roue.
	 *
	 * @param position the position
	 * @param vitesse  the vitesse
	 */
	public Roue(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 80);


	}

	/**
	 * Instantiates a new Roue.
	 *
	 * @param roue the roue
	 */
	public Roue(Roue roue) {
		super(roue);
	}

	/**
	 * Instantiates a new Roue.
	 *
	 * @param position the position
	 */
	public Roue(Case position) {
		super(position);
	}

	/**
	 * Gets vitesse max.
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
	 * Temp remplissage double.
	 *
	 * @return the double
	 */
	@Override
	public double tempRemplissage() {
		return 10;
	}

	/**
	 * Duree deversement unitaire double.
	 *
	 * @return the double
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
		// TODO Auto-generated method stub
		return 80.0;
	}

	/**
	 * Peut atteindre boolean.
	 *
	 * @param position the position
	 * @return the boolean
	 */
	@Override
	public boolean peutAtteindre(Case position) {
		// TODO Auto-generated method stub
		return position.getNature() == NatureTerrain.TERRAIN_LIBRE || position.getNature() == NatureTerrain.HABITAT;
	}
}