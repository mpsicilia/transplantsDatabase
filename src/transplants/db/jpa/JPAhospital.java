package transplants.db.jpa;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import transplants.db.pojos.Hospital;

public class JPAhospital {
	private JPAmanager jpaManager;

		public JPAhospital(JPAmanager jpamanager){
			this.jpaManager=jpamanager;
			jpaManager.connect();
			
		}

	public boolean insert(Hospital hospital) {
		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(hospital);			
			jpaManager.getEManager().getTransaction().commit();		
			//jpaManager.getEManager().close();
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateHospital (Hospital hospital){
		///should I pass the hospital or each methots
		jpaManager.getEManager().getTransaction().begin();
		hospital.setAddress(hospital.getAddress());
		return false;
	}
	public List<Hospital> shearchHospital (String name){
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		try{
			jpaManager.getEManager().getTransaction().begin();
			Query q1 = jpaManager.getEManager().createNativeQuery("SELECT * FROM hospitals WHERE "
					+ " name LIKE '%" + name + "%'", JPAhospital.class);
			hospitalList = (List<Hospital>) q1.getResultList();
			jpaManager.getEManager().getTransaction().commit();		
			jpaManager.getEManager().close();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return hospitalList;
	}
	public boolean removeHospital(Hospital hosp){		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().remove(hosp);			
			jpaManager.getEManager().getTransaction().commit();		
			jpaManager.getEManager().close();
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
