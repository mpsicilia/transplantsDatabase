package transplants.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;



@Entity
@Table (name= "Requested_organs")

public class Requested_organ implements Serializable {

	private static final long serialVersionUID = 4061202503200538758L;
	
	@Id
	@GeneratedValue(generator="Requested_organs")
	@TableGenerator(name="Requested_organs", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="Requested_organs")
	private Integer id;
	private String name;
	private Float maxWeight;
	private Float minWeight;

	@ManyToOne
	@JoinColumn(name = "patient_id") // the FK
	private Patient patient;
	
	@OneToOne(mappedBy="requested_organ")
	private Organ organ;
	
	@ManyToMany
	//the attribute above joins both tables creating requested_organs/animals
	@JoinTable(name="RequestedOrgan_AnimalsTissues",
		joinColumns={@JoinColumn(name="requested_id", referencedColumnName="id")},
	    inverseJoinColumns={@JoinColumn(name="animal_id", referencedColumnName="id")})

	//req organ has a list of animals
	//animal has a list of reqs
	private List<Animal_tissue> animalTissues;
	
	public Requested_organ(){
		animalTissues=new ArrayList<Animal_tissue>();
	}
	
	public Requested_organ(Integer id, String name, Float maxWeight, Float minWeight) {
		this.id = id;
		this.name = name;
		this.maxWeight = maxWeight;
		this.minWeight = minWeight;
	}
	public Requested_organ(String name, Float maxWeight, Float minWeight) {
		this.name = name;
		this.maxWeight = maxWeight;
		this.minWeight = minWeight;
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
		Requested_organ other = (Requested_organ) obj;
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
	public Float getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(Float maxWeight) {
		this.maxWeight = maxWeight;
	}
	public Float getMinWeight() {
		return minWeight;
	}
	public void setMinWeight(Float minWeight) {
		this.minWeight = minWeight;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Animal_tissue> getAnimalTissues(){
		return animalTissues;
	}
	
	public void setAnimalTissues(List<Animal_tissue> animalTissues){
		this.animalTissues=animalTissues;
	}	

		@Override
		public String toString() {
			return "Requested_organ [ name=" + name + ", maxWeight=" + maxWeight + ", minWeight="
					+ minWeight + "]";
		}

}
