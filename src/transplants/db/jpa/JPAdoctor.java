package transplants.db.jpa;

import java.util.Iterator;
import java.util.List;

import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;

public class JPAdoctor {

	private JPAmanager jpaManager;

	public JPAdoctor(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}

	public boolean insert(Doctor doctor) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(doctor);
			jpaManager.getEManager().getTransaction().commit();

			List<Hospital> hospitalofdoctor = doctor.getHospital();
			Iterator<Hospital> it1 = hospitalofdoctor.iterator();

			while (it1.hasNext()) {
				Hospital hosp = it1.next();
				doctor.addHospital(hosp);
				hosp.addDoctor(doctor);
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
