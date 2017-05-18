package transplants.db.pojos;

import java.util.ArrayList;
import java.util.List;

public class TransplantDatabase {
	
	private List<Hospital> hospitalsOfDatabase;
	
	public TransplantDatabase(){
		hospitalsOfDatabase = new ArrayList<Hospital>();
	}
	
	public List<Hospital> getAllHospOFDatabase(){
		return hospitalsOfDatabase;
	}
	
	//Addition of Hospitals
	public boolean addHospital (Hospital hosp){
		if(!hospitalsOfDatabase.contains(hosp)){
			return this.hospitalsOfDatabase.add(hosp);
		}
		else return false;
	}
	
	//Remove hospital
	public boolean removeHospital (Hospital hosp){
		if(!hospitalsOfDatabase.contains(hosp)){
			return this.hospitalsOfDatabase.remove(hosp);
		}
		else return false;
	}			

}
