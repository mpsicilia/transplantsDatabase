package transplants.db.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import transplants.db.pojos.Hospital;
import transplants.db.pojos.TransplantDatabase;

public class XMLHospital {
	
	private XMLmanager xml;
	
	public XMLHospital (XMLmanager xmlmanager){
		this.xml = xmlmanager;
		
	}
	
	public boolean javaToXmlHospital (Marshaller marsh, TransplantDatabase dataH, File fileXML){
		try{
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			
			marsh.marshal(dataH, fileXML);
			
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public Hospital xmlToJavaHospital (Unmarshaller unmarsh, File fileXML){
		Hospital hosp = new Hospital();
		try{
			hosp = (Hospital) unmarsh.unmarshal(fileXML);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return hosp;
	}

}