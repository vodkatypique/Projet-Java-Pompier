package game;

public class OccupationRobot {
	private long dateFin;
	private boolean OccupationGenerale;

	public OccupationRobot(long dateFin) {
		this.dateFin = dateFin;
	}

	public boolean getOccupationGenerale() {
		return OccupationGenerale;
	}

	public void setOccupationGenerale(boolean occupationGenerale) {
		OccupationGenerale = occupationGenerale;
	}	
	public long getDateFin() {
		return this.dateFin;
	}
	
	public void setDateFin(long date) {
		this.dateFin = date;
	}
}
