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
	public boolean marshalDatabase (TransplantDatabase dataHosp){
		try{
			return XMLhosp.javaToXmlDatabase(marshaller, dataHosp, xmlFile);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	//unmarshaling method
	public TransplantDatabase unmarshalDatabase (TransplantDatabase database){
		try{
			database = XMLhosp.xmlToJavaDatabase(unmarshaller, xmlFile, database);
		}catch (Exception e){
			e.printStackTrace();
		}
		return database;		
	}
	
	
}
