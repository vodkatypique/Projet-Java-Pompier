package game;

/**
 * The type Patte.
 */
public class Patte extends Robot {
	/**
	 * Instantiates a new Patte.
	 *
	 * @param position the position
	 * @param vitesse  the vitesse
	 */
	public Patte(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 30);
	}


	/**
	 * Instantiates a new Patte.
	 *
	 * @param pa the pa
	 */
	public Patte(Patte pa) {
		super(pa);
	}

	/**
	 * Instantiates a new Patte.
	 *
	 * @param position the position
	 */
	public Patte(Case position) {
		super(position);
	}

	/**
	 * Sets vitesse.
	 *
	 * @param vitesse the vitesse
	 */
	@Override
	public void setVitesse(double vitesse) {//la vitesse ne varie pas pour ce type de robot
		// TODO Auto-generated method stub
		super.setVitesse(30);
	}

	/**
	 * Gets vitesse.
	 *
	 * @param nature the nature
	 * @return the vitesse
	 */
	@Override
	public double getVitesse(NatureTerrain nature) {
		if (nature == NatureTerrain.ROCHE) {
			//return super.getVitesse(nature) - 10;
			return 10;//réduite "A" et non "DE" dans le sujet
		}
		return super.getVitesse(nature);
	}

	/**
	 * Gets vitesse max.
	 *
	 * @return the vitesse max
	 */
	@Override
	public double getVitesseMax() {
		return Double.MAX_VALUE;
	}

	/**
	 * Gets reservoir max.
	 *
	 * @return the reservoir max
	 */
	@Override
	public double getReservoirMax() {
		return Double.MAX_VALUE;
	}

	/**
	 * Temp remplissage double.
	 *
	 * @return the double
	 */
	@Override
	public double tempRemplissage() {
		return 0;
	}

	/**
	 * Peut remplir boolean.
	 *
	 * @param carte the carte
	 * @return the boolean
	 */
	@Override
	public boolean peutRemplir(Carte carte) {
		// TODO Auto-generated method stub
		return Boolean.FALSE;
	}

	/**
	 * Duree deversement unitaire double.
	 *
	 * @return the double
	 */
	@Override
	public double dureeDeversementUnitaire() {
		// TODO Auto-generated method stub
		return 1.0 / 10;
	}


	/**
	 * Gets vitesse default.
	 *
	 * @return the vitesse default
	 */
	@Override
	double getVitesseDefault() {
		// TODO Auto-generated method stub
		return 30.0;
	}


	/**
	 * Peut atteindre boolean.
	 *
	 * @param position the position
	 * @return the boolean
	 */
	@Override
	boolean peutAtteindre(Case position) {
		// TODO Auto-generated method stub
		return position.getNature() != NatureTerrain.EAU;
	}

}