package transplants.db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;


public class JPAdoctor {
	
	private EntityManager em;
	

//HAGO ESTE CONSTRUCTOR en todos los JPA individuales PORQUE ES LA MANERA QUE HE ENCONTRADO DE TRABAJAR CON EL MISMO EM.
	public JPAdoctor(JPAmanager jpamanager){
		em=jpamanager.getEManager();
		
	}
	public boolean insert(Doctor doctor) {
		
		try {
			//EntityManager em = Persistence.createEntityManagerFactory("transplants-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			em.getTransaction().begin();
			
			em.persist(doctor);
			
			em.getTransaction().commit();
			
			// Close the entity manager
			em.close();
			//CUANDO INSERTO EL DOCTOR??
			Hospital hosp=doctor.getHospital();
			hosp.addDoctor(doctor);
			
			return true;
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
