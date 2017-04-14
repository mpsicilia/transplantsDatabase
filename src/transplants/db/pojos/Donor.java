package transplants.db.pojos;

import java.time.LocalDate;

public class Donor extends Person{
	
	private static final long serialVersionUID = 6705263044123670258L;
	private String deadOrAlive;
    //same questions as Patient
	
	public Donor() {
	}
	
	public Donor (String name, LocalDate birthDate, Float weight, Float height, String gender, 
			      String bloodType, String deadOrAlive){
		super( name, birthDate, weight, height, gender, bloodType);
		this.deadOrAlive=deadOrAlive;
		
	}
	
	public Donor (Integer id, String name, LocalDate birthDate, Float weight, Float height, String gender, 
		      String bloodType, String deadOrAlive){
	super(id, name, birthDate, weight, height, gender, bloodType);
	this.deadOrAlive=deadOrAlive;
	
}

	public String getDeadOrAlive() {
		return deadOrAlive;
	}

	public void setDeadOrAlive(String deadOrAlive) {
		this.deadOrAlive = deadOrAlive;
	}

	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", birthDate=" + birthDate
				+ ", weight=" + weight + ", height=" + height + ", gender=" + gender + ","
				+ " bloodType=" + bloodType + ", deadOrAlive=" + deadOrAlive + "]";
	}
	
	
	
}
