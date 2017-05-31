package transplants.db.jpa;

import transplants.db.pojos.Organ;


public class JPAorgan {

	private JPAmanager jpaManager;

	public JPAorgan(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}
	//M: used from jpamanager.insert
	public boolean insert(Organ organ) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().persist(organ);
			jpaManager.getEManager().getTransaction().commit();			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//M: used from jpamanager.delete
	public boolean delete(Organ organ) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().remove(organ);
			jpaManager.getEManager().getTransaction().commit();			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

