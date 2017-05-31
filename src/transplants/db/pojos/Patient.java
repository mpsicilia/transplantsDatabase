package transplants.db.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import transplants.db.xml.SQLDateAdapter;

import java.io.Serializable;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name = "Patients")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder = {"pathology", "additionDate", "lifeExpectancy", "score"})
public class Patient extends Person implements Serializable {

	private static final long serialVersionUID = 5283904286714952072L;

	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date lifeExpectancy;
	@XmlElement
	private String pathology;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date additionDate;
	@XmlElement
	private long score;
	
	@ManyToMany
	//the attribute above joins both tables creating doctors/patients
	@JoinTable(name="doctors_patients",
		joinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")},
	    inverseJoinColumns={@JoinColumn(name="doctor_id", referencedColumnName="id")})
	//joincolumn makes reference to the class Patient.
	//inversejoincolumns makes reference to the opposite class-->doctor
	//Patient has a list of doctors
	//Doctor has a list of patients
	@XmlTransient //to avoid infinite loops, bc a patient has a list of doctors and the doctor has a list of patients and this will never end
	private List<Doctor> doctors;
	
	@ManyToOne(fetch = FetchType.LAZY)//only get the hospital when u ask for it (with gethospital)
	@JoinColumn(name = "hospital_id") // the FK
	//Hospitals have list of patients and patient contains hospital, I made it transient to avoid an infinite loop
	//Each hospital shows its patients
	@XmlTransient 
	private Hospital hospital;
	
	@OneToMany(mappedBy="patient")
	@XmlTransient
	private List<Requested_organ> requested_organ;
	//M: used
	public Patient(){
		doctors= new ArrayList<Doctor>();
		requested_organ=new ArrayList<Requested_organ>();
	}
	
	//M: used
	public Patient(String name, Date birthDate,Float weight, Float height, String gender, String pathology, String bloodType, 
			 Date additionDate,Date lifeExpectancy){
		super(name, birthDate,weight,height,gender, bloodType);
		this.pathology = pathology;
		this.lifeExpectancy=lifeExpectancy;
		this.additionDate=additionDate;
		this.generateScore();
		doctors= new ArrayList<Doctor>();
		requested_organ=new ArrayList<Requested_organ>();
	}
	//M: used
	public Patient(Integer id, String name, Date birthDate,Float weight, Float height, String gender, String pathology, 
			String bloodType, Date additionDate, Date lifeExpectancy){
		super(id, name, birthDate,weight,height,gender, bloodType);
//		this.id= id;
		this.lifeExpectancy=lifeExpectancy;
		this.additionDate=additionDate;
		this.generateScore();
		doctors= new ArrayList<Doctor>();
		requested_organ=new ArrayList<Requested_organ>();
	}
	
	public Patient(Integer id, String name, Date birthDate,Float weight, Float height, String gender, String pathology, 
			String bloodType, Date additionDate, Date lifeExpectancy, Long score){
		super(id, name, birthDate,weight,height,gender, bloodType);
//		this.id= id;
		this.lifeExpectancy=lifeExpectancy;
		this.additionDate=additionDate;
		this.score= score;
		doctors= new ArrayList<Doctor>();
		requested_organ=new ArrayList<Requested_organ>();
	}
	public void generateScore(){
		LocalDate localLifeExp= lifeExpectancy.toLocalDate();
		LocalDate localAdditionDate= additionDate.toLocalDate();
		LocalDate today= LocalDate.now();
		Period daysSinceAddition= Period.between(today, localAdditionDate);
		long s1= daysSinceAddition.getDays();
		Period daysLifeExp= Period.between(today, localLifeExp);		
		long s2= daysLifeExp.getDays();
		long score= s1+ 1/s2*100;		
		this.score = score;

		
	}
	public long getScore(){
		
		return score;
	}
	public void setScore(long score){
		this.score= score;
	}
	//what about the parent class?? Do we create them in the parent class??
	public Date getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(Date lifeExpectancy) {
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
	
	public List<Requested_organ> getRequested_organ(){
		return requested_organ;
	}
	public void setRequested_organ (List<Requested_organ> requested_organ){
		this.requested_organ= requested_organ;
	}
	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Hospital getHospital() {
		return hospital;
	}
	//C used from generic
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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


		@Override
		public String toString() {
			return "Patient [lifeExpectancy=" + lifeExpectancy + ", pathology=" + pathology + ", additionDate="
					+ additionDate + ", score=" + score + ", name=" + name + ", birthDate=" + birthDate + ", weight="
					+ weight + ", height=" + height + ", gender=" + gender + ", bloodType=" + bloodType + "]";
		}
	
	
}
