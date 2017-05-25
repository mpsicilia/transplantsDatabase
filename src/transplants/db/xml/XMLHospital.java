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
	
	public TransplantDatabase xmlToJavaHospital (Unmarshaller unmarsh, File fileXML, TransplantDatabase dataToUnmarsh){
		try{
			dataToUnmarsh = (TransplantDatabase) unmarsh.unmarshal(fileXML);
		}catch (Exception e){
			e.printStackTrace();
		}
		return dataToUnmarsh;
	}

}