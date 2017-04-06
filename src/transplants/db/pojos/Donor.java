package transplants.db.pojos;

public class Donor extends Person{
	
	private static final long serialVersionUID = -3931573024903104495L;
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
