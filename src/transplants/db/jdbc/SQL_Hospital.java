package transplants.db.jdbc;

import java.io.*;
import java.sql.*;
import java.util.*;

import transplants.db.pojos.Hospital;

public class SQL_Hospital {
	Connection c;
	List <Hospital> hospital= new ArrayList<Hospital>();
	
	private SQL_Hospital(){
		this.Connect();
	}
	
	private void Connect(){
		try{
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");
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
			String sql = "INSERT INTO Hospitals (name, phone_number, address, city"
					+ "postcode, country) VALUES ('" + hospital.getName() + "', '" + hospital.getPhone_number() + "',"
					+ " '" + hospital.getAddress() + "', '" + hospital.getCity() + "', '" + hospital.getPostcode() + "', '" + hospital.getCountry() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Hospital> Search_Hospital(String name){
		try{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM HOSPITAL";
			ResultSet rs = stmt.executeQuery(sql);
		

			rs.close();
		    stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		
		}
		//return //buscar todo entre los hospitales y devolver dentro del try y despues
				//pasarle el string con loque quiere buscar
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
