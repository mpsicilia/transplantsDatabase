package transplants.db.jpa;



import javax.persistence.EntityManager;
import javax.persistence.Persistence;


import transplants.db.pojos.Patient;

public class JPApatient {
	private EntityManager em;

		public JPApatient(JPAmanager jpamanager){
			em=jpamanager.getEManager();
			
		}
	public boolean insert(Patient patient) {
		

		try {
			// COMPANY PROVIDER cambiado!!!!
			//EntityManager em = Persistence.createEntityManagerFactory("transplants-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();

			em.getTransaction().begin();
			// Store the object
			em.persist(patient);

			em.getTransaction().commit();

			em.close();
	        //List<Doctor> doctors=patient.getDoctors();
	        //LA RELACION N-N DOCTOR PACIENTE LA DB LA HACE SOLA?
			//NO SE SI HABRIA QUE INSERTAR EL PACIENTE EN LA LISTA DE PACIENTES QUE TENGAN SUS DOCTORES
			//POR EJEMPLO: maria tiene dos doctores: pedro y juan. habria que añadir a la lista de pacientes de pedro y juan, maria?
			
	       
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
