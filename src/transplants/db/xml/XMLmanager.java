package transplants.db.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import transplants.db.pojos.Hospital;

public class XMLmanager {

	private JAXBContext jaxb;
	private Marshaller marshaller;
	
	private XMLHospital XMLhosp;
	
	public XMLmanager () throws JAXBException{
		jaxb = JAXBContext.newInstance(Hospital.class);
		marshaller = jaxb.createMarshaller();
	}
	
	public boolean javaToXml (Object obj){
		try{
			if (Hospital.class == obj.getClass()) {
				// create file
				XMLhosp = new XMLHospital(this); 				
				Hospital hospital = (Hospital) obj;
				return XMLhosp.javaToXmlHospital(marshaller, hospital);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
