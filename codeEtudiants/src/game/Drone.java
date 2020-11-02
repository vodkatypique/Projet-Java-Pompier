package game;

public class Drone extends Robot {
    public Drone(Case position) {
        this.position = position;
        this.resevoir = 10000;
        this.vitesse = 100;
    }

    public Drone(Case position, double vitesse) {
        super();
        this.vitesse = vitesse;
    }

    @Override
    public void deverserEau(int vol) {

    }

}
