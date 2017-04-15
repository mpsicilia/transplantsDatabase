package transplants.db.pojos;

import java.io.Serializable;

public class Animal_tissue implements Serializable {

	private static final long serialVersionUID = -7167881940806327162L;
	private Integer id;
	private String name;
	private String typeOfTissue;
	private String pathology;
	private Integer timeItLasts;
	
	public Animal_tissue() {
		super();
	}
	
		
	public Animal_tissue(Integer id, String name, String typeOfTissue, String pathology, int time) {
		super();
		this.id = id;
		this.name = name;
		this.typeOfTissue = typeOfTissue;
		this.pathology = pathology;
		this.timeItLasts = time;
	}
	
	public Animal_tissue(String name, String typeOfTissue, String pathology, int time) {
		super();
		this.name = name;
		this.typeOfTissue = typeOfTissue;
		this.pathology = pathology;
		this.timeItLasts = time;
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
		return "Animal_tissue [\nname=" + name + ", \ntype_of_tissue=" + typeOfTissue + ", \npathology="
				+ pathology + ", \ntime=" + timeItLasts + "]";
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
	public String getTypeOfTissue() {
		return typeOfTissue;
	}
	public void setTypeOfTissue(String typeOfTissue) {
		this.typeOfTissue = typeOfTissue;
	}
	public String getPathology() {
		return pathology;
	}
	public void setPathology(String pathology) {
		this.pathology = pathology;
	}
	public int getTimeItLasts() {
		return timeItLasts;
	}
	public void setTimeItLasts(int timeItLasts) {
		this.timeItLasts = timeItLasts;
	}
	
}
