
public abstract class Evenement {
	long date;
	public Evenement(long date) {
		this.date=date;
	}
	long getDate() {
		return this.date;
	}
	abstract void execute();
	
}
