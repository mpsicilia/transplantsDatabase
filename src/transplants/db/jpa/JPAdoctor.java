package transplants.db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.pojos.Doctor;


public class JPAdoctor {
	public boolean create(Doctor doctor) {
		// Get the entity manager
		try {
			EntityManager em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			return true;
			//falta codigo
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
