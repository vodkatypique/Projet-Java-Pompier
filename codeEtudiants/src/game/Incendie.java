package game;

public class Incendie {
    private Case position;
    private int intensite;

    public Incendie(Case pos, int intensite) {
        this.position = pos;
        this.intensite = intensite;
    }

    public Case getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Incendie{" +
                "position=" + position +
                ", intensite=" + intensite +
                '}';
    }
}
