package game;

public class OccupationRobot {
	private boolean estOccupe;
	private long dateFin;
	
	public OccupationRobot(boolean estOccupe,long dateFin) {
		this.estOccupe=estOccupe;
		this.dateFin=dateFin;
	}
	
	public OccupationRobot(OccupationRobot or) {
		this.estOccupe = or.getEstOccupe();
		this.dateFin = or.getDateFin();
	}
	
	public boolean getEstOccupe() {
		return this.estOccupe;
	}
	
	public void changeState() {
		if(this.estOccupe == true)
			this.estOccupe = false;
		else
			this.estOccupe = true;
	}
	
	public long getDateFin() {
		return this.dateFin;
	}
	
	public void setDateFin(long date) {
		this.dateFin = date;
	}
}
