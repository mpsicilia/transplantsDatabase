package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;

public class JPAhospital {
	
	//ATTRIBUTES
	private JPAmanager jpaManager;

	//METHODS
	public JPAhospital(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}
	
	//This is used in order to insert a hospital
	public boolean insert(Hospital hosp) {
		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().persist(hosp);
			jpaManager.getEManager().getTransaction().commit();			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//We are using this method to look for a hospital of a patient
	public Hospital hospitalofpatient(String namepat){
		Hospital hospital=new Hospital();
		try{			
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM Hospitals AS Hosp JOIN Patients AS Pat ON Hosp.id=Pat.hospital_id"
					+ " WHERE Pat.name LIKE '" + namepat + "'" ,Hospital.class);
			hospital=(Hospital) q1.getSingleResult();
			return hospital;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return hospital;
	}
	
	//We are using this method so we can search for a specific hospital; that is why we are 
	//searching by id
	public Hospital getHospitalbyid(Integer idhosp) {
		Hospital hosp = new Hospital();
		try {
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM Hospitals WHERE id = ? ",Hospital.class);
			q1.setParameter(1, idhosp);
			hosp = (Hospital) q1.getSingleResult();
			return hosp;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hosp;
	}

	
	//We are using this method so we return all the patients that a hospital has
	public List<Patient> searchAllPatients(Hospital hospit){
		List<Patient> patients = new ArrayList<Patient>();
		try {			
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Patients AS Pat JOIN Hospitals AS Hosp "
					+ "ON Pat.hospital_id=Hosp.id WHERE Hosp.id= '" + hospit.getId() + "'", Patient.class);
			patients = (List<Patient>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

}
