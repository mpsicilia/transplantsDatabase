package transplants.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "doctors")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"nameOfDoctor", "specialization", "registrationNumber", "hospital", "patients"})
public class Doctor implements Serializable{

	private static final long serialVersionUID = -1701687912909197672L;
	@Id
	@GeneratedValue(generator="doctors")
	@TableGenerator(name="doctors", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="doctors")
	@XmlTransient
	private Integer id;
	@XmlElement
	private String registrationNumber;
	@XmlElement
	private String specialization;
	@XmlAttribute
	private String nameOfDoctor;
	//no estaba antes el atributo de Hospital ni lista de pacientes
	@ManyToMany(mappedBy= "doctors") 	
	private List<Hospital> hospital;
	@ManyToMany(mappedBy = "doctors")
	private List<Patient> patients;

	//our default constructor
	public Doctor (){
		hospital= new ArrayList<Hospital>();
		patients =new ArrayList<Patient>();
	}
	
	public Doctor(String nameOfDoctor,String registrationNumber, String specialization){
		this.nameOfDoctor=nameOfDoctor;
		this.registrationNumber=registrationNumber;
		this.specialization=specialization;		
	}
	
	public Doctor(Integer id, String nameOfDoctor,String registrationNumber, String specialization){
		this.id=id;
		this.nameOfDoctor=nameOfDoctor;
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
		return nameOfDoctor;
	}
	public void setNameOfDoctor(String nameOfDoctor) {
		this.nameOfDoctor = nameOfDoctor;
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
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", nameOfDoctor=" + nameOfDoctor + ", registrationNumber=" + registrationNumber + ", "
				+ "specialization=" + specialization + "]";
	}
	public boolean addHospital(Hospital hospi) {
		if (!hospital.contains(hospi)) {
			return this.hospital.add(hospi);
		}
		else return false;
	}
	//FALTA EL REMOVE NO?????
	
	
	
	
}

