package game;

public class Chenille extends Robot {
    public Chenille(Case position) {
        this.position = position;
        this.resevoir = 2000;
        this.vitesse = 60;
    }

    @Override
    public void deverserEau(int vol) {

    }

    @Override
    public double getVitesse(NatureTerrain nature) {
        if (nature == NatureTerrain.FORET) {
            return super.getVitesse(nature) / 2;
        }
        return super.getVitesse(nature);
    }


}