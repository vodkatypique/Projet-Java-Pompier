package game;

import java.util.ArrayList;

import interfaces.Drawable;

/**
 * The type Robot.
 */
public abstract class Robot implements Drawable{
	private Case position;
	private Case positionInit;
	private double vitesse;
	private double reservoir;
	private double reservoirInit;
	private boolean occupationGenerale;

	/**
	 * Instantiates a new Robot avec une position et la vitesse par defaut du type de robot
	 *
	 * @param position the position du robot
	 */
	public Robot(Case position) {
		this.position = new Case(position);
		this.positionInit = new Case(this.position);
		this.occupationGenerale = false;
		setVitesse(this.getVitesseDefault());
		setResevoir(getReservoirMax());
		this.reservoirInit = this.reservoir;
	}

	/**
	 * Instantiates a new Robot avec sa position et sa vitesse
	 *
	 * @param position the position du robot
	 * @param vitesse la vitesse du robot
	 */
	public Robot(Case position, int vitesse) {//TODO cleanup les constructeurs
		this.position = position;
		this.positionInit = new Case(this.position);
		setVitesse(vitesse);
		setResevoir(getReservoirMax());
		this.reservoirInit = this.reservoir;
		this.occupationGenerale = false;
	}

	/**
	 * renvoit le plus court chemin permettant d'aller remplir le robot selon son type
	 *
	 * a ameliorer, gros soucis de complexité visible pour la Carte 50x50, pas visible sinon
	 * On a preferer rester optimal, mais on pourrait ne tester les chemins que pour une certaine
	 * proportion de case d'eau, ou alors faire une recherche de chemin approximative (avec A* par exemple)
	 * ou combiner les deux
	 *
	 * @param carte the carte
	 * @return the plus court chemin
	 */
	public PlusCourtChemin chercherEau(Carte carte) {
		Double tempsLePlusRapide = Double.MAX_VALUE;
		ArrayList<PlusCourtChemin> listeChemin = new ArrayList<PlusCourtChemin>();
		for (Case[] cases : carte.getPlateau()) {
			for (Case endroit : cases) {
				if (endroit.getNature() == NatureTerrain.EAU) {
					for (Direction direction : Direction.values()) {
						Case voisin = carte.getVoisin(endroit, direction);
						if (voisin == null) {
							continue;
						}
						if (voisin.getNature() == NatureTerrain.EAU) {
							continue;
						}
						PlusCourtChemin chemin = new PlusCourtChemin(this, voisin, carte);
						if (chemin.getTempsOptim() != 0) {
							double temps = chemin.getTempsOptim();
							if (temps < tempsLePlusRapide) {
								tempsLePlusRapide = temps;
								listeChemin.add(chemin);
							}
						}
					}
				}
			}
		}
		if (listeChemin.isEmpty()) {
			System.err.println("Erreur, Pas d'eau disponible");
			return null;
		}
		return listeChemin.get(listeChemin.size() - 1);
	}

	/**
	 * Duree remplissage reservoir long.
	 *
	 * @param volume the volume
	 * @return the duree du remplissage
	 */
	public long dureeRemplissageReservoir(double volume) {
		long dureeRemplissage = (long) ((volume / getReservoirMax()) * tempRemplissage());
		return dureeRemplissage;
	}

	/**
	 * Duree deversement double.
	 *
	 * @param vol the vol
	 * @return la duree de deversement en seconde
	 */
	public double dureeDeversement(double vol) {// en seconde
		return vol * dureeDeversementUnitaire();
	}

	/**
	 * Peut remplir boolean.
	 *
	 * @param carte the carte
	 * @return si on peut remplir le reservoir ou non a la position courante
	 */
	public boolean peutRemplir(Carte carte) {
		Boolean peutRemplir = Boolean.FALSE;
		int lig = this.position.getLigne();
		int col = this.position.getColonne();

		for (Direction d : Direction.values()) {
			Case voisin = carte.getVoisin(new Case(lig, col, NatureTerrain.TERRAIN_LIBRE), d);
			if (voisin != null) {
				if (voisin.getNature() == NatureTerrain.EAU) {
					peutRemplir = Boolean.TRUE;
				}
			}
		}
		return peutRemplir;

	}

	/**
	 * Gets vitesse max.
	 *
	 * @return the vitesse max
	 */
	abstract double getVitesseMax();

	/**
	 * Gets reservoir max.
	 *
	 * @return the reservoir max
	 */
	abstract double getReservoirMax();

	/**
	 * Temp remplissage double.
	 *
	 * @return temps du remplissage du reservoir
	 */
	abstract double tempRemplissage();// pour la totalité du reservoir

	/**
	 * voir classe fille
	 */
	abstract double dureeDeversementUnitaire();// le temps en seconde pour le deversement de 1L

	/**
	 * Gets vitesse default.
	 *
	 * @return the vitesse default
	 */
	abstract double getVitesseDefault();

	/**
	 * voir classe fille
	 */
	abstract boolean peutAtteindre(Case position);

	/**
	 * Gets position.
	 *
	 * @return the position courante
	 */
	public Case getPosition() {
		return this.position;
	}

	/**
	 * Sets position.
	 *
	 * @param localisation the localisation
	 */
	public void setPosition(Case localisation) {
		this.position = localisation;
	}

	/**
	 * Sets resevoir.
	 *
	 * @param resevoir the resevoir
	 */
	public void setResevoir(double resevoir) {
		if (resevoir < 0) {
			System.err.println("Erreur, Reservoir négatif ");
			this.reservoir = 0;
		} else if (resevoir > getReservoirMax()) {
			System.err.println("Erreur, resevoir trop grand");
			this.reservoir = getReservoirMax();
		} else {
			this.reservoir = resevoir;
		}
	}

	/**
	 * Sets vitesse.
	 *
	 * @param vitesse the vitesse
	 */
	public void setVitesse(double vitesse) {
		if (vitesse < 0) {
			System.err.println("Erreur, Vitesse négative ");
			this.vitesse = 0;
		} else if (vitesse > getVitesseMax()) {
			System.err.println("Erreur, Vitesse trop grande");
			this.vitesse = getVitesseMax();
		} else {
			this.vitesse = vitesse;
		}
	}

	/**
	 * Gets vitesse.
	 *
	 * @param nature the nature
	 * @return the vitesse
	 */
	public double getVitesse(NatureTerrain nature) {
		return this.vitesse;
	}

	/**
	 * Reset l'etat du reservoir.
	 */
	public void resetReservoir() {
		this.reservoir = this.reservoirInit;
	}

	/**
	 * Reset position.
	 *
	 * @param donnees the donnees
	 */
	public void resetPosition(DonneesSimulation donnees) {
		Case temp = donnees.getCarte().getCase(this.positionInit.getLigne(), this.positionInit.getColonne());
		this.position = temp;
	}

	/**
	 * To string
	 *
	 * @return l'objet formaté sous forme de string
	 */
	@Override
	public String toString() {
		return "Robot{" + "position=" + position + ", type='" + getClass().getName() + '\'' + '}';
	}

	/**
	 * Gets reservoir.
	 *
	 * @return the l'etat du reservoir
	 */
	public double getReservoir() {
		return reservoir;
	}

	/**
	 * Gets occupation robot.
	 *
	 * @return l'occupation du robot
	 */
	public boolean getOccupationGenerale() {
		return this.occupationGenerale;
	}
	
	public void setOccupationGenerale(boolean val) {
		this.occupationGenerale = val;
	}
}
