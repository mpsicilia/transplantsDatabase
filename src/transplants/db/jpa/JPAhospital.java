package transplants.db.jpa;



import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.pojos.Hospital;

public class JPAhospital {
	private EntityManager em;
	

		public JPAhospital(JPAmanager jpamanager){
			em=jpamanager.getEManager();
			
		}

	public boolean insert(Hospital hospital) {
		
		try {
			//si quitas el import de EM te da este error, osea que algo mal tenemos en persistence.xml
			//pero si pones el import te dice que nunca se usa, que coño?
			//EntityManager em = Persistence.createEntityManagerFactory("transplants-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			//EN JPADEMO EL USUARIO AÑADE INFORMACION AQUI.
			//EN NUESTRO CASO, LA INFORMACION SE HA AÑADIDO EN UIHOSPITAL
			
			em.getTransaction().begin();
			// Store the object
			em.persist(hospital);
			
			em.getTransaction().commit();
			
			
			em.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
