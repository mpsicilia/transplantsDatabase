package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;


import transplants.db.jdbc.DBManager;
import transplants.db.jpa.JPAmanager;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class UIGenericMenu {

	private BufferedReader console=new BufferedReader (new InputStreamReader (System.in));
   	private Integer option=0;
	
	public static void main (String []args){		
		UIGenericMenu genericMenu= new UIGenericMenu();
	}	
	
	public UIGenericMenu(){
		DBManager dbManager = new DBManager();
		JPAmanager jpaManager= new JPAmanager();
		UI_Hospitals uiHospital=new UI_Hospitals();
		UI_Doctor uiDoctor=new UI_Doctor();
		UI_Donor uiDonor=new UI_Donor();
		UI_Patient uiPatient=new UI_Patient();
		UI_Organ uiOrgan= new UI_Organ();
		UI_AnimalTissue uiAnimalT= new UI_AnimalTissue();
		UI_RequestedOrgan uiRequested = new UI_RequestedOrgan();
		try{
				        
	       /* System.out.println("Temporary option: DROP ALL THE TABLES? [Y/N]");
	        String drop = console.readLine();
	        if(drop.equalsIgnoreCase("Y")){
	        	boolean dropped = dbManager.dropTables();
	        	if(dropped){
	        		System.out.println("Tables have been dropped. ");
	        	}
	        	else{
	        		System.out.println("Tables have not been dropped. ");
	        	}
	        } */
	        
	        System.out.println("Do you want to create the tables?: [yes/no]");
	        String decider= console.readLine();
	        if (decider.equals("yes")){
	        	boolean created = dbManager.createTables();
		        	if(created){
		        		System.out.println("Tables have been created. ");
		        	}
		        	else{
		        		System.out.println("Tables have not been created. ");
		        	}
	        }else{
	        	System.out.println("Tables should be already created");
	        }
	        while(true){
	        	System.out.println("\nBASIC MENU: ");
	        	System.out.println("1. Introduce new information to the database. ");
	        	System.out.println("2. Search for specific information in the database. ");
	        	System.out.println("3. Save the database information in an XML file. ");
	            System.out.println("4. Exit from the database. ");
	        		        	
	        	 do{
	                    System.out.println("\nChoose an option[1-4]:");
	                    String read= console.readLine();
	                    option= Integer.parseInt(read);            
	                }while (option<1|| option>4);
	        	 switch (option){
	        	 	case 1: {
	        	 		System.out.println("\nMENU: ");
	    	        	System.out.println("1. Introduce a new hospital. ");
	    	        	System.out.println("2. Introduce a new doctor. ");//introduce the hospital where works
	    	        	System.out.println("3. Introduce a new donor."); //introducing also the organ that is donating
	    	        	System.out.println("4. Introduce a new patient. "); //introducing also the requested organ
	    	            System.out.println("5. Introduce a new animal tissue in order to be donated. ");
	    	        	System.out.println("\nChoose an option[1-5]:");
	                    String read1= console.readLine();
	                    int option1= Integer.parseInt(read1); 
	                    switch (option1){
		                    case 1:
		                    	uiHospital.introduceNewHospital(dbManager);
		                    	//En caso de que queramos hacerlas con JPA; adem�s habr�a que a�adir en UIPojo el m�todo
		                    	//al que le pasas JPAmanager en vez de DBmanager
		                    	//uiHospital.introduceNewHospital(jpaManager);
		                    	break;
		                    case 2:
		                    	uiDoctor.introduceNewDoctor(dbManager);
		                    	//uiDoctor.introduceNewDoctor(jpaManager);
		                    	break;
		                    case 3:
		                    	Donor d = uiDonor.introduceNewDonor(jpaManager);
		                    	System.out.println("Introduce the organ that the donor donates. ");
		                    	//Donor in JPA but organ in jdbc

		                    	uiOrgan.introduceNewOrgan(d, dbManager, jpaManager);
		                    	
		                    	break;
		                    case 4:
		                    	Patient p = uiPatient.introduceNewPatient(jpaManager);
		                    	System.out.println("Introduce the organ that the patient needs. ");
		                    	//patient in JPA but requested organ in jdbc
		                    	uiRequested.introduceNewReqOrgan(p,dbManager);
		                    	//foreign keys in requested organ
		                    	break;
		                    case 5:
		                    	uiAnimalT.introduceNewAnimalTissue(dbManager);
		                    	break;
	                    }	                         	 		        			
	        	 		
	        	 	}
	        	 	break;
	        	 	case 2:{	        	 		
	        	 		System.out.print("\n1. Search a specific hospital. ");
	        	 		System.out.print("\n2. Check the information of a specific doctor. ");
	        	 		System.out.print("\n3. Check the information of a specific donor. ");
	        	 		System.out.print("\n4. Check the information of a specific patient. ");
	        	 		System.out.print("\n5. Check the characteristics of an organ. "); 
	        	 		//cuando hagamos esta opcion tenia pensado hacer que el usuario decidiese si quiere 
	        	 		//un organo para donar o para recibir, o nose como veais vosotras, en plan mostrar no modificar
	        	 		System.out.println("\nChoose an option[1-5]:");
	                    String read2= console.readLine();
	                    int option2= Integer.parseInt(read2); 
	                    switch(option2){
	                    	case 1:
	                    		List<Hospital> hosps = uiHospital.searchHospital(dbManager);
	                    		Iterator <Hospital> it = hosps.iterator();
	                	 		int counter = 1;
	               
	                	 		while (it.hasNext()){
	                	 			Hospital hospi = it.next();
	                	 			System.out.println(counter + ". " + hospi);
	                	 			counter++;
	                	 		}	
	                	 		if(counter ==1){
	                	 			System.out.println("Hospital not found, would you like to introduce a new hospital?: [yes/no]");
	                	 			if ((console.readLine()).equalsIgnoreCase("yes")){
	                	 				uiHospital.introduceNewHospital(dbManager);
	                	 				break;
	                	 			}
	                	 			
	                	 		}
	                	 		if (counter!=1){
	                    		System.out.print("\nRELATED WITH THE HOSPITAL THAT YOU JUST LOOKED FOR:");
	                    		System.out.print("\n1. Update information.");
	                    		System.out.print("\n2. Delete hospital.");
	                    		System.out.print("\n3. Go back to the menu. ");
	                    		System.out.print("\nChoose an option[1-3]:");
	                    		String opt = console.readLine();
	                    		int op = Integer.parseInt(opt);
	                    		switch (op){
	                    			case 1:
	                    				System.out.println("Introduce the number of the hospital: ");
	                    				int numHosp = Integer.parseInt(console.readLine());
	                    				Hospital hospUp = hosps.get(numHosp-1);
	                    				uiHospital.updateHospital(hospUp, dbManager);
	                    				break;
	                    			case 2:
	                    				System.out.println("Introduce the number of the hospital: ");
	                    				int num = Integer.parseInt(console.readLine());
	                    				Hospital hospDel = hosps.get(num-1);
	                    				uiHospital.deleteHospital(hospDel, dbManager);
	                    				break;
	                    			case 3:
	                    				break;
	                    		}
	                    		break;
	                	 	   }
	                    	case 2:
	                    		List <Doctor> doct= uiDoctor.searchDoctor(dbManager);
	                    		Iterator <Doctor> it1 = doct.iterator();
	                	 		int counterDoctor = 1;
	                	 		while (it1.hasNext()){
	                	 			Doctor doctor = it1.next();
	                	 			System.out.println(counterDoctor + ". " + doctor);
	                	 			counterDoctor++;
	                	 		}
	                	 		if(counterDoctor ==1){
	                	 			System.out.println("Doctor not found, would you like to introduce a new Doctor?: [yes/no]");
	                	 			if ((console.readLine()).equalsIgnoreCase("yes")){
	                	 				uiDoctor.introduceNewDoctor(dbManager);
	                	 				break;
	                	 			}
	                	 		}
	                	 		if (counterDoctor !=1){
	                	 		System.out.print("\nRELATED WITH THE DOCTOR THAT YOU JUST LOOKED FOR:");
	                    		System.out.print("\n1. Update information.");
	                    		System.out.print("\n2. Delete information.");
	                    		System.out.print("\n3. See the hospital in which the doctor works.");
	                    		System.out.print("\n4. Go back to the menu. ");
	                    		System.out.print("\nChoose an option[1-4]:");
	                    		String optDoctor = console.readLine();
	                    		int opDoctor = Integer.parseInt(optDoctor);
	                    		switch (opDoctor){
                    			case 1:
                    				System.out.println("Introduce the number of the doctor: ");
                    				int numDoct = Integer.parseInt(console.readLine());
                    				Doctor doctUp = doct.get(numDoct-1);
                    				uiDoctor.updateDoctor(doctUp, dbManager);
                    				break;
                    			case 2:
                    				System.out.println("Introduce the number of the doctor: ");
                    				numDoct = Integer.parseInt(console.readLine());
                    				Doctor doctDel = doct.get(numDoct-1);
                    				uiDoctor.deleteDoctor(doctDel, dbManager);
                    				break;
                    			case 3: 
                    				System.out.println("Introduce the number of the doctor: ");
                    				numDoct = Integer.parseInt(console.readLine());
                    				Doctor doctSearch = doct.get(numDoct-1);
                    				uiHospital.DoctorHospital(doctSearch.getNameOfDoctor(), dbManager);
                    				break;
                    			case 4:
                    				break;
                    				
                    		}
	                    		break;
	                	 	}		
	                    	case 3:
	                    		List <Donor> donor= uiDonor.searchDonor(jpaManager);
	                    		Iterator <Donor> it2 =donor.iterator();
	                	 		int counterDonor = 1;
	                	 		while (it2.hasNext()){
	                	 			Donor don = it2.next();
	                	 			System.out.println(counterDonor + ". " + don);
	                	 			counterDonor++;
	                	 		}
	                	 		if(counterDonor ==1){
	                	 			System.out.println("Donor not found, would you like to introduce a new Donor?: [yes/no]");
	                	 			if ((console.readLine()).equalsIgnoreCase("yes")){
	                	 				uiDonor.introduceNewDonor(jpaManager);
	                	 				break;
	                	 			}
	                	 		}
	                	 		if (counterDonor!=1){
	                	 		System.out.print("\nRELATED WITH THE DONOR THAT YOU JUST LOOKED FOR:");
	                    		System.out.print("\n1. Update information.");
	                    		System.out.print("\n2. Delete information.");
	                    		System.out.print("\n3. See the organs that is donating");
	                    		System.out.print("\n4. Go back to the menu. ");
	                    		System.out.print("\nChoose an option[1-4]:");
	                    		String optDonor = console.readLine();
	                    		int opDonor = Integer.parseInt(optDonor);
	                    		int numDon = 0;
	                    		switch (opDonor){
                    			case 1:
                    				System.out.println("Introduce the number of the donor: ");
                    				numDon = Integer.parseInt(console.readLine());
                    				Donor donUp = donor.get(numDon-1);
                    				uiDonor.updateDonor(donUp, jpaManager);
                    				break;
                    			case 2:
                    				System.out.println("Introduce the number of the donor: ");
                    				numDon = Integer.parseInt(console.readLine());
                    				Donor donDel = donor.get(numDon-1);
                    				uiDonor.deleteDonor(donDel, jpaManager);
                    				break;
                    			case 3:
                    				System.out.println("Introduce the number of the donor: ");
                    				numDon = Integer.parseInt(console.readLine());
                    				Donor donOrg = donor.get(numDon-1);
                    				uiOrgan.organsOfDonor(donOrg, dbManager);
                    				break;
                    			case 4:
                    				break;
                    				
	                    		}
	                    		break;
	                	 	}
	                    	case 4:
	                    		List <Patient> pat= uiPatient.searchPatient(jpaManager);
	                    		Iterator <Patient> it3 = pat.iterator();
	                	 		int counterPat = 1;
	                	 		while (it3.hasNext()){
	                	 			Patient p = it3.next();
	                	 			System.out.println(counterPat + ". " + p);
	                	 			counterPat++;
	                	 		}
	                	 		if(counterPat ==1){
	                	 			System.out.println("Patient not found, would you like to introduce a new patient?: [yes/no]");
	                	 			if ((console.readLine()).equalsIgnoreCase("yes")){
	                	 				uiPatient.introduceNewPatient(jpaManager);
	                	 				break;
	                	 			}
	                	 		}
	                	 		if (counterPat!=1){
	                	 		System.out.print("\nRELATED WITH THE PATIENT THAT YOU JUST LOOKED FOR:");
	                    		System.out.print("\n1. Update information.");
	                    		System.out.print("\n2. Delete information.");
	                    		System.out.print("\n3. See where the patient is hospitalised and the doctors that treat him. ");
	                    		System.out.print("\n4. Check the characteristics of the requested organ. ");
	                    		System.out.print("\n5. Go back to the menu. ");
	                    		System.out.print("\nChoose an option[1-5]:");
	                    		String optP = console.readLine();
	                    		int opPat = Integer.parseInt(optP);
	                    		switch (opPat){
                    			case 1:
                    				System.out.println("Introduce the number of the patient: ");
                    				int numPat = Integer.parseInt(console.readLine());
                    				Patient patUp = pat.get(numPat-1);
                    				uiPatient.updatePatient(patUp,jpaManager);
                    				break;
                    			case 2:
                    				System.out.println("Introduce the number of the patient: ");
                    				int numPat2 = Integer.parseInt(console.readLine());
                    				Patient patDel = pat.get(numPat2-1);
                    				uiPatient.deletePatient(patDel, jpaManager);
                    				break;
                    			case 3:
                    				System.out.println("Introduce the number of the patient: ");
                    				int numPat3 = Integer.parseInt(console.readLine());
                    				Patient patHD = pat.get(numPat3-1);
                    				uiPatient.patientHospitalAndDoctor(patHD.getName(),jpaManager);
                    				break;
                    			case 4:
                    				System.out.println("Introduce the number of the patient: ");
                    				int numPat4 = Integer.parseInt(console.readLine());
                    				//metodo al que le pasas el id paciente y te muestra las caracteristicas del organo que necesita el paciente
                    				Patient patReq = pat.get(numPat4-1);
                    				List<Requested_organ> reqs = uiRequested.characteristicsOfRequestedOrgans(patReq.getId(), dbManager);
                    				System.out.println("Patient: " + patReq.getName() + " needs the following organs: \n");
                    				Iterator <Requested_organ> itReq = reqs.iterator();
                    				int countReq = 1;
                    				while (itReq.hasNext()){
                    					Requested_organ r = itReq.next();
                    					System.out.println(countReq + ". " + r);
                    					countReq++;
                    				}
                    				
                    				break;
                    			case 5:
                    				break;                    				
	                    		}
	                    		break;
	                	 	}
	                    	break;
	                    }	        	 		
	        	 	}
	        	 	break;
	        	         	
	        	 	case 3:
	        	 		//In rodrigos, the xml thing is done with jpa, im going to try to do it with jdbc
	        	 		//uiHospital.javaToXmlHospital(dbManager);
	        	 		break;
	        	 	
	        	 	case 4:
	        	 		System.out.println("The program has been closed.");
	        	 		System.exit(0);        	 		 		
	        	 }
	        
	        }
		}catch(IOException ex){
			ex.printStackTrace();
		}
	

}
	
	}