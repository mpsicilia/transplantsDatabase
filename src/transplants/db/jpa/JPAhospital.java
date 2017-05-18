package transplants.db.jpa;

import java.util.List;

import javax.persistence.Query;

import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;

public class JPAhospital {
	private JPAmanager jpaManager;

	public JPAhospital(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}
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
	public String hospitalofpatient(String namepat){
		String namehosp = "";
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT name FROM Hospitals AS Hosp JOIN Patients AS Pat ON Hosp.id=Pat.hospital_id "
					+ "WHERE Pat.name LIKE ? '" ,Hospital.class);
			q1.setParameter(1, namepat);
			namehosp=(String) q1.getSingleResult();
			jpaManager.getEManager().getTransaction().commit();
			return namehosp;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return namehosp;
	}
	
	public Hospital getHospitalbyid(Integer idhosp) {
		Hospital hosp = new Hospital();
		try {
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM Hospitals WHERE id = ? ",Hospital.class);
			q1.setParameter(1, idhosp);
			hosp = (Hospital) q1.getSingleResult();
			jpaManager.getEManager().getTransaction().commit();
			return hosp;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hosp;
	}
	
	public Hospital getHospital(Hospital hosp) {
		Hospital hospi = new Hospital();
		try {
			Query q1 = jpaManager.getEManager().createNativeQuery(
					"SELECT * FROM Hospitals WHERE name LIKE ? AND phoneNumber LIKE ? AND address LIKE ? AND city LIKE ? AND postcode"
							+ " LIKE ? AND country LIKE ? ",Hospital.class);
						
			q1.setParameter(1, hosp.getName());
			q1.setParameter(2, hosp.getPhone_number());
			q1.setParameter(3, hosp.getAddress());
			q1.setParameter(4, hosp.getCity());
			q1.setParameter(5, hosp.getPostcode());
			q1.setParameter(6, hosp.getCountry());
		
			hospi = (Hospital) q1.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hospi;
	}

	public boolean removeHospital(Hospital hosp) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().remove(hosp);
			jpaManager.getEManager().getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/*public List<Hospital> selectAllHospitals() {
		try {
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM Hospitals", Hospital.class);
			List<Hospital> allhospitals = (List<Hospital>) q1.getResultList();
			jpaManager.getEManager().getTransaction().commit();
			return allhospitals;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/

}