package transplants.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 8117129296546913171L;
	protected Integer id;
	protected LocalDate birthDate;
	protected Float weight;
	protected Float height;
	protected String gender;
	protected String bloodType;
	//faltan las FK
	
	public Person (){
	}
	
	public Person(LocalDate birthDate, Float weight, Float height, String gender, String bloodType) {
		super();
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
		this.bloodType = bloodType;
	}
	
	public Person(Integer id, LocalDate birthDate, Float weight, Float height, String gender, String bloodType) {
		super();
		this.id = id;
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
		this.bloodType = bloodType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
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
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//Should we put a method of compatibility test??
	
	
}
