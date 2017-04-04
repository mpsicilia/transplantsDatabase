package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public interface DBManagerInterface {
	
	public void connect();

	public void disconnect();
	
	public boolean insert(Object obj);
	
	public List<Object> search(String name); 
	
	public boolean update (Object obj);
	
	public boolean delete (Object obj);
	
}
