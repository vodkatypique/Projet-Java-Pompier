package game;

public class Drone extends Robot {
    public Drone(Case position) {
        this.position = position;
        this.resevoir = 10000;
        this.vitesse = 100;
    }

    @Override
    public void deverserEau(int vol) {

    }

}
