package transplants.db.xml;

import java.io.File;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import transplants.db.pojos.TransplantDatabase;

public class XMLHospital {

	private XMLmanager xml;

	public XMLHospital(XMLmanager xmlmanager) {
		this.xml = xmlmanager;

	}

	public boolean javaToXmlDatabase(Marshaller marsh, TransplantDatabase dataH, File fileXML) {
		try {
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marsh.marshal(dataH, fileXML);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public TransplantDatabase xmlToJavaDatabase(Unmarshaller unmarsh, File fileXML, TransplantDatabase dataToUnmarsh) {
		try {
			dataToUnmarsh = (TransplantDatabase) unmarsh.unmarshal(fileXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataToUnmarsh;
	}

}