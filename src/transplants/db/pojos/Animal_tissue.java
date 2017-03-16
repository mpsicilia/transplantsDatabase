package transplants.db.pojos;

import java.io.Serializable;

import sample.db.pojos.Report;

public class Animal_tissue implements Serializable {
	
	private static final long serialVersionUID = 2350829857786761680L;
	
	private int id;
	private String name;
	private String type_of_tissue;
	private String pathology;
	private int time;
	
	private List<Requested_organ> requestedOrgans;
	
	
	
	public Animal_tissue() {
		super();
		this.requestedOrgans = new ArrayList <Requested_organ>();
	}
	
		
	public Animal_tissue(int id, String name, String type_of_tissue, String pathology, int time,
			List<Requested_organ> requestedOrgans) {
		super();
		this.id = id;
		this.name = name;
		this.type_of_tissue = type_of_tissue;
		this.pathology = pathology;
		this.time = time;
		this.requestedOrgans = requestedOrgans;
	}


	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal_tissue other = (Animal_tissue) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Animal_tissue [id=" + id + ", name=" + name + ", type_of_tissue=" + type_of_tissue + ", pathology="
				+ pathology + ", time=" + time + "]";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType_of_tissue() {
		return type_of_tissue;
	}
	public void setType_of_tissue(String type_of_tissue) {
		this.type_of_tissue = type_of_tissue;
	}
	public String getPathology() {
		return pathology;
	}
	public void setPathology(String pathology) {
		this.pathology = pathology;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	
	//Add to the list
	public void addRequestedOrgan(Requested_organ organ) {
		if (!requestedOrgans.contains(organ)) {
			this.requestedOrgans.add(organ);
		}
	}

	//Remove from the list
	public void removeRequestedOrgan(Requested_organ organ) {
		if (requestedOrgans.contains(organ)) {
			this.requestedOrgans.remove(organ);
		}
	}
}
