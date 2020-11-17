package game;

public class OccupationRobot {
	private boolean estOccupe;
	private long dateFin;
	private boolean OccupationGenerale;

	public OccupationRobot(boolean estOccupe, long dateFin) {
		this.estOccupe = estOccupe;
		this.dateFin = dateFin;
	}

	public boolean isOccupationGenerale() {
		return OccupationGenerale;
	}

	public void setOccupationGenerale(boolean occupationGenerale) {
		OccupationGenerale = occupationGenerale;
	}

	public OccupationRobot(OccupationRobot or) {
		this.estOccupe = or.getEstOccupe();
		this.dateFin = or.getDateFin();
	}

	public boolean getEstOccupe() {
		return this.estOccupe;
	}

	public void changeState() {
		if (this.estOccupe == true) {
			this.estOccupe = false;
		} else {
			this.estOccupe = true;
		}
	}
	
	public long getDateFin() {
		return this.dateFin;
	}
	
	public void setDateFin(long date) {
		this.dateFin = date;
	}
}
