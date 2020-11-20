package game;

import java.awt.Color;

import gui.GUISimulator;
import gui.GraphicalElement;
import interfaces.Drawable;

/**
 * The type Incendie.
 */
public class Incendie implements Drawable{
    private Case position;
    private int intensite;
    private int intensiteInit;

    /**
     * Instantiates a new Incendie.
     *
     * @param pos       the position
     * @param intensite the intensite
     */
    public Incendie(Case pos, int intensite) {
        this.position = pos;
        this.intensite = intensite;
        this.intensiteInit = intensite;
    }


    /**
     * Decremente intensite de l'incendie, pour quand on tentera de l'eteindre
     *
     * @param intensite the intensite de l'incendie
     */
    public void decrementeIntensite(double intensite) {
        this.intensite -= intensite;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Case getPosition() {
        return position;
    }

    /**
     * Gets intensite.
     *
     * @return the intensite
     */
    public int getIntensite() {
        return intensite;
    }

    /**
     * Reset intensite.
     */
    public void resetIntensite() {
        this.intensite = this.intensiteInit;
    }

    /**
     * To string.
     *
     * @return l'objet formaté sous forme de String
     */
    @Override
    public String toString() {
        return "Incendie{" +
                "position=" + position +
                ", intensite=" + intensite +
                '}';
    }


	@Override
	public GraphicalElement draw(GUISimulator gui, int n) {
		// TODO Auto-generated method stub
		int tailleCase = (int) (RESOLUTION / n);
		return new gui.Rectangle(this.getPosition().getColonne() * tailleCase + OFFSET_GAUCHE, this.getPosition().getLigne() * tailleCase + OFFSET_HAUT, Color.RED, Color.RED, tailleCase);
		/*return (new gui.Text(incendie.getPosition().getColonne() * tailleCase + OFFSET_GAUCHE,
				this.getPosition().getLigne() * tailleCase + this.offsetHaut, Color.WHITE, "" + incendie.getIntensite()));*/

	}
    
    
}
