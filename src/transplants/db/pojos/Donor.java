package transplants.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Donors")
public class Donor extends Person implements Serializable{
	@Id
	@GeneratedValue(generator="Donors")
	@TableGenerator(name="Donors", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="Donors")
	private Integer id;
	private static final long serialVersionUID = 6705263044123670258L;
	private String deadOrAlive;
   
	@OneToMany(mappedBy="donor") 
	private List<Organ> organs;
	
	public Donor() {
		organs=new ArrayList<Organ>();
	}
	
	public Donor (String name, Date birthDate, Float weight, Float height, String gender, 
			      String deadOrAlive, String bloodType){
		super(name, birthDate, weight, height, gender, bloodType);
		this.deadOrAlive=deadOrAlive;
		super.birthDate=birthDate;
		
	}
	
	public Donor (Integer id, String name, Date birthDate, Float weight, Float height, St
			ring gender, 
		      String deadOrAlive, String bloodType){
		super(name, birthDate, weight, height, gender, bloodType);
<<<<<<< HEAD
		this.id=id;
=======
	this.id=id;
>>>>>>> branch 'master' of https://github.com/mpsicilia/transplantsDatabase.git
		this.deadOrAlive=deadOrAlive;
		super.birthDate=birthDate;
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeadOrAlive() {
		return deadOrAlive;
	}

	public void setDeadOrAlive(String deadOrAlive) {
		this.deadOrAlive = deadOrAlive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Organ> getOrgans(){
		return organs;
	}
	
	public void setOrgans(List<Organ> organs){
		this.organs=organs;
	}
	public boolean addOrgan(Organ organ) {
		if (!organs.contains(organ)) {
			 return this.organs.add(organ);
		}
		else return false;
	}

	// Additional method to remove from a list
	public boolean removeOrgan(Organ organ) {
		if (organs.contains(organ)) {
			return this.organs.remove(organ);
		}
		else return false;
	}

	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", birthDate=" + birthDate
				+ ", weight=" + weight + ", height=" + height + ", gender=" + gender + ","
				+ " bloodType=" + bloodType + ", deadOrAlive=" + deadOrAlive + "]";
	}
	
	
	
}

