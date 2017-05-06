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
		
	}
	public boolean insert(Doctor doctor) {
		
		try {
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(doctor);			
			jpaManager.getEManager().getTransaction().commit();		
			jpaManager.getEManager().close();
			
			
			//habria que guardar en esta lista (que devuelve gethospital) en uidoctor, los hospitales en los que trabaja
			// el doctor.
			//una vez teniendo esos hospitales ya podria hacer dos bucles while.
			
			
			Query q2=jpaManager.getEManager().createNativeQuery("SELECT * FROM hospitals",Hospital.class);
			
			/*getHospital() me tiene que devolver la lista de hospitales donde trabaja el doctor. esta lista se debe inicializar
			en uidoctor cuando arreglemos lo de poder introducir varios hospitales al doctor.
			 * o si no se puede conectar ui con pojo, pues poner esos hospitales en una lista y pasarsela al insert y copiar esa lista en la lista propia 
			 * del pojo de doctor
			 * allhospitals devuelve todos los hospitales de la db (query q2 arriba de este comentario)
			 * lo que hace el primer bucle while es ir pasando los hospitales que haya en la db y cogiendo el id.
			 * el bucle de dentro lo que hace es pasar los hospitales en los que trabaje el doctor. cuando el id de uno de los hospitales
			 * coincida con el id de un hospital en el que trabaja el señor, va a añadir el doctor a la lista de doctores de su hospital
			
			 * y asi recorriendo toodos los hospitales de la db
			*/
			
			List<Hospital> hospitalofdoctor =doctor.getHospital();
			List<Hospital> allhospitals=(List<Hospital>) q2.getResultList();
    		
			Iterator <Hospital> it1 = hospitalofdoctor.iterator();
    		Iterator <Hospital> it2 = allhospitals.iterator();
    		
    		while(it1.hasNext()){
    			Hospital allhosp=it1.next();
    			int id1=allhosp.getId();
    			while(it2.hasNext()){
    				Hospital hospdoctor=it2.next();
    				int id2=hospdoctor.getId();
    				if(id1==id2){
    					allhosp.addDoctor(doctor);
    					doctor.addHospital(hospdoctor);
    					//esta ultima line creo que no haria falta porque la lista de hospitales del doctor ya estaria 
    					//inicializada en Uidoctor como he dicho arriba.
    					
    				}
    			}
    		}
    			
    				
    
    		
    	
			
	 	
			
			return true;
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
