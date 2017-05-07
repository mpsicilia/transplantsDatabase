package transplants.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.pojos.Donor;
import transplants.db.pojos.Organ;

public class JPAdonor {
	private JPAmanager jpaManager;

	public JPAdonor(JPAmanager jpamanager){
		this.jpaManager=jpamanager;
		
	}
	
	public boolean insert(Donor donor){
		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(donor);			
			jpaManager.getEManager().getTransaction().commit();		
			List<Organ> organs=donor.getOrgans();
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
			
		
		
	}
}

