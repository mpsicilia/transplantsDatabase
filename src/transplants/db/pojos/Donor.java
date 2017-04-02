package transplants.db.pojos;

public class Donor extends Person{
	private String deadOrAlive;
//same questions as Patient
	public Donor(String deadOrAlive) {
		super();
		this.deadOrAlive = deadOrAlive;
	}

	public String getDeadOrAlive() {
		return deadOrAlive;
	}

	public void setDeadOrAlive(String deadOrAlive) {
		this.deadOrAlive = deadOrAlive;
	}
	
	
}
