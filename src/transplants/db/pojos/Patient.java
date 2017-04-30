package transplants.db.pojos;

import java.sql.Date;
import java.time.LocalDate;

public class Patient extends Person{


	private static final long serialVersionUID = 5283904286714952072L;
	//should this one implement Serializable? Should person? Or both?
	private Integer lifeExpectancy;
	private String pathology;
	private Date additionDate;
	
	public Patient(){
		
	}
	
	public Patient(String name, Date birthDate,Float weight, Float height, String gender, String pathology, String bloodType, 
			Integer lifeExpectancy, Date additionDate){
		super(name, birthDate,weight,height,gender, bloodType);
		this.pathology = pathology;
		this.lifeExpectancy=lifeExpectancy;
		this.additionDate=additionDate;
	}
	
	public Patient(Integer id, String name, Date birthDate,Float weight, Float height, String gender, String pathology, 
			String bloodType, Integer lifeExpectancy, Date additionDate){
		super(id, name, birthDate,weight,height,gender, bloodType);
		this.lifeExpectancy=lifeExpectancy;
		this.additionDate=additionDate;
	}
	
	//what about the parent class?? Do we create them in the parent class??
	public Integer getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(Integer lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public Date getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Date additionDate) {
		this.additionDate = additionDate;
	}
	
	public String getPathology() {
		return pathology;
	}

	public void setPathology(String pathology) {
		this.pathology = pathology;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", weight=" + weight + 
				", height=" + height + ", gender=" + gender + "lifeExpectancy=" + lifeExpectancy + 
				", additionDate=" + additionDate + ", bloodType=" + bloodType + "]";
	}

	public int compareTo(Patient p) {
		return additionDate.compareTo(p.getAdditionDate());
	}
	
	
	
}
