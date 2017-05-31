package transplants.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "doctors")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "specialization", "registrationNumber"})
public class Doctor implements Serializable{

	private static final long serialVersionUID = -1701687912909197672L;
	@Id
	@GeneratedValue(generator="doctors")
	@TableGenerator(name="doctors", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="doctors")
	@XmlAttribute
	private Integer id;
	@XmlElement
	private String registrationNumber;
	@XmlElement
	private String specialization;
	@XmlAttribute
	private String name;
	
	@ManyToMany(mappedBy= "doctors") 
	@XmlTransient
	private List<Hospital> hospital;
	@ManyToMany(mappedBy = "doctors")
	@XmlTransient
	private List<Patient> patients;

	//our default constructor
	public Doctor (){
		hospital= new ArrayList<Hospital>();
		patients =new ArrayList<Patient>();
	}
	//M: used
	public Doctor(String nameOfDoctor,String registrationNumber, String specialization){
		this.name=nameOfDoctor;
		this.registrationNumber=registrationNumber;
		this.specialization=specialization;		
	}
	//M: used
	public Doctor(Integer id, String nameOfDoctor,String registrationNumber, String specialization){
		this.id=id;
		this.name=nameOfDoctor;
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

	//getters and setters of all its attributes
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
	public String getNameOfDoctor() {
		return name;
	}
	public void setNameOfDoctor(String nameOfDoctor) {
		this.name = nameOfDoctor;
	}

	public List<Patient> getPatients(){
		return patients;
	}
	
	public void setPatients(List<Patient> patients){
		this.patients=patients;
	}
	public List<Hospital> getHospital() {
		return hospital;
	}

	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}
	
	// Additional method to add to a list
	public boolean addPatient(Patient patient) {
		if (!patients.contains(patient)) {
			return this.patients.add(patient);
		}
		else return false;
	}

	// Additional method to remove from a list
	public boolean removePatient(Patient patient) {
		if (patients.contains(patient)) {
			return this.patients.remove(patient);
		}
		else return false;
	}
	
	public boolean addHospital(Hospital hospi) {
		if (!hospital.contains(hospi)) {
			return this.hospital.add(hospi);
		}
		else return false;
	}
	
	public boolean removeHospital(Hospital hosp) {
		if (hospital.contains(hosp)) {
			return this.hospital.remove(hosp);
		}
		else return false;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", nameOfDoctor=" + name + ", registrationNumber=" + registrationNumber + ", "
				+ "specialization=" + specialization + "]";
	}
	
	
}

