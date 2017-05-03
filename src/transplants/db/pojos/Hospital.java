package transplants.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "hospitals")


public class Hospital implements Serializable{

	private static final long serialVersionUID = -2900229453507535621L;
	@Id
	@GeneratedValue(generator="hospitals")
	@TableGenerator(name="hospitals", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="hospitals")
	private Integer id;
	private String name;
	private String phone_number;
	private String address;
	private String city;
	private String postcode;
	private String country;
	//Hospital is related with doctor and patient
	//with doctors is an n-n because one doctor can work at many hospitals and 1 hospital can have many doctors
	@ManyToMany
	@JoinTable(name="hospitalsdoctors",//name of the n-n table
	joinColumns={@JoinColumn(name="hospital_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="doctor_id", referencedColumnName="id")})
	private List<Doctor> doctors;
	//in the case of patients we have one to many because 1 hospital can host many patients
	@OneToMany(mappedBy="hospital")
	private List<Patient> patients;
	
	
	public Hospital (){
		super();
		doctors= new ArrayList<Doctor>();
		patients= new ArrayList<Patient>();
		}
	
	public Hospital(String name, String phone_number, String address, String city, String postcode,
			String country) {
		this.name = name;
		this.phone_number = phone_number;
		this.address = address;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
	}	
	
	public Hospital (String name){
		this.name=name;
	}
	
	public Hospital(int id, String name, String phone_number, String address, String city, String postcode,
			String country) {
		this.id=id;
		this.name = name;
		this.phone_number = phone_number;
		this.address = address;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
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
		Hospital other = (Hospital) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public List<Doctor> getDoctors() {
		return doctors;
	}
	
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	public List<Patient> getPatients(){
		return patients;
	}
	
	public void setPatients(List<Patient> patients){
		this.patients=patients;
	}

	// Additional method to add to a list
		public boolean addDoctor(Doctor doctor) {
			if (!doctors.contains(doctor)) {
				 return this.doctors.add(doctor);
			}
			else return false;
		}

		// Additional method to remove from a list
		public boolean removeDoctor(Doctor doctor) {
			if (doctors.contains(doctor)) {
				return this.doctors.remove(doctor);
			}
			else return false;
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
	
		return "Hospital:id =" + id + ", name=" + name + ", phone_number=" + phone_number + ", address=" + address + ", city=" + city
				+ ", postcode=" + postcode + ", country=" + country + "]";
	}
	
	
}
