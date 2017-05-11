package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import transplants.db.pojos.Organ;
import transplants.db.pojos.Requested_organ;

public class JPAorgan {

	private JPAmanager jpaManager;
	
	public JPAorgan(JPAmanager jpamanager){
		this.jpaManager=jpamanager;
		
	}

	public boolean insert(Organ organ) {
		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(organ);			
			jpaManager.getEManager().getTransaction().commit();		
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Organ> searchOrgan (String name){
		List<Organ> organs = new ArrayList<Organ>();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Organs WHERE name LIKE '%" + name + "%'", JPAorgan.class);
			organs = (List<Organ>) q.getResultList();
			jpaManager.getEManager().getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		return organs;
	}
	
	public boolean updateOrgan (Organ orgOld, Organ orgNew){
		try{
			jpaManager.getEManager().getTransaction().begin();
			orgOld.setName(orgNew.getName());
			orgOld.setWeight(orgNew.getWeight());
			orgOld.setTypeOfDonation(orgNew.getTypeOfDonation());
			orgOld.setLifeOfOrgan(orgNew.getLifeOfOrgan());
			
			jpaManager.getEManager().getTransaction().commit();
			//both directions
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(Organ organ){
		try{
			jpaManager.getEManager().getTransaction().begin();
			//Delete the object
			jpaManager.getEManager().remove(organ);			
			jpaManager.getEManager().getTransaction().commit();		
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
