package transplants.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.pojos.Donor;
import transplants.db.pojos.Organ;

public class JPAdonor {
	private EntityManager em;

	public JPAdonor(JPAmanager jpamanager){
		em=jpamanager.getEManager();
		
	}
public boolean insert(Donor donor){
	
	try {
	
		//EntityManager em = Persistence.createEntityManagerFactory("transplants-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	
		em.getTransaction().begin();
		// Store the object
		em.persist(donor);
		
		em.getTransaction().commit();
		
		
		em.close();
		List<Organ> organs=donor.getOrgans();
	
		
		
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}
}

