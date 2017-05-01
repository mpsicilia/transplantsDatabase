package transplants.db.jpa;



import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.pojos.Hospital;

public class JPAhospital {
	public boolean create(Hospital hospital) {
		// Get the entity manager
		try {
			//CAMBIAR COMPANY PROVIDER
			EntityManager em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			//SE SUPONE QUE AQUI EL USUARIO AÑADIRIA INFORMACION DEL HOSPITAL.
			//NO SE SI SIRVE HACERLO ASI.
			em.getTransaction().begin();
			// Store the object
			em.persist(hospital);
			// End transaction
			em.getTransaction().commit();
			
			// Close the entity manager
			em.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}