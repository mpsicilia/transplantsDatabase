package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import transplants.db.pojos.Donor;

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
	
	public Integer getIdOfDonor(Donor don){
		Donor donor= new Donor();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT id FROM Donors "
					+ "WHERE (name LIKE '" + don.getName() + "') "
					+ "AND (weight = " + don.getWeight() + ")"
					+ "AND (height = " + don.getHeight() + ") "
					+ "AND (gender LIKE '" + don.getGender() + "')"
					+ "AND (deadAlive LIKE '" + don.getDeadOrAlive() + "') "
					+ "AND (bloodType LIKE '" + don.getBloodType() + "')", Donor.class);
			donor = (Donor) q1.getSingleResult();
			jpaManager.getEManager().getTransaction().commit();		
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return donor.getId();
	}
	
	public boolean insertDonorFK (int idDonor, int idOrg){
		return false;
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
	
	public boolean updateDonor (Donor oldDonor, Donor newDonor){
		try{
			jpaManager.getEManager().getTransaction().begin();
			oldDonor.setName(newDonor.getName());
			oldDonor.setBirthDate(newDonor.getBirthDate());	
			oldDonor.setWeight(newDonor.getWeight());
			oldDonor.setHeight(newDonor.getHeight());
			oldDonor.setGender(newDonor.getGender());
			oldDonor.setDeadOrAlive(newDonor.getDeadOrAlive());
			oldDonor.setBloodType(newDonor.getBloodType());
			jpaManager.getEManager().getTransaction().commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
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
