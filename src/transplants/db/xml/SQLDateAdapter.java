package transplants.db.xml;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
//XML annotations in patient and person
//we use this class to get the dates from the XMLFile and to put the dates in the XMLFile
public class SQLDateAdapter extends XmlAdapter<String, Date> {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public String marshal(Date sqlDate) throws Exception { //receives a date and returns a string
		return sqlDate.toLocalDate().format(formatter);
	}

	@Override
	public Date unmarshal(String string) throws Exception { //receives a string and returns a date
		LocalDate localDate = LocalDate.parse(string, formatter);
		return Date.valueOf(localDate);
	}

}