package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import transplants.db.pojos.Donor;
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
			jpaManager.getEManager().persist(patient);
			jpaManager.getEManager().getTransaction().commit();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/*public Integer getIdOfPatient(Patient pat){
		Patient patient=new Patient();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT id FROM Patients "
					+ "WHERE (name LIKE '" + pat.getName() + "') "
					+ "AND (weight = " + pat.getWeight() + ")"
					+ "AND (height = " + pat.getHeight() + ") "
					+ "AND (gender LIKE '" + pat.getGender() + "')"
					+ "AND (pathology LIKE '" + pat.getPathology() + "') "
					+"AND ( lifeExpectancy  LIKE'" +pat.getLifeExpectancy() + "')"
				    +"AND ( additionDate  LIKE'" +pat.getAdditionDate() + "')"
				    +"AND ( birthDate  LIKE'" +pat.getAdditionDate() + "')"
					+ "AND (bloodType LIKE '" + pat.getBloodType() + "')", Patient.class);
			patient = (Patient) q1.getSingleResult();
			jpaManager.getEManager().getTransaction().commit();		
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return patient.getId();
	}*/

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

	public boolean updatePatient(Patient patient, Patient newpatient) {
		try {
			jpaManager.getEManager().getTransaction().begin();

			patient.setName(newpatient.getName());

			patient.setAdditionDate(newpatient.getAdditionDate());

			patient.setBirthDate(newpatient.getBirthDate());

			patient.setBloodType(newpatient.getBloodType());

			patient.setDoctors(newpatient.getDoctors());
			 
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
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Patients WHERE "
					+ "name LIKE '%" + name + "%'", Patient.class);
			patients = (List<Patient>) q.getResultList();
			jpaManager.getEManager().getTransaction().commit();
			
					
		}catch (Exception e){
			e.printStackTrace();
		}
		return patients;
	}

	

}
