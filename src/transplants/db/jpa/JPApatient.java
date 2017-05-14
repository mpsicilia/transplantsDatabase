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

			System.out.println(patient);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Integer getIdOfPatient(Patient pat) {
		Patient patient = new Patient();
		try {
			//RODRIGO: FINISH THIS!!
			Query q1 = jpaManager.getEManager()
					.createNativeQuery("SELECT * FROM Patients WHERE "
							+ "name LIKE ? AND weight = ? AND height = ?", Patient.class);
			q1.setParameter(1, pat.getName());
			q1.setParameter(2, pat.getWeight());
			q1.setParameter(3, pat.getHeight());
			patient = (Patient) q1.getSingleResult();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return patient.getId();
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
	//lo puedes borrar mira mi donor
	
	/*public boolean updatePatient(Patient patient, Patient newpatient) {
		List<Doctor> listofdoctors = newpatient.getDoctors();
		//List<Requested_organ> lisreq=newpatient.getRequested_organ();
		Hospital hosp = newpatient.getHospital();
		try {

			jpaManager.getEManager().getTransaction().begin();

			patient.setName(newpatient.getName());

			patient.setAdditionDate(newpatient.getAdditionDate());

			patient.setBirthDate(newpatient.getBirthDate());

			patient.setBloodType(newpatient.getBloodType());

			patient.setDoctors(listofdoctors);
			Iterator<Doctor> it = listofdoctors.iterator();
			// N-N relationship
			while (it.hasNext()) {
				Doctor doct = it.next();
				patient.addDoctor(doct);
				doct.addPatient(patient);

			}

			patient.setGender(newpatient.getGender());

			patient.setHeight(newpatient.getHeight());

			patient.setWeight(newpatient.getWeight());

			patient.setLifeExpectancy(newpatient.getLifeExpectancy());

			patient.setHospital(hosp);
			hosp.addPatient(patient);

			patient.setPathology(newpatient.getPathology());

			patient.setRequested_organ(newpatient.getRequested_organ());
			
			jpaManager.getEManager().getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}*/

	public Hospital updateHospitalofPatient(Patient pat, Hospital hosp) {
		try {
			jpaManager.getEManager().getTransaction().begin();
			pat.setHospital(hosp);
			hosp.addPatient(pat);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hosp;
	}

	public List<Patient> searchPatient(String name) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			jpaManager.getEManager().getTransaction().begin();
			Query q = jpaManager.getEManager()
					.createNativeQuery("SELECT * FROM Patients WHERE " + "name LIKE '%" + name + "%'", Patient.class);
			patients = (List<Patient>) q.getResultList();
			jpaManager.getEManager().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}
   //PARA LA RELACION ENTRE PACIENTE Y EL HOSPITAL
	public Hospital getHospitalbyid(Integer id) {
		Hospital hosp = new Hospital();
		try {
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager()
					.createNativeQuery("SELECT * FROM Hospitals WHERE id= " + id   , Hospital.class);
			hosp = (Hospital) q1.getSingleResult();
			jpaManager.getEManager().getTransaction().commit();
			return hosp;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hosp;
	}

}
