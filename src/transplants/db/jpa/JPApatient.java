package transplants.db.jpa;



import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import transplants.db.pojos.Patient;

public class JPApatient {

	private JPAmanager jpaManager;

	public JPApatient(JPAmanager jpamanager){
		this.jpaManager=jpamanager;
		
	}
public boolean insert(Patient patient) {
		
		try {			
			jpaManager.getEManager().getTransaction().begin();
			// Store the object
			jpaManager.getEManager().persist(patient);			
			jpaManager.getEManager().getTransaction().commit();		
			
			return true;
			 //LA RELACION N-N DOCTOR PACIENTE LA DB LA HACE SOLA?
			//NO SE SI HABRIA QUE INSERTAR EL PACIENTE EN LA LISTA DE PACIENTES QUE TENGAN SUS DOCTORES
			//POR EJEMPLO: maria tiene dos doctores: pedro y juan. habria que añadir a la lista de pacientes de pedro y juan, maria?
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
public boolean delete(Patient patient) {
	
	try {			
		jpaManager.getEManager().getTransaction().begin();
		// Store the object
		jpaManager.getEManager().remove(patient);			
		jpaManager.getEManager().getTransaction().commit();		
		return true;
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}

}
