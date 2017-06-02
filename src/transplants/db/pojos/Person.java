package transplants.db.pojos;


import java.sql.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import transplants.db.xml.SQLDateAdapter;

@Entity
//This is our parent class for donor and patient. The way in which this inheritance is structure
//is so in a future, a patient could be a donor and vice versa.
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType (propOrder = {"id", "name", "birthDate", "gender", "weight", "height", "bloodType"})
public abstract class Person {
	
	@Id
	@GeneratedValue(generator="Patients")
	@TableGenerator(name="Patients", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="Patients")
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	protected String name;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	protected Date birthDate;
	@XmlElement
	protected Float weight;
	@XmlElement
	protected Float height;
	@XmlElement
	protected String gender;
	@XmlElement
	protected String bloodType;

	
	public Person (){
	}
	
	public Person(String name, Date birthDate, Float weight, Float height, String gender, String bloodType) {
		super();
		this.name=name;
		this.birthDate=birthDate;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
		this.bloodType = bloodType;
	}
	
	public Person(Integer id, String name, Date birthDate, Float weight, Float height, String gender, String bloodType) {
		super();
		this.id = id;
		this.name=name;
		this.birthDate=birthDate;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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
	
	
}
