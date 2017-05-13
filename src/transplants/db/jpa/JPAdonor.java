package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Person;

public class JPAdonor {
	private JPAmanager jpaManager;

	public JPAdonor(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

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
	
	public Organ selectOrgan (Integer id){
		Organ newOrgan=new Organ ();
		try{			
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Organs WHERE id = " + id + "", Organ.class);
			newOrgan = (Organ) q.getSingleResult();			
		}catch (Exception e){
			e.printStackTrace();
		}
		return newOrgan;
	}
	
	
	public Integer getIdOfDonor(Donor don){
		Donor donor= new Donor();
		try{
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT id FROM Donors "
					+ "WHERE name LIKE ? AND weight LIKE ? AND height LIKE ? "
					+ "AND gender LIKE ? AND deadAlive LIKE ? AND bloodType LIKE ? ", Donor.class);
			q1.setParameter(1, don.getName());
			q1.setParameter(2, don.getWeight());
			q1.setParameter(3, don.getHeight());
			q1.setParameter(4, don.getGender());
			q1.setParameter(5, don.getDeadOrAlive());
			q1.setParameter(6, don.getBloodType());
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return donor.getId();
	}

	public List <Donor> searchDonor (String name){
		List<Donor> donorList = new ArrayList<Donor>();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM Donors WHERE "
					+ " name LIKE '%" + name + "%'", Donor.class);
			donorList = (List<Donor>) q1.getResultList();
			jpaManager.getEManager().getTransaction().commit();		
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return donorList;
	}
	
	public boolean removeDonor(Donor donor){		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().remove(donor);			
			jpaManager.getEManager().getTransaction().commit();		
			
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
