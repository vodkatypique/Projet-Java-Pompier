package game;

/**
 * The type Incendie.
 */
public class Incendie {
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
}
