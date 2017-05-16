package transplants.db.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Hospital;

public class XMLmanager {

	private JAXBContext jaxb;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	private XMLHospital XMLhosp = new XMLHospital (this);
	
	public XMLmanager () throws JAXBException{
		jaxb = JAXBContext.newInstance(Hospital.class);
		marshaller = jaxb.createMarshaller();
		unmarshaller = jaxb.createUnmarshaller();
	}
	//marshalling method
	public boolean javaToXml (Object obj){
		try{
			if (Hospital.class == obj.getClass()) {
				// create file				
				Hospital hospital = (Hospital) obj;
				return XMLhosp.javaToXmlHospital(marshaller, hospital);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	//unmarshaling methods
	public Hospital unmarshallHospital (){
		Hospital hospital = new Hospital();
		try{
			hospital = XMLhosp.xmlToJavaHospital(unmarshaller);
		}catch (Exception e){
			e.printStackTrace();
		}
		return hospital;
	}
	
	public Animal_tissue unmarshallAnimalTissue (){
		Animal_tissue at = new Animal_tissue();
		try{
			//extra
		}catch (Exception e){
			e.printStackTrace();
		}
		return at;
	}
	
}
