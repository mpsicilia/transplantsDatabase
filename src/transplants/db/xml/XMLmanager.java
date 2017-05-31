package transplants.db.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import transplants.db.pojos.TransplantDatabase;

public class XMLmanager {

	private JAXBContext jaxb;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private File xmlFile = new File ("./xmlFiles/TransplantsDatabase.xml");
	
	private XMLDatabase XMLhosp = new XMLDatabase (this);
	
	public XMLmanager (){
		try{
			jaxb = JAXBContext.newInstance(TransplantDatabase.class);
			marshaller = jaxb.createMarshaller();
			unmarshaller = jaxb.createUnmarshaller();
			
		}catch(JAXBException ex){
			ex.printStackTrace();
		}
		
	}
	//M: used
	//marshalling method!
	public boolean marshalDatabase (TransplantDatabase dataHosp){
		try{
			return XMLhosp.javaToXmlDatabase(marshaller, dataHosp, xmlFile);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	//M: used
	//unmarshaling method
	public TransplantDatabase unmarshalDatabase (TransplantDatabase database){
		try{
			database = XMLhosp.xmlToJavaDatabase(unmarshaller, xmlFile, database);
		}catch (Exception e){
			e.printStackTrace();
		}
		return database;		
	}
	
	//method in order to transform an xml into a html
	public void simpleTransform(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
