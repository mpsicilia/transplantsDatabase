package transplants.db.xml;

import java.io.File;

import javax.xml.bind.Marshaller;

import transplants.db.pojos.Hospital;

public class XMLHospital {
	
	private File fileHosp;
	private XMLmanager xml;
	
	public XMLHospital (XMLmanager xmlmanager){
		fileHosp = new File ("./xmlFiles/Hospitals.xml");
		this.xml = xmlmanager;
		
	}
	
	public boolean javaToXmlHospital (Marshaller marsh, Hospital hosp){
		try{
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			marsh.marshal(hosp, fileHosp);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}