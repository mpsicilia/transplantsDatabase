package transplants.db.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TransplantDatabase")
@XmlType(propOrder = { "hospitalsOfDatabase" })

public class TransplantDatabase {

	@XmlElement(name = "Hospital")
	@XmlElementWrapper(name = "Hospitals")
	private List<Hospital> hospitalsOfDatabase;

	public TransplantDatabase() {
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

}
