package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import transplants.db.pojos.Organ;


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
	
	public Organ selectOrgan (Integer id){
		Organ newOrgan=new Organ ();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Organs WHERE id = " + id + "", JPAorgan.class);
			newOrgan = (Organ) q.getSingleResult();
			jpaManager.getEManager().getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		return newOrgan;
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
