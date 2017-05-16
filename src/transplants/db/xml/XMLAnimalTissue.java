package transplants.db.xml;

import java.io.File;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Hospital;

public class XMLAnimalTissue {

	
	private File fileHosp;
	private XMLmanager xml;
	
	public XMLAnimalTissue (XMLmanager xmlmanager){
		fileHosp = new File ("./xmlFiles/Hospital.xml");
		this.xml = xmlmanager;
		
	}
	
	public boolean javaToXmlAnimal (Marshaller marsh, Animal_tissue animal){
		try{
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			marsh.marshal(animal, fileHosp);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public Animal_tissue xmlToJavaHospital (Unmarshaller unmarsh){
		Animal_tissue at = new Animal_tissue();
		try{
			at = (Animal_tissue) unmarsh.unmarshal(fileHosp);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return at;
	}
}