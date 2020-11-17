package game;

public class Incendie {
    private Case position;
    private int intensite;
    private int intensiteInit;

    public Incendie(Case pos, int intensite) {
        this.position = pos;
        this.intensite = intensite;
        this.intensiteInit = intensite;
    }

    public Incendie(Incendie inc) {
    	this.position = new Case(inc.getPosition());
    	this.intensite = inc.getIntensite();
    	this.intensiteInit = this.intensite;
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
	
	public void resetIntensite() {
		this.intensite = this.intensiteInit;
	}
	
    @Override
    public String toString() {
        return "Incendie{" +
                "position=" + position +
                ", intensite=" + intensite +
                '}';
    }
}
