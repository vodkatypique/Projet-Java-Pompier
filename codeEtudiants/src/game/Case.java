package game;

/**
 * The type Case.
 */
public class Case {
	private int ligne;
	private int colonne;
	private NatureTerrain nature;

	/**
	 * Instantiates a new Case.
	 *
	 * @param ligne   the ligne
	 * @param colonne the colonne
	 * @param nature  the nature
	 */
	public Case(int ligne, int colonne, NatureTerrain nature) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = nature;
	}

	/**
	 * Instantiates a new Case.
	 *
	 * @param ca the ca
	 */
	public Case(Case ca) {
		this.ligne = ca.ligne;
		this.colonne = ca.colonne;
		this.nature = ca.nature;
	}

	/**
	 * Gets colonne.
	 *
	 * @return the colonne
	 */
	public int getColonne() {
		return this.colonne;
	}

	/**
	 * Gets ligne.
	 *
	 * @return the ligne
	 */
	public int getLigne() {
		return this.ligne;
	}

	/**
	 * Equals boolean.
	 *
	 * @param o the o
	 * @return the boolean
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Case ca = (Case) o;
		return this.ligne == ca.getLigne() && this.colonne == ca.getColonne() && this.nature == ca.getNature();
	}

	/**
	 * Gets direction.
	 *
	 * @param lig the lig
	 * @param col the col
	 * @return the direction
	 */
	public Direction getDirection(int lig, int col) {
		// direction pour arriver � la case en lig et col voisine
		if (this.getLigne() == lig - 1)
			return Direction.SUD;
		if (this.getLigne() == lig + 1)
			return Direction.NORD;
		if (this.getColonne() == col - 1)
			return Direction.EST;
		return Direction.OUEST;
	}

	/**
	 * Gets nature.
	 *
	 * @return the nature
	 */
	public NatureTerrain getNature() {
		return this.nature;
	}

	/**
	 * To string string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Case{" + "ligne=" + ligne + ", colonne=" + colonne + ", nature=" + nature + '}';
	}
}
