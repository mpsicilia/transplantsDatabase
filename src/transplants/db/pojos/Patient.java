package transplants.db.pojos;

import java.sql.Date;

public class Patient extends Person{

	private static final long serialVersionUID = -6077511442892940517L;
	//should this one implement Serializable? Should person? Or both?
	private String lifeExpectancy;
	private Date additionDate;
	
	public Patient(){
		
	}
	
	public Patient(Integer id,Date birthDate,Float weight, Float height, String gender, String bloodType, 
			String lifeExpectancy, Date additionDate){
		super(id, birthDate,weight,height,gender, bloodType);
		this.lifeExpectancy=lifeExpectancy;
		this.additionDate=additionDate;
	}
	
	//what about the parent class?? Do we create them in the parent class??
	public String getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(String lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public Date getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Date additionDate) {
		this.additionDate = additionDate;
	}
	
	
	
}
