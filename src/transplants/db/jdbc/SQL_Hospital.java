package transplants.db.jdbc;

import java.io.*;
import java.sql.*;
import java.util.*;

import transplants.db.pojos.Hospital;

public class SQL_Hospital {
	Connection c;
	List <Hospital> hospital= new ArrayList<Hospital>();
	
	public SQL_Hospital(){
		this.Connect();
	}
	
	private void Connect(){
		try{
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//CAMBIARRRR, cambiado
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
       }catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void Disconnect(){
		try{
			c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void Insert_Hospital(Hospital hospital){
		try{
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO Hospitals (name, phone_number, address, city, "
					+ "postcode, country) VALUES ('" + hospital.getName() + "', '" + hospital.getPhone_number() + "',"
					+ " '" + hospital.getAddress() + "', '" + hospital.getCity() + "', '" + hospital.getPostcode() + "', '" + hospital.getCountry() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Hospital> Search_Hospital(String name){
		List <Hospital> lookForHospital=new ArrayList<Hospital>();
		try{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM Hospitals WHERE name LIKE '%"+ name+ "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id= rs.getInt("id");
				String name1=rs.getString("name");
				if (name.equals(name1)){					
					String phone_number1= rs.getString("phone_number");
					String address1= rs.getString("address");
					String city1= rs.getString("city");
					String postcode1= rs.getString("postcode");
					String country1= rs.getString("country");
					Hospital hospitalToShow=new Hospital(id,name1, phone_number1,address1, city1, postcode1, country1);
				    lookForHospital.add(hospitalToShow);					
				}				
			}
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return lookForHospital;

	}
		
	public void Create_Table(){
		try{
		Statement stmt1 = c.createStatement();
		String hospitals = "CREATE TABLE Hospitals "
				   + "(id      		 	INTEGER  PRIMARY KEY AUTOINCREMENT,"
				   + " name     		TEXT     NOT NULL, "
				   + " phone_number		TEXT,  "
				   + " address  		TEXT	 NOT NULL, "
				   + " city 			TEXT,"
				   + " postcode			TEXT,"
				   + " country			TEXT)";
		stmt1.executeUpdate(hospitals);
		stmt1.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
					
						
	
}
