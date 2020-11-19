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
     * @param pos       the pos
     * @param intensite the intensite
     */
    public Incendie(Case pos, int intensite) {
        this.position = pos;
        this.intensite = intensite;
        this.intensiteInit = intensite;
    }

    /**
     * Instantiates a new Incendie.
     *
     * @param inc the inc
     */
    public Incendie(Incendie inc) {
        this.position = new Case(inc.getPosition());
        this.intensite = inc.getIntensite();
        this.intensiteInit = this.intensite;
    }


    /**
     * Decremente intensite.
     *
     * @param intensite the intensite
     */
    public void decrementeIntensite(double intensite) {
        this.intensite -= intensite;//on décremente
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
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Incendie{" +
                "position=" + position +
                ", intensite=" + intensite +
                '}';
    }
}
