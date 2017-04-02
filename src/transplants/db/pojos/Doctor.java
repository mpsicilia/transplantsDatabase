package transplants.db.pojos;

import java.io.Serializable;

public class Doctor implements Serializable{
	
	private static final long serialVersionUID = -7672023348972074686L;//generated verdad?
	private Integer id;
	private String registrationNumber;
	private String specialization;
	
	//our default constructor
	public Doctor (){
	}
	
	public Doctor(String registrationNumber, String specialization){
		this.registrationNumber=registrationNumber;
		this.specialization=specialization;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	//getters and setters of all its atributes
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	
}
