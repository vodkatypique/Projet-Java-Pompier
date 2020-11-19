/*
 * Carte
 * 
 * 1.0
 *
 * 20/11/2020
 * 
 * Benjamin Cathelineau, Clément Caffin, Brown Ebouky
 */
package game;


/**
 * The type Carte.
 * represente la carte de notre univers
 */
public class Carte {
	private int tailleCases;
	private int nbLignes;
	private int nbColonnes;

	private Case[][] plateau;


	/**
	 * Instantiates a new Carte.
	 * on remplie la carte avec les valeurs representant un tableau,
	 * on itere donc sur ce plateau et creant une case pour chaque valeur du plateau
	 *
	 * @param nbLignes    the nb lignes
	 * @param nbColonnes  the nb colonnes
	 * @param tailleCases the taille cases
	 * @param plateau     the plateau
	 */
	public Carte(int nbLignes, int nbColonnes, int tailleCases, Case[][] plateau) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.tailleCases = tailleCases;
		this.plateau = new Case[nbLignes][nbColonnes];
		this.plateau = plateau;
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				this.plateau[i][j] = new Case(plateau[i][j]);
			}
		}
	}
	/**
	 * Gets distance entre case.
	 *
	 * @return the distance entre case
	 */
	public double getDistanceEntreCase() {// en metres
		return this.tailleCases;
		
	}

	/**
	 * Gets taille cases.
	 *
	 * @return the taille cases
	 */
	public int getTailleCases() {
		return tailleCases;
	}

	/**
	 * Gets nb lignes.
	 *
	 * @return the nb lignes
	 */
	public int getNbLignes() {
		return nbLignes;
	}

	/**
	 * Gets nb colonnes.
	 *
	 * @return the nb colonnes
	 */
	public int getNbColonnes() {
		return nbColonnes;
	}

	/**
	 * Gets case.
	 *
	 * @param lig the lig
	 * @param col the col
	 * @return the case
	 */
	public Case getCase(int lig, int col) {
		if (lig < 0 || lig > (this.nbLignes - 1) || col < 0 || col > (this.nbColonnes - 1)) {
			return null;
		}
		return plateau[lig][col];
	}

	/**
	 * Gets voisin.
	 * si le voisin Existe (on appelle voisinExiste(src, dir) qui  va le verifier)
	 * alors on retourne la case qui caracterise ce voisin
	 *
	 * @param src the src
	 * @param dir the dir
	 * @return the voisin
	 */
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
		return null;
	}

	/**
	 * Get plateau
	 *
	 * @return the plateau
	 */
	public Case[][] getPlateau() {
		return plateau;
	}

	/**
	 * Voisin existe boolean.
	 * verifie les conditions limites pour l'existence d'un voisin (les bords de l'univers)
	 *
	 * @param src the src
	 * @param dir the dir
	 * @return true si un voisin existe, false sinon
	 */
	public boolean voisinExiste(Case src, Direction dir) {
		return (src.getColonne() != 0 || dir != Direction.OUEST)
				&& (src.getColonne() != (this.nbColonnes - 1) || dir != Direction.EST)
				&& (src.getLigne() != 0 || dir != Direction.NORD)
				&& (src.getLigne() != (this.nbLignes - 1) || dir != Direction.SUD);

	}
}
