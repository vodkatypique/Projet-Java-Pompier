package game;

import java.util.ArrayList;
import java.util.Map;

public class Sommet implements Comparable<Sommet>{
	private Case position;
	private double temps;
	private Map<Sommet, Double> voisins;
	private Sommet parent;

	public Sommet(Case ca, double t) {
		this.position = ca;
		this.temps = t;
		this.parent = null;
	}
	
	public Sommet(Case ca) {
		this.position = ca;
		this.temps = Double.MAX_VALUE;
		this.parent = null;
	}

	@Override
	public int compareTo(Sommet o) {
		// TODO Auto-generated method stub
		if(this.temps > o.getTemps())
			return 1;
		else if(this.temps < o.getTemps())
			return -1;
		return 0;
	}
	
	public void addVoisin(Sommet s, double temps) {
		this.voisins.put(s, temps);
	}
	
	public ArrayList<Sommet> getVoisins(Carte ca, Robot robot) {
		ArrayList<Sommet> res = new ArrayList<>();
		// on verifie que la case voisine existe et que le robot peut atteindre cette case
		if(ca.voisinExiste(this.position, Direction.NORD) && robot.peutAtteindre(ca.getCase(this.position.getLigne()-1, this.position.getColonne())))
			res.add(new Sommet(ca.getCase(this.position.getLigne()-1, this.position.getColonne())));
		if(ca.voisinExiste(this.position, Direction.SUD) && robot.peutAtteindre(ca.getCase(this.position.getLigne()+1, this.position.getColonne())))
			res.add(new Sommet(ca.getCase(this.position.getLigne()+1, this.position.getColonne())));
		if(ca.voisinExiste(this.position, Direction.OUEST) && robot.peutAtteindre(ca.getCase(this.position.getLigne(), this.position.getColonne()-1)))
			res.add(new Sommet(ca.getCase(this.position.getLigne(), this.position.getColonne()-1)));
		if(ca.voisinExiste(this.position, Direction.EST) && robot.peutAtteindre(ca.getCase(this.position.getLigne(), this.position.getColonne()+1)))
			res.add(new Sommet(ca.getCase(this.position.getLigne(), this.position.getColonne()+1)));
		
		return res;
	}
	
	public double getTemps() {
		return this.temps;
	}
	
	public Case getPosition() {
		return this.position;
	}
	
	public void setTemps(double t) {
		this.temps = t;
	}
	
	public Sommet getParent() {
		return this.parent;
	}
	
	public void setParent(Sommet s) {
		this.parent = s;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this ==  o)
			return true;
		if(o == null)
			return false;
		if(this.getClass() != o.getClass())
			return false;
		Sommet s = (Sommet)o;
		return this.position.equals(s.getPosition());
	}
}
