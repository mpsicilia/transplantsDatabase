package transplants.db.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.TransplantDatabase;

public class XMLmanager {

	private JAXBContext jaxb;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private File xmlFile = new File ("./xmlFiles/TransplantsDatabase.xml");
	
	private XMLHospital XMLhosp = new XMLHospital (this);
	
	public XMLmanager (){
		try{
			jaxb = JAXBContext.newInstance(TransplantDatabase.class);
			marshaller = jaxb.createMarshaller();
			unmarshaller = jaxb.createUnmarshaller();
			
		}catch(JAXBException ex){
			ex.printStackTrace();
		}
		
	}
	//marshalling method!
	public boolean javaToXmlHospitals (TransplantDatabase dataHosp){
		try{
			return XMLhosp.javaToXmlHospital(marshaller, dataHosp, xmlFile);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	//unmarshaling methods
	public Hospital unmarshallHospital (){
		Hospital hospital = new Hospital();
		try{
			hospital = XMLhosp.xmlToJavaHospital(unmarshaller, xmlFile);
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
