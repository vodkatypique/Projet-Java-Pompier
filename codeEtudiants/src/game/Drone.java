package game;

import java.util.ArrayList;

public class Drone extends Robot {

	public Drone(Case position, Integer vitesse) {
		super(position, vitesse != null ? vitesse : 100);

	}

	public Drone(Drone d) {
		super(d);
	}
	
	public Drone(Case position) {
		super(position);
	}
	
	@Override
	public double dureeDeversement(double vol) {
		return 30;
	}
	@Override
	double getVitesseMax() {
		return 150;
	}

	@Override
	double getReservoirMax() {
		return 10000;
	}

	@Override
	double tempRemplissage() {
		// en minute
		return 30;
	}
	@Override
	public boolean peutRemplir(Carte carte) {
		if (carte.getCase(this.getPosition().getLigne(),this.getPosition().getColonne() ).getNature() == NatureTerrain.EAU) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	@Override
	public PlusCourtChemin chercherEau(Carte carte) {
    	Double tempsLePlusRapide=Double.MAX_VALUE;
    	ArrayList<PlusCourtChemin> listeChemin=new ArrayList<PlusCourtChemin>();
    	for (Case[] cases : carte.getPlateau()) {
    		for (Case endroit : cases) {
					if(endroit.getNature().equals(NatureTerrain.EAU)) {
						PlusCourtChemin chemin =new PlusCourtChemin(this, endroit, carte);
						double temps = chemin.getTempsOptim();
						if(temps<tempsLePlusRapide) {
							tempsLePlusRapide=temps;
							listeChemin.add(chemin);
						}
					}
			}
		}
    	return listeChemin.get(listeChemin.size()-1);
	}

	@Override
	public double dureeDeversementUnitaire() {
		// TODO Auto-generated method stub
		return 30.0/getReservoirMax();
	}

	@Override
	public double getVitesseDefault() {
		// TODO Auto-generated method stub
		return 100.0;
	}

	@Override
	boolean peutAtteindre(Case position) {
		// TODO Auto-generated method stub
		return true;
	}


}
