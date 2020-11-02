package game;

public class Carte {
    private int tailleCases;
    private int nbLignes;
    private int nbColonnes;

    private Case[][] plateau;


    public Carte(int nbLignes, int nbColonnes, int tailleCases) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.tailleCases = tailleCases;
        this.plateau = new Case[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                this.plateau[i][j] = new Case(i, j, NatureTerrain.FORET);
            }
        }
    }

    public int getTailleCases() {
        return tailleCases;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public Case getCase(int lig, int col) {
        return plateau[lig][col];
    }

    public Case getVoisin(Case src, Direction dir) {
        if (this.voisinExiste(src, dir)) {
            return this.plateau[src.getLigne()][src.getColonne()];
        }
        return null; //TODO ERRRREEEEEUUUUR
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public boolean voisinExiste(Case src, Direction dir) {
        if ((src.getColonne() == 0 && dir == Direction.OUEST) || (src.getColonne() == this.nbColonnes && dir == Direction.EST) || (src.getLigne() == 0 && dir == Direction.NORD) || (src.getLigne() == this.nbLignes && dir == Direction.SUD)) {
            return false;
        }
        return true;


    }
}
