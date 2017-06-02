package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import transplants.db.pojos.Donor;

public class JPAdonor {
	
	//ATTRIBUTES
	private JPAmanager jpaManager;
	
	
	//METHODS
	public JPAdonor(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}

	//This is used in order to insert a donor
	public boolean insert(Donor donor) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().persist(donor);
			jpaManager.getEManager().getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	//Used in order search a donor by its name
	public List <Donor> searchDonor (String name){
		List<Donor> donorList = new ArrayList<Donor>();
		try{			
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM Donors WHERE "
					+ " name LIKE '%" + name + "%'", Donor.class);
			donorList = (List<Donor>) q1.getResultList();	
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return donorList;
	}
	
	//Used to remove the donor that we are passing 
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
