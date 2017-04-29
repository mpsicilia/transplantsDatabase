package transplants.db.pojos;

import java.io.Serializable;

public class Organ implements Serializable{

	private static final long serialVersionUID = -5113823135998745845L;
	private Integer id;
	private String name;
	private Float weight;
	private String typeOfDonation;
	
	public Organ (){
		
	}
	
	public Organ(Integer id,String name, Float weight, String typeOfDonation) {
		//super();
		this.id=id;
		this.name = name;
		this.weight = weight;
		this.typeOfDonation = typeOfDonation;
	}
	
	public Organ(String name, Float weight, String typeOfDonation) {
		//super();
		this.name = name;
		this.weight = weight;
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

	@Override
	public String toString() {
		return "Organ [\nname=" + name + ", \nweight=" + weight + ", \ntypeOfDonation=" + typeOfDonation + "]";
	}
	
	

	
}
