package game;

public class Patte extends Robot {
    public Patte(Case position) {
        this.position = position;
        this.resevoir = -1;
        this.vitesse = 30;
    }

    @Override
    public void deverserEau(int vol) {

    }

    @Override
    public double getVitesse(NatureTerrain nature) {
        if (nature == NatureTerrain.ROCHE) {
            return super.getVitesse(nature) - 10;
        }
        return super.getVitesse(nature);
    }

    @Override
    public void setVitesse(double vitesse) {
        this.vitesse = this.vitesse;
    }
}