package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Requested_organ;

public class JPArequest {

private JPAmanager jpaManager;
	
	public JPArequest(JPAmanager jpamanager){
		this.jpaManager=jpamanager;
		
	}

	public boolean insert(Requested_organ requested) {
		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(requested);			
			jpaManager.getEManager().getTransaction().commit();
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Requested_organ> searchOrgan (String name){
		List<Requested_organ> requests = new ArrayList<Requested_organ>();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Requested_organs WHERE name LIKE '%" + name + "%'", JPArequest.class);
			requests = (List<Requested_organ>) q.getResultList();
			jpaManager.getEManager().getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		return requests;
	}
	
	public boolean updateRequest (Requested_organ reqOld, Requested_organ reqNew){
		try{
			jpaManager.getEManager().getTransaction().begin();
			reqOld.setName(reqNew.getName());
			reqOld.setMinWeight(reqNew.getMinWeight());
			reqOld.setMaxWeight(reqNew.getMaxWeight());
			reqOld.setAnimalTissues(reqNew.getAnimalTissues());
			jpaManager.getEManager().getTransaction().commit();
			//both sides!!
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(Requested_organ requested){
		try{
			jpaManager.getEManager().getTransaction().begin();
			//Delete the object
			jpaManager.getEManager().remove(requested);			
			jpaManager.getEManager().getTransaction().commit();		
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
