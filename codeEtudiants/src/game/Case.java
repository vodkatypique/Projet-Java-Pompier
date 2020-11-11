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

	public int getColonne() {
		return this.colonne;
	}

	public int getLigne() {
		return this.ligne;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Case))
			return Boolean.FALSE;
		Case case_obj=(Case) obj;
		if(this.colonne==case_obj.colonne&&this.ligne==this.ligne) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public NatureTerrain getNature() {
		return this.nature;
	}

	@Override
	public String toString() {
		return "Case{" + "ligne=" + ligne + ", colonne=" + colonne + ", nature=" + nature + '}';
	}
}
