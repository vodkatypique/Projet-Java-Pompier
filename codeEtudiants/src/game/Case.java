package game;

import java.awt.Color;

import gui.GUISimulator;
import gui.GraphicalElement;
import interfaces.Drawable;

/**
 * The type Case.
 * reprensente une case du plateau qui represente notre univers
 */
public class Case implements Drawable{
	private int ligne;
	private int colonne;
	private NatureTerrain nature;

	/**
	 * Instantiates a new Case.
	 *
	 * @param ligne   the posX
	 * @param colonne the posY
	 * @param nature  the nature de la case
	 */
	public Case(int ligne, int colonne, NatureTerrain nature) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = nature;
	}

	/**
	 * Instantiates a new Case depuis une case existante
	 * recopie la case
	 *
	 * @param ca the case a copier
	 */
	public Case(Case ca) {
		this.ligne = ca.ligne;
		this.colonne = ca.colonne;
		this.nature = ca.nature;
	}

	/**
	 * Gets indice colonne.
	 *
	 * @return the colonne indice
	 */
	public int getColonne() {
		return this.colonne;
	}

	/**
	 * Gets index ligne.
	 *
	 * @return the ligne index
	 */
	public int getLigne() {
		return this.ligne;
	}

	/**
	 * Deux cases sont egales si elles sont toutes deux des cases, ont la meme nature, et la meme position.
	 *
	 * @param o the Objet a comparé a la Case
	 * @return true si egale, false sinon
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
	 * Gets direction pour arriver a la case en lig et col voisine.
	 *
	 * @param lig the lig
	 * @param col the col
	 * @return the direction a prendre
	 */
	public Direction getDirection(int lig, int col) {
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
	 * @return the nature de la case
	 */
	public NatureTerrain getNature() {
		return this.nature;
	}

	/**
	 * Affiche textuel d'une Case
	 *
	 * @return une case formaté sous forme de string
	 */
	@Override
	public String toString() {
		return "Case{" + "ligne=" + ligne + ", colonne=" + colonne + ", nature=" + nature + '}';
	}

	@Override
	public GraphicalElement draw(GUISimulator gui, int n) {
		// TODO Auto-generated method stub
		int tailleCase = (int) (RESOLUTION / n);
		int i = this.ligne;
		int j = this.colonne;
		switch (this.nature) {
			case FORET:
				return new gui.Rectangle(j * tailleCase + OFFSET_GAUCHE, i * tailleCase + OFFSET_HAUT, Color.GREEN, Color.GREEN, tailleCase);
			case TERRAIN_LIBRE:
				return new gui.Rectangle(j * tailleCase+OFFSET_GAUCHE, i * tailleCase+OFFSET_HAUT, Color.WHITE, Color.WHITE, tailleCase);
			case HABITAT:
				return new gui.Rectangle(j * tailleCase+OFFSET_GAUCHE, i * tailleCase+OFFSET_HAUT, Color.CYAN, Color.CYAN, tailleCase);
			case ROCHE:
				return new gui.Rectangle(j * tailleCase+OFFSET_GAUCHE, i * tailleCase+OFFSET_HAUT, Color.DARK_GRAY, Color.DARK_GRAY, tailleCase);
			case EAU:
				return new gui.Rectangle(j * tailleCase + OFFSET_GAUCHE, i * tailleCase+OFFSET_HAUT, Color.BLUE, Color.BLUE, tailleCase);
			
		}
		return null;
		
		
	}
}
