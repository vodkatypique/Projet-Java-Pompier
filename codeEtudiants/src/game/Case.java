package game;

public class Case {
	private int ligne;
	private int colonne;
	private NatureTerrain nature;

	public Case(int ligne, int colonne, NatureTerrain nature) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = nature;
	}
	
	public Case(Case ca) {
		this.ligne = ca.ligne;
		this.colonne = ca.colonne;
		this.nature = ca.nature;
	}

	public int getColonne() {
		return this.colonne;
	}

	public int getLigne() {
		return this.ligne;
	}
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
	
	public Direction getDirection(int lig, int col) {
		// direction pour arriver � la case en lig et col voisine
		if(this.getLigne() == lig - 1)
			return Direction.SUD;
		if(this.getLigne() == lig + 1)
			return Direction.NORD;
		if(this.getColonne() == col -1)
			return Direction.EST;
		return Direction.OUEST;
	}

	public NatureTerrain getNature() {
		return this.nature;
	}

	@Override
	public String toString() {
		return "Case{" + "ligne=" + ligne + ", colonne=" + colonne + ", nature=" + nature + '}';
	}
}
