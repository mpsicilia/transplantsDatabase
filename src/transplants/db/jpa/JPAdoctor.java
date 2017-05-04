package transplants.db.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import sample.db.pojos.Employee;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;


public class JPAdoctor {
	
	private JPAmanager jpaManager;
	

//HAGO ESTE CONSTRUCTOR en todos los JPA individuales PORQUE ES LA MANERA QUE HE ENCONTRADO DE TRABAJAR CON EL MISMO EM.
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
			
			Query q1=jpaManager.getEManager().createNativeQuery("SELECT * FROM doctors",Doctor.class);
			Query q2=jpaManager.getEManager().createNativeQuery("SELECT * FROM hospitals",Hospital.class);
			
			List<Doctor> doctors =(List<Doctor>) q1.getResultList();
    		Iterator <Doctor> it = doctors.iterator();
    		while(it.hasNext()){
    			
    				
    	 			Doctor doct = it.next();
    	 	        doct.addHospital(hospi);
    	 			
    	 		
    		}
    		
    		/*// Get 4 employees from the database
		Query q3 = em.createNativeQuery("SELECT * FROM employees", Employee.class);
		List<Employee> employeeList2 = (List<Employee>) q3.getResultList();
		Employee emp1 = employeeList2.get(0);
		Employee emp2 = employeeList2.get(1);
		Employee emp3 = employeeList2.get(2);
		Employee emp4 = employeeList2.get(3);
		// Set authors
		rep1.addAuthor(emp1);
		rep1.addAuthor(emp2);
		rep2.addAuthor(emp3);
		rep2.addAuthor(emp4);
		emp1.addReport(rep1);
		emp2.addReport(rep2);
		emp3.addReport(rep1);
		emp4.addReport(rep2);
    	*/
    		
			
	 	
			
			return true;
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
