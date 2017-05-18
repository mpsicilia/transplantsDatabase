package transplants.db.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import transplants.db.pojos.Hospital;

public class XMLHospital {
	
	private File fileHosp;
	private XMLmanager xml;
	
	public XMLHospital (XMLmanager xmlmanager){
		fileHosp = new File ("./xmlFiles/Hospitals.xml");
		this.xml = xmlmanager;
		
	}
	
	public boolean javaToXmlHospital (Marshaller marsh, List<Hospital> hs){
		try{
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			Iterator<Hospital> it = hs.iterator();
			while (it.hasNext()){
				Hospital h = it.next();
				marsh.marshal(h, fileHosp);
			}
			
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public Hospital xmlToJavaHospital (Unmarshaller unmarsh){
		Hospital hosp = new Hospital();
		try{
			hosp = (Hospital) unmarsh.unmarshal(fileHosp);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return hosp;
	}

}