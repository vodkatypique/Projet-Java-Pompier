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
	 * @param vitesse  the vitesse
	 */
	public Chenille(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 60);
	}

	/**
	 * Instantiates a new Chenille.
	 *
	 * @param c the c
	 */
	public Chenille(Chenille c) {
		super(c);
	}

	/**
	 * Instantiates a new Chenille.
	 *
	 * @param position the position
	 */
	public Chenille(Case position) {
		super(position);
	}

	/**
	 * Gets vitesse.
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
	 * @return the reservoir max
	 */
	@Override
	double getReservoirMax() {
		// TODO Auto-generated method stub
		return 2000;
	}

	/**
	 * Temp remplissage double.
	 *
	 * @return the double
	 */
	@Override
	double tempRemplissage() {
		// TODO Auto-generated method stub
		return 5;
	}

	/**
	 * Duree deversement unitaire double.
	 *
	 * @return the double
	 */
	@Override
	double dureeDeversementUnitaire() {
		return 8.0 / 100;
	}

	/**
	 * Gets vitesse default.
	 *
	 * @return the vitesse default
	 */
	@Override
	double getVitesseDefault() {
		// TODO Auto-generated method stub
		return 60.0;
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
		return position.getNature() != NatureTerrain.EAU && position.getNature() != NatureTerrain.ROCHE;
	}


}