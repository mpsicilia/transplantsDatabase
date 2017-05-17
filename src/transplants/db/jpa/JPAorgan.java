package transplants.db.jpa;

import transplants.db.pojos.Organ;

// RODRIGO: BEGIN
public class JPAorgan {

	private JPAmanager jpaManager;

	public JPAorgan(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}

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

}
// RODRIGO: END
