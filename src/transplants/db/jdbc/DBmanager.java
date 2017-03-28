package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public interface DBmanager {
	
	public void connect();

	public void disconnect();
	
	public void insert(Object obj);
	
	public List<Object> search(Object obj); 
	
}
