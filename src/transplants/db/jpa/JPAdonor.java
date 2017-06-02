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

	//M: used from jpamanager: insert
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
	//same thing in jdbc...
	/*public List<Organ> selectOrgan (Integer id){
		List <Organ> newOrgan =new ArrayList < Organ> ();
		try{				
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Organs WHERE id = ? ", Organ.class);
			q.setParameter(1, id);
			newOrgan = (List<Organ>) q.getResultList();	
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return newOrgan;
	}*/
	
	//M: used by jpamanager:searchdonor
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
	//M: used from jpamanager.delete
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
	
	/*public Integer getIdOfDonor(Donor don){
		Donor donor= new Donor();
		try{
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM Donors "
					+ "WHERE name LIKE ? AND weight = ? AND height = ? "
					+ "AND gender = ? AND deadAlive = ? AND bloodType = ? ", Donor.class);
			q1.setParameter(1, don.getName());
			q1.setParameter(2, don.getWeight());
			q1.setParameter(3, don.getHeight());
			q1.setParameter(4, don.getGender());
			q1.setParameter(5, don.getDeadOrAlive());
			q1.setParameter(6, don.getBloodType());
			donor =(Donor)q1.getSingleResult();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return donor.getId();
	}*/
	//not used...M: used now by the show results of compt test
	public Donor getDonorOfOrgan (Integer orgId){
		
			Donor donor = new Donor();
		try{				
			Query q1= jpaManager.getEManager().createNativeQuery("SELECT * FROM Donors AS Don JOIN Organs "
					+ " AS Org ON Don.id = Org.donor_id WHERE Org.id = " + orgId + "", Donor.class);
			donor = (Donor) q1.getSingleResult();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return donor;
	}
}
