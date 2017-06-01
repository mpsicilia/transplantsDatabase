package transplants.db.pojos;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "Organs")

public class Organ implements Serializable{

	private static final long serialVersionUID = -5113823135998745845L;
	@Id
	@GeneratedValue(generator="Organs")
	@TableGenerator(name="Organs", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="Organs")
	private Integer id;
	private String name;
	private Float weight;	
	private String typeOfDonation;	
	private Date lifeOfOrgan;
	@OneToOne
	@JoinColumn(name = "requested_id") // FK
	private Requested_organ requested_organ;
	
	@ManyToOne
	@JoinColumn(name= "donor_id")//FK
	private Donor donor;
	public Organ (){
		
	}
	
	public Organ(Integer id,String name, Float weight, String typeOfDonation, Date lifeOfOrgan) {
		this.id=id;
		this.name = name;
		this.weight = weight;
		this.lifeOfOrgan= lifeOfOrgan;
		this.typeOfDonation = typeOfDonation;
	}
	
	public Organ(String name, Float weight, String typeOfDonation,Date lifeOfOrgan) {
		this.name = name;
		this.weight = weight;
		this.lifeOfOrgan= lifeOfOrgan;
		this.typeOfDonation = typeOfDonation;
	}
	//equals and hashcode
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
		Organ other = (Organ) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	//Getters and setters
	
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

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getTypeOfDonation() {
		return typeOfDonation;
	}

	public void setTypeOfDonation(String typeOfDonation) {
		this.typeOfDonation = typeOfDonation;
	}
	public Date getLifeOfOrgan() {
		return lifeOfOrgan;
	}

	public void setLifeOfOrgan(Date lifeOfOrgan) {
		this.lifeOfOrgan = lifeOfOrgan;
	}

	public Requested_organ getRequested_organ() {
		return requested_organ;
	}

	public void setRequested_organ(Requested_organ requested_organ) {
		this.requested_organ = requested_organ;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	@Override
	public String toString() {
		return "Organ [id=" + id + ", name=" + name + ", weight=" + weight + ", typeOfDonation=" + typeOfDonation
				+ ", lifeOfOrgan=" + lifeOfOrgan + "]";
	}
	
}

