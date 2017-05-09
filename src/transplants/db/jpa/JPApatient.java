package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class JPApatient {

	private JPAmanager jpaManager;

	public JPApatient(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}

	public boolean insert(Patient patient) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(patient);
			jpaManager.getEManager().getTransaction().commit();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Patient patient) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().remove(patient);
			jpaManager.getEManager().getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatePatient(Patient patient, Patient newpatient) {
		try {
			jpaManager.getEManager().getTransaction().begin();

			patient.setName(newpatient.getName());

			patient.setAdditionDate(newpatient.getAdditionDate());

			patient.setBirthDate(newpatient.getBirthDate());

			patient.setBloodType(newpatient.getBloodType());

			patient.setDoctors(newpatient.getDoctors());
			 //n-n relationship newpatient.getDoctors()
			patient.setGender(newpatient.getGender());

			patient.setHeight(newpatient.getHeight());

			patient.setWeight(newpatient.getWeight());

			patient.setLifeExpectancy(newpatient.getLifeExpectancy());

			patient.setHospital(newpatient.getHospital());
			newpatient.getHospital().addPatient(newpatient);

			patient.setPathology(newpatient.getPathology());

			patient.setRequested_organ(newpatient.getRequested_organ());
			// patient.set
			jpaManager.getEManager().getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}
	public List<Patient> searchPatient (String name){
		List<Patient> patients = new ArrayList<Patient>();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Patients WHERE name LIKE '%" + name + "%'", JPApatient.class);
			patients = (List<Patient>) q.getResultList();
			jpaManager.getEManager().getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		return patients;
	}

}
