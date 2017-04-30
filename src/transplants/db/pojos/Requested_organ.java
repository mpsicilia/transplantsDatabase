package transplants.db.pojos;

import java.io.Serializable;

public class Requested_organ implements Serializable {

	private static final long serialVersionUID = 4061202503200538758L;
	private Integer id;
	private String name;
	private Float maxWeight;
	private Float minWeight;
	
	public Requested_organ(){
		
	}
	
	public Requested_organ(Integer id, String name, Float maxWeight, Float minWeight) {
		//super();
		this.id = id;
		this.name = name;
		this.maxWeight = maxWeight;
		this.minWeight = minWeight;
	}
	public Requested_organ(String name, Float maxWeight, Float minWeight) {
		//super();
		this.name = name;
		this.maxWeight = maxWeight;
		this.minWeight = minWeight;
	}
		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Requested_organ other = (Requested_organ) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(Float maxWeight) {
		this.maxWeight = maxWeight;
	}
	public Float getMinWeight() {
		return minWeight;
	}
	public void setMinWeight(Float minWeight) {
		this.minWeight = minWeight;
	}

	@Override
	public String toString() {
		return "Requested_organ [id=" + id + ", name=" + name + ", maxWeight=" + maxWeight + ", minWeight=" + minWeight
				+ "]\n";
	}
	
	
//need to see in what pojos we are going to put the toString
}
