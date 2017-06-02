package transplants.db.jpa;

import transplants.db.pojos.Organ;

public class JPAorgan {

	// ATTRIBUTES
	private JPAmanager jpaManager;

	// METHODS
	public JPAorgan(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}

	// This is used in order to insert an organ
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

	// This is used in order to delete an organ
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
