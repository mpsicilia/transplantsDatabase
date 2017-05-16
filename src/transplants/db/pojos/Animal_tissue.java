package transplants.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "Animal_tissues")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType (propOrder = {"name", "pathology", "typeOfTissue", "lifeExpTissue", "requested_organs"})
public class Animal_tissue implements Serializable {

	private static final long serialVersionUID = -7167881940806327162L;
	@Id 
	@GeneratedValue(generator="Animal_tissues")
	@TableGenerator(name="Animal_tissues", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="Animal_tissues")
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String typeOfTissue;
	@XmlElement
	private String pathology;
	@XmlElement
	private Date lifeExpTissue;
	
	@ManyToMany(mappedBy = "animalTissues") //bc it is mapped by the list of the other class. ver apuntes
	@XmlElement (name = "Requested organ")
	@XmlElementWrapper (name  = "Requested organs")
	private List<Requested_organ> requested_organs;
	
	
	public Animal_tissue() {
		super();
		requested_organs=new ArrayList<Requested_organ>();
	}
	
		
	public Animal_tissue(Integer id, String name, String typeOfTissue, String pathology, Date lifeExpTissue) {
		super();
		this.id = id;
		this.name = name;
		this.typeOfTissue = typeOfTissue;
		this.pathology = pathology;
		this.lifeExpTissue= lifeExpTissue;
	}
	
	public Animal_tissue(String name, String typeOfTissue, String pathology, Date lifeExpTissue) {
		super();
		this.name = name;
		this.typeOfTissue = typeOfTissue;
		this.pathology = pathology;
		this.lifeExpTissue= lifeExpTissue;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Animal_tissue other = (Animal_tissue) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Animal_tissue [id = " + id + ", name=" + name + ", typeOfTissue=" + typeOfTissue + ", pathology="
				+ pathology + ", lifeExpTissue =" + lifeExpTissue + "]";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeOfTissue() {
		return typeOfTissue;
	}
	public void setTypeOfTissue(String typeOfTissue) {
		this.typeOfTissue = typeOfTissue;
	}
	public String getPathology() {
		return pathology;
	}
	public void setPathology(String pathology) {
		this.pathology = pathology;
	}
	
	public Date getLifeExpTissue() {
		return lifeExpTissue;
	}


	public void setLifeExpTissue(Date lifeExpTissue) {
		this.lifeExpTissue = lifeExpTissue;
	}


	public List<Requested_organ> getRequested_organs(){
		return requested_organs;
	}
	
	public void setRequested_organs(List<Requested_organ> requested_organs){
		this.requested_organs=requested_organs;
	}

	
	public void addRequestedOrgan(Requested_organ requested_organ) {
		if (!requested_organs.contains(requested_organ)) {
			this.requested_organs.add(requested_organ);
		}
	}

	// Additional method to remove from a list
	public void removeRequestedOrgan(Requested_organ requested_organ) {
		if (requested_organs.contains(requested_organ)) {
			this.requested_organs.remove(requested_organ);
		}
	}
}

