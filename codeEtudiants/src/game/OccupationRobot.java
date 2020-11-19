package game;

public class OccupationRobot {
	private long dateFin;
	private boolean occupationGenerale;

	public OccupationRobot(long dateFin) {
		this.dateFin = dateFin;  
	}
	
	public OccupationRobot() {
		
	}

	public boolean getOccupationGenerale() {
		return this.occupationGenerale;
	}

	public void setOccupationGenerale(boolean occupationGenerale) {
		this.occupationGenerale = occupationGenerale;
	}	
	public long getDateFin() {
		return this.dateFin;
	}
	
	public void setDateFin(long date) {
		this.dateFin = date;
	}
}
