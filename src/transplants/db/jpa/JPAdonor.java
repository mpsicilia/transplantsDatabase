package transplants.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.pojos.Donor;

public class JPAdonor {
	private JPAmanager jpaManager;

	public JPAdonor(JPAmanager jpamanager){
		this.jpaManager=jpamanager;
		jpaManager.connect();
		
	}
public boolean insert(Donor donor) {
		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(donor);			
			jpaManager.getEManager().getTransaction().commit();		
			return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
public boolean delete(Donor donor) {
	
	try {			
		jpaManager.getEManager().getTransaction().begin();
		// Store the object
		jpaManager.getEManager().remove(donor);			
		jpaManager.getEManager().getTransaction().commit();		
		return true;
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}
}

