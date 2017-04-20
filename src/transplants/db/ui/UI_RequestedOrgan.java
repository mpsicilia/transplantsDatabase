package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Requested_organ;

public class UI_RequestedOrgan {
	private DBManager dbManager=new DBManager();
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_RequestedOrgan(){
		
	}
	
	public void introduceNewReqOrgan(){
		try{
			System.out.print("Name: ");
			String name = console.readLine();
			
			System.out.print("Maximum Weight: ");
			Float maxWeight = Float.parseFloat(console.readLine());
			
			System.out.print("Minimun Weight: ");
			Float minWeight = Float.parseFloat(console.readLine());
			
			Requested_organ reqOrgan= new Requested_organ(name, maxWeight, minWeight); 
			
			boolean ok=dbManager.insert(reqOrgan);
			//foreign keys, que pueden ser con el animal tissue (extra table) o organ donated (tabla de organ)
			System.out.println("Choose if the requested organ is going to be supplied by an animal tissue or by a human organ. [animal/human]");
			//TO DO --->>metodo que muestre todos los animaltissues y organs disponibles
			//TO DO --->>cuando elija, ver si son compatibles
			String elect = console.readLine();
			boolean organOK = false;
			if(elect.equalsIgnoreCase("human")){
				organOK = dbManager.insertPrimaryKey(obj1, obj2);
			}
			if(elect.equalsIgnoreCase("animal")){
				organOK = dbManager.insertPrimaryKey(obj1, obj2);
			}
			if (ok){
				System.out.print(" The request Organ has been introduced");
			}else{
				System.out.print("the request Organ has NOT been introduced");
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public List<Requested_organ> searchReqOrgan(){
		try{
			System.out.println("Introduce the name of the requested organ: ");
	 		String name = console.readLine();	 	
			List<Requested_organ> reqOrgan = dbManager.searchRequest(name);
	 		return reqOrgan;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; 
	}
	
	public void updateReqOrgan(Requested_organ reqOrgan){
		boolean again = true;	
		try{
			while(again){
				System.out.println("Choose the information that is going to be updated [1-3]: ");
				System.out.println("1. Name");
				System.out.println("2. Maximum Weight");
				System.out.println("3. Minimum Weight");				
				int op = Integer.parseInt(console.readLine());
				switch (op){
					case 1:
						System.out.println("Introduce the new name: ");
						reqOrgan.setName(console.readLine());
						break;
					case 2:
						System.out.println("Introduce the new maximum weight: ");
						reqOrgan.setMaxWeight(Float.parseFloat(console.readLine()));
						break;
					case 3:
						System.out.println("Introduce the new minimum weight: ");
						reqOrgan.setMinWeight(Float.parseFloat(console.readLine()));
						break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if((console.readLine()).equals("no")){
					again =  false;
				}
			}
			
			boolean updated = dbManager.update(reqOrgan);
			if(updated){
				System.out.println("Request Organ has been updated. \n"
						+ reqOrgan.toString());//see the toString
			}
			else{
				System.out.println(" Request Organ has NOT been updated. ");
			}
			
			}catch (IOException ex){
				ex.printStackTrace();
			}
	}
	
	public void deleteRequestOrgan (Requested_organ reqOrgan){
		try{
			boolean deleted = dbManager.delete(reqOrgan);
			if(deleted){
				System.out.println("Requested Organ has been deleted.");
			}
			else{
				System.out.println("Requested Organ has NOT been deleted. ");
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	
	}
}
