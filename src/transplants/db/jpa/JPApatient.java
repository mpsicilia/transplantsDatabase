package transplants.db.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import transplants.db.pojos.Doctor;

import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;

public class JPApatient {

	private JPAmanager jpaManager;

	public JPApatient(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}

	public boolean insert(Patient patient) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().persist(patient);
			jpaManager.getEManager().getTransaction().commit();

			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Patient getPatient(Patient pat) {
		Patient patient = new Patient();
		try {
			Query q1 = jpaManager.getEManager().createNativeQuery(
					"SELECT * FROM Patients WHERE name LIKE ? AND birthDate = ? AND weight = ? AND height = ? AND gender"
							+ " LIKE ? AND pathology LIKE ? AND bloodType LIKE ? AND additionDate = ?"
							+ "AND lifeExpectancy = ?", Patient.class);
			q1.setParameter(1, pat.getName());
			q1.setParameter(2, pat.getBirthDate());
			q1.setParameter(3, pat.getWeight());
			q1.setParameter(4, pat.getHeight());
			q1.setParameter(5, pat.getGender());
			q1.setParameter(6, pat.getPathology());
			q1.setParameter(7, pat.getBloodType());
			q1.setParameter(8, pat.getAdditionDate());
			patient = (Patient) q1.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return patient;
	}

	public boolean removePatient(Patient patient) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().remove(patient);
			jpaManager.getEManager().getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	 

	public boolean updateHospitalofPatient(Patient pat, Hospital hosp) {
		try {
			jpaManager.getEManager().getTransaction().begin();
			pat.setHospital(hosp);
			hosp.addPatient(pat);
			jpaManager.getEManager().getTransaction().commit();
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public List<Patient> selectAllPatients() {
		try {
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM Patients", Patient.class);
			List<Patient> allpatients = (List<Patient>) q1.getResultList();
			jpaManager.getEManager().getTransaction().commit();
			return allpatients;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Patient> searchPatient(String name) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			jpaManager.getEManager().getTransaction().begin();
			Query q = jpaManager.getEManager()
					.createNativeQuery("SELECT * FROM Patients WHERE name LIKE ?", Patient.class);
			q.setParameter(1, name);
			patients = (List<Patient>) q.getResultList();
			jpaManager.getEManager().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	
	

}
