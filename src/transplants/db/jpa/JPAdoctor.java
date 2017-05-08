package transplants.db.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;


import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;


public class JPAdoctor {
	
	private JPAmanager jpaManager;
	


	public JPAdoctor(JPAmanager jpamanager){
		this.jpaManager=jpamanager;
		jpaManager.connect();
		
	}
	public boolean insert(Doctor doctor) {
		
		try {
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(doctor);			
			jpaManager.getEManager().getTransaction().commit();		
			jpaManager.getEManager().close();
			
			
			
			Query q2=jpaManager.getEManager().createNativeQuery("SELECT * FROM hospitals",Hospital.class);
			
			
			
			List<Hospital> hospitalofdoctor =doctor.getHospital();
			
    		
			Iterator <Hospital> it1 = hospitalofdoctor.iterator();
    		
    		
    	
    		while(it1.hasNext()){
    			Hospital hosp=it1.next();
    			hosp.addDoctor(doctor);
    		}
    			
    				
    
    		
    	
			
	 	
			
			return true;
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
