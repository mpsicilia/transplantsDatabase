package transplants.db.jpa;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.pojos.Hospital;

public class JPAhospital {
	private JPAmanager jpaManager;

		public JPAhospital(JPAmanager jpamanager){
			this.jpaManager=jpamanager;
			jpaManager.connect();
			
		}

	public boolean insert(Hospital hospital) {
		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(hospital);			
			jpaManager.getEManager().getTransaction().commit();		
			jpaManager.getEManager().close();
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
