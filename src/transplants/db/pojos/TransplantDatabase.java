package transplants.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//We created this class in order to have a root element for our XML file. Is not a real entity as
//we can see that is why is only annotated with xml. This root element is going to contain a list
//of hospitals.
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TransplantDatabase")
@XmlType(propOrder = {"nameOfDatabase", "hospitalsOfDatabase" })

public class TransplantDatabase implements Serializable {

	private static final long serialVersionUID = 9095209125569144711L;
	@XmlAttribute
	private String nameOfDatabase;
	@XmlElement(name = "Hospital")
	@XmlElementWrapper(name = "Hospitals")
	private List<Hospital> hospitalsOfDatabase;

	public TransplantDatabase (){
		hospitalsOfDatabase = new ArrayList<Hospital>();
	}
	
	public TransplantDatabase (String name){
		this.nameOfDatabase = name;
		hospitalsOfDatabase = new ArrayList<Hospital>();
	}
	
	public List<Hospital> getAllHospOFDatabase() {
		return hospitalsOfDatabase;
	}

	// Addition of Hospitals
	public boolean addHospital(Hospital hosp) {
		if (!hospitalsOfDatabase.contains(hosp)) {
			return this.hospitalsOfDatabase.add(hosp);
		} else
			return false;
	}

	// Remove hospital
	public boolean removeHospital(Hospital hosp) {
		if (!hospitalsOfDatabase.contains(hosp)) {
			return this.hospitalsOfDatabase.remove(hosp);
		} else
		return false;
	}

	public String getNameOfDatabase() {
		return nameOfDatabase;
	}

	public void setNameOfDatabase(String nameOfDatabase) {
		this.nameOfDatabase = nameOfDatabase;
	}
	
}
