package game;

public class Incendie {
    private Case position;
    private int intensite;

    public Incendie(Case pos, int intensite) {
        this.position = pos;
        this.intensite = intensite;
    }

    public Incendie(Incendie inc) {
    	this.position = new Case(inc.getPosition());
    	this.intensite = inc.getIntensite();
    }
    
    
    public void decrementeIntensite(double intensite) {
		this.intensite -= intensite;//on décremente
	}

	public Case getPosition() {
        return position;
    }
	public int getIntensite() {
		return intensite;
	}
    @Override
    public String toString() {
        return "Incendie{" +
                "position=" + position +
                ", intensite=" + intensite +
                '}';
    }
}
