package transplants.db.pojos;

import java.sql.Date;
import java.time.LocalDate;

public class Patient extends Person{

	private static final long serialVersionUID = -6077511442892940517L;
	//should this one implement Serializable? Should person? Or both?
	private Integer lifeExpectancy;
	private String pathology;
	private LocalDate additionDate;
	
	public Patient(){
		
	}
	
	public Patient(LocalDate birthDate,Float weight, Float height, String gender, String pathology, String bloodType, 
			Integer lifeExpectancy, LocalDate additionDate){
		super(birthDate,weight,height,gender, bloodType);
		this.pathology = pathology;
		this.lifeExpectancy=lifeExpectancy;
		this.additionDate=additionDate;
	}
	
	public Patient(Integer id, LocalDate birthDate,Float weight, Float height, String gender, String pathology, 
			String bloodType, Integer lifeExpectancy, LocalDate additionDate){
		super(id, birthDate,weight,height,gender, bloodType);
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

	public LocalDate getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(LocalDate additionDate) {
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
		return "Patient [id=" + id + ", birthDate=" + birthDate + ", weight=" + weight + 
				", height=" + height + ", gender=" + gender + "lifeExpectancy=" + lifeExpectancy + 
				", additionDate=" + additionDate + ", bloodType=" + bloodType + "]";
	}
	
	
	
}
