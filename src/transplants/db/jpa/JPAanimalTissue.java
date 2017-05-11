package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Requested_organ;

public class JPAanimalTissue {

private JPAmanager jpaManager;
	
	public JPAanimalTissue(JPAmanager jpamanager){
		this.jpaManager=jpamanager;
		jpaManager.connect();
		
	}

	public boolean insert(Animal_tissue animalT) {
		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(animalT);			
			jpaManager.getEManager().getTransaction().commit();		
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Animal_tissue> searchOrgan (String name){
		List<Animal_tissue> animalTis = new ArrayList<Animal_tissue>();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Animal_tissues WHERE name LIKE '%" + name + "%'", JPAanimalTissue.class);
			animalTis = (List<Animal_tissue>) q.getResultList();
			jpaManager.getEManager().getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		return animalTis;
	}
	
	public boolean updateAnimalTissue (Animal_tissue atOld, Animal_tissue atNew){
		try{
			jpaManager.getEManager().getTransaction().begin();
			atOld.setName(atNew.getName());
			atOld.setTypeOfTissue(atNew.getTypeOfTissue());
			atOld.setPathology(atNew.getPathology());
			atOld.setLifeExpTissue(atNew.getLifeExpTissue());
			
			atOld.setRequested_organs(atNew.getRequested_organs());
			jpaManager.getEManager().getTransaction().commit();
			//change in both directions!!!
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(Animal_tissue animalT){
		try{
			jpaManager.getEManager().getTransaction().begin();
			//Delete the object
			jpaManager.getEManager().remove(animalT);			
			jpaManager.getEManager().getTransaction().commit();	
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
