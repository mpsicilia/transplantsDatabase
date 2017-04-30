package transplants.db.jpa;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.pojos.Hospital;

public class JPAhospital {
 public void create (Hospital hospital) throws IOException{
	// Get the entity manager
			EntityManager em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
 }
}
