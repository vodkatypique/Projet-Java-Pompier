package game;

public class Roue extends Robot {
    public Roue(Case position) {
        this.position = position;
        this.resevoir = 5000;
        this.vitesse = 80;
    }

    public Roue(Case position, double vitesse) {
        super();
        this.vitesse = vitesse;
    }

    @Override
    public void deverserEau(int vol) {

    }
}