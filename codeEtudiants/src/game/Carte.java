package game;

public class Carte {
	private int tailleCases;
	private int nbLignes;
	private int nbColonnes;

	private Case[][] plateau;

	public Carte(int nbLignes, int nbColonnes, int tailleCases) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.tailleCases = tailleCases;
		this.plateau = new Case[nbLignes][nbColonnes];
	}

	public Carte(int nbLignes, int nbColonnes, int tailleCases, Case[][] plateau) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.tailleCases = tailleCases;
		this.plateau = new Case[nbLignes][nbColonnes];
		this.plateau = plateau;
		// il faut donner les valeurs � chaque �l�ment de plateau en copiant pour
		// n'avoir pas
		// � manipuler les r�f�rences
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				this.plateau[i][j] = new Case(plateau[i][j]);
			}
		}
	}

	public Carte(Carte ca) {
		this.nbLignes = ca.getNbLignes();
		this.nbColonnes = ca.getNbColonnes();
		this.tailleCases = ca.getTailleCases();
		this.plateau = new Case[nbLignes][nbColonnes];
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				this.plateau[i][j] = new Case(ca.getPlateau()[i][j]);
			}
		}
	}

	public static double getDistanceEntreCase() {// en metres
		return 1000;
	}

	public int getTailleCases() {
		return tailleCases;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public Case getCase(int lig, int col) {
		if (lig < 0 || lig > (this.nbLignes - 1) || col < 0 || col > (this.nbColonnes - 1)) {
			return null;
		}
		return plateau[lig][col];
	}

	public Case getVoisin(Case src, Direction dir) {
		if (this.voisinExiste(src, dir)) {
			switch (dir) {
				case NORD:
					return (this.plateau[src.getLigne() - 1][src.getColonne()]);
				case SUD:
					return (this.plateau[src.getLigne() + 1][src.getColonne()]);
				case EST:
					return (this.plateau[src.getLigne()][src.getColonne() + 1]);
				case OUEST:
					return (this.plateau[src.getLigne()][src.getColonne() - 1]);
				default:
					return null;
			}
		}
		return null; // TODO ERRRREEEEEUUUUR
	}

	public Case[][] getPlateau() {
		return plateau;
	}

	public boolean voisinExiste(Case src, Direction dir) {
		return (src.getColonne() != 0 || dir != Direction.OUEST)
				&& (src.getColonne() != (this.nbColonnes - 1) || dir != Direction.EST)
				&& (src.getLigne() != 0 || dir != Direction.NORD)
				&& (src.getLigne() != (this.nbLignes - 1) || dir != Direction.SUD);

	}
}
