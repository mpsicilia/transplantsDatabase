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
			return XMLhosp.javaToXmlHospital(marshaller, dataHosp, xmlFile);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	//unmarshalling method
	public TransplantDatabase unmarshalDatabase (TransplantDatabase data){
		try{
			data = XMLhosp.xmlToJavaHospital(unmarshaller, xmlFile, data);
		}catch (Exception e){
			e.printStackTrace();
		}
		return data;
	}
}