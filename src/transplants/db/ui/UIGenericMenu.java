package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;


import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;

public class UIGenericMenu {

	//private BufferedReader reader;
	//private Integer option;
	
	public static void main (String []args){		
		UIGenericMenu genericMenu= new UIGenericMenu();
	}	
	
	public UIGenericMenu(){
		DBManager dmanager = new DBManager();
		UI_Hospitals uiHospital=new UI_Hospitals();
		UI_Doctor uiDoctor=new UI_Doctor();
		UI_Donor uiDonor=new UI_Donor();
		UI_Patient uiPatient=new UI_Patient();
		try{
			BufferedReader console= new BufferedReader (new InputStreamReader (System.in));
	        int option=0;
	        
	        /*System.out.println("Temporary option: DROP ALL THE TABLES? [Y/N]");
	        String drop = console.readLine();
	        if(drop.equalsIgnoreCase("Y")){
	        	boolean dropped = dmanager.dropTables();
	        	if(dropped){
	        		System.out.println("Tables have been dropped. ");
	        	}
	        	else{
	        		System.out.println("Tables have not been dropped. ");
	        	}
	        }
	        */
	        System.out.println("Do you want to create the tables?: [yes/no]");
	        String decider= console.readLine();
	        if (decider.equals("yes")){
	        	boolean created = dmanager.createTables();
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
	        	System.out.println("\nMENU: ");
	        	System.out.println("1. Introduce new information to the database. ");
	        	System.out.println("2. Search for specific information in the database. ");
	        	System.out.println("3. Options that this database offers in order to make "
	        						+ "transplantation prosses more efficient.");
	        	System.out.println("4. Exit from the database. ");
	        		        	
	        	 do{
	                    System.out.println("\nChoose an option[1-4]:");
	                    String read= console.readLine();
	                    option= Integer.parseInt(read);            
	                }while (option<0|| option>4);
	        	 switch (option){
	        	 	case 1: {
	        	 		System.out.println("\nMENU: ");
	    	        	System.out.println("1. Introduce a new hospital. ");
	    	        	System.out.println("2. Introduce a new doctor. ");
	    	        	System.out.println("3. Introduce a new donor.");
	    	        	System.out.println("4. Introduce a new patient. ");
	    	        	System.out.println("5. Introduce a new request for an organ. ");
	    	        	System.out.println("6. Introduce a new organ in order to be donanted. ");
	    	        	System.out.println("\nChoose an option[1-6]:");
	                    String read1= console.readLine();
	                    int option1= Integer.parseInt(read1); 
	                    switch (option1){
		                    case 1:
		                    	uiHospital.introduceNewHospital();
		                    	break;
		                    case 2:
		                    	uiDoctor.introduceNewDoctor();
		                    	break;
		                    case 3:
		                    	uiDonor.introduceNewDonor();
		                    	break;
		                    case 4:
		                    	uiPatient.introduceNewPatient();
		                    	break;
		                    case 5:
		                    case 6:
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
	                    		List<Hospital> hosps = uiHospital.searchHospital();
	                    		Iterator <Hospital> it = hosps.iterator();
	                	 		int counter = 1;
	                	 		//-->to do/*¡¡¡¡HAY QUE PONER MENSAJE DE QUE SI NO ENCUENTRA EL DOCTOR SI LO DESEA AÑADIR O NO!!!!*/
	                	 		while (it.hasNext()){
	                	 			Hospital hospi = it.next();
	                	 			System.out.println(counter + ". " + hospi);
	                	 			counter++;
	                	 		}	                	 		
	                    		System.out.print("RELATED WITH THE HOSPITAL THAT YOU JUST LOOKED FOR:");
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
	                    				uiHospital.updateHospital(hospUp);
	                    				break;
	                    			case 2:
	                    				System.out.println("Introduce the number of the hospital: ");
	                    				int num = Integer.parseInt(console.readLine());
	                    				Hospital hospDel = hosps.get(num-1);
	                    				uiHospital.deleteHospital(hospDel);
	                    				break;
	                    			case 3:
	                    				break;
	                    		}
	                    		break;
	                    		
	                    	case 2:
	                    		List <Doctor> doct= uiDoctor.searchDoctor();
	                    		Iterator <Doctor> it1 = doct.iterator();
	                	 		int counterDoctor = 1;
	                	 		while (it1.hasNext()){
	                	 			Doctor doctor = it1.next();
	                	 			System.out.println(counterDoctor + ". " + doctor);
	                	 			counterDoctor++;
	                	 		}
	                	 		System.out.print("RELATED WITH THE DOCTOR THAT YOU JUST LOOKED FOR:");
	                    		System.out.print("\n1. Update information.");
	                    		System.out.print("\n2. Delete information.");
	                    		System.out.print("\n3. See the hospital in which the doctor works.");
	                    		System.out.print("\nChoose an option[1-3]:");
	                    		String optDoctor = console.readLine();
	                    		int opDoctor = Integer.parseInt(optDoctor);
	                    		switch (opDoctor){
                    			case 1:
                    				System.out.println("Introduce the number of the doctor: ");
                    				int numDoct = Integer.parseInt(console.readLine());
                    				Doctor doctUp = doct.get(numDoct-1);
                    				uiDoctor.updateDoctor(doctUp);
                    				break;
                    			case 2:
                    				System.out.println("Introduce the number of the doctor: ");
                    				numDoct = Integer.parseInt(console.readLine());
                    				Doctor doctDel = doct.get(numDoct-1);
                    				uiDoctor.deleteDoctor(doctDel);
                    				break;
                    			case 3: 
                    				System.out.println("Introduce the number of the doctor: ");
                    				numDoct = Integer.parseInt(console.readLine());
                    				Doctor doctSearch = doct.get(numDoct-1);
                    				uiHospital.DoctorHospital(doctSearch.getNameOfDoctor());
                    				break;
                    				
                    		}
                    		break;
	                    			
	                    	case 3:
	                    		List <Donor> donor= uiDonor.searchDonor();
	                    		Iterator <Donor> it2 =donor.iterator();
	                	 		int counterDonor = 1;
	                	 		while (it2.hasNext()){
	                	 			Donor don = it2.next();
	                	 			System.out.println(counterDonor + ". " + don);
	                	 			counterDonor++;
	                	 		}
	                	 		System.out.print("RELATED WITH THE DONOR THAT YOU JUST LOOKED FOR:");
	                    		System.out.print("\n1. Update information.");
	                    		System.out.print("\n2. Delete information.");
	                    		System.out.print("\n3. See in what hospital he is and who is taking care of him.");
	                    		System.out.print("\nChoose an option[1-3]:");
	                    		String optDonor = console.readLine();
	                    		int opDonor = Integer.parseInt(optDonor);
	                    		switch (opDonor){
                    			case 1:
                    				System.out.println("Introduce the number of the donor: ");
                    				int numDon = Integer.parseInt(console.readLine());
                    				Donor donUp = donor.get(numDon-1);
                    				uiDonor.updateDonor(donUp);
                    				break;
                    			case 2:
                    				System.out.println("Introduce the number of the donor: ");
                    				numDon = Integer.parseInt(console.readLine());
                    				Donor donDel = donor.get(numDon-1);
                    				uiDonor.deleteDonor(donDel);
                    				break;
                    			case 3:
                    				break;
                    				
	                    		}
	                    		break;
	                    	case 4:
	                    		List <Patient> pat= uiPatient.searchPatient();
	                    		Iterator <Patient> it3 = pat.iterator();
	                	 		int counterPat = 1;
	                	 		while (it3.hasNext()){
	                	 			Patient p = it3.next();
	                	 			System.out.println(counterPat + ". " + p);
	                	 			counterPat++;
	                	 		}
	                	 		System.out.print("RELATED WITH THE PATIENT THAT YOU JUST LOOKED FOR:");
	                    		System.out.print("\n1. Update information.");
	                    		System.out.print("\n2. Delete information.");
	                    		System.out.print("\nChoose an option[1-2]:");
	                    		String optP = console.readLine();
	                    		int opPat = Integer.parseInt(optP);
	                    		switch (opPat){
                    			case 1:
                    				System.out.println("Introduce the number of the patient: ");
                    				int numPat = Integer.parseInt(console.readLine());
                    				Patient patUp = pat.get(numPat-1);
                    				uiPatient.updatePatient(patUp);
                    				break;
                    			case 2:
                    				System.out.println("Introduce the number of the patient: ");
                    				numPat = Integer.parseInt(console.readLine());
                    				Patient patDel = pat.get(numPat-1);
                    				uiPatient.deletePatient(patDel);
                    				break;
                    			case 3:
                    				break;
                    				
	                    		}
	                    		break;
	                    	case 5:
	                    }
	        	 		
	        	 	}
	        	 	break;
	        	 	//al igual podemos hacer solo dos opciones generales y a partir de ahi poner
	        	 	//todas estas opciones. Seguro que lo vamos viendo a medida que lo vayamos haciendo
	        	 	//pero he apuntado las ideas aqui para que no se nos olviden
	        	 	case 3:
	        	 		System.out.print("1. Search a hospital by the type of organ transplantation"
  	 				          			 + "that takes place in it.");
	        	 		System.out.print("2. Hospital and doctor that is in charge of the patient.");
	        	 		System.out.print("3. Hospital and doctor that is in charge of the donor.");
	        	 		System.out.print("4. Hospital in which the organ that is going to"
	        	 				         + " be donated is located, and the owner of it.");
	        	 		System.out.print("5. Patient that is reciving an organ.");
	        	 		System.out.print("6. Compatibility test.");
	        	 		System.out.print("7. See if there is a donor for the organ that you want.");
	        	 		System.out.print("8. Number of organs of a specific organ that can be donated.");
	        	 		System.out.print("9. Check the waiting list.");
	        	 		
	        	 		break;
	        	 	
	        	 	case 4:
	        	 		System.exit(0);
	        	 	}
	        	 		 		
	        	 }
	        
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
	
}

}