package transplants.db.pojos;

import java.sql.Date;

public class Person {
	protected Integer id;
	protected Date birthDate;
	protected Float weight;
	protected Float height;
	protected String gender;
	protected String bloodType;
	//faltan las FK
	
	public Person (){
	}
	public Person(Integer id, Date birthDate, Float weight, Float height, String gender, String bloodType) {
		super();
		this.id = id;
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
		this.bloodType = bloodType;
	}
	
	//Should we put a method of compatibility test??
	
	
}
