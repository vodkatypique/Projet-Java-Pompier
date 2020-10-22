public class Carte {
    private int tailleCases;
    private int nbLignes;
    private int nbColonnes;

    private Case[][] plateau;


    public Carte(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.tailleCases = 1;
        this.plateau = new Case[nbLignes][nbColonnes];
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
}
