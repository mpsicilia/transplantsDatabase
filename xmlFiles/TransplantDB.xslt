<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <!-- First we are going to give a title to our page, and it is going to appear in bold letter -->
   <head><title><b>Database for organs transplant.</b></title></head>
   <body>
   	 <h3>Purpose of this database:</h3>
   	 <p>This database was created in order to speed up transplantations in order to facilitated work in hospitals.
   	 For example, here we are going to illustrate the information of the hospitals in which transplants can take
   	 place the doctors that work on them and the patients that contains in a really graphical way.</p>
   	 <!-- This is an empty line before we actually put our tables on it -->
   	 <br/>
   	 <xsl:for-each select="TransplantDatabase/Hospitals/Hospital">
	   	 <p>Hospital with id=<xsl:value-of select ="@id"/> has the following information: </p>
	   	 <p>*Name: <xsl:value-of select ="@name"/></p>
	   	 <p>*PhoneNumber:<xsl:value-of select ="phoneNumber"/></p>
	   	 <p>*Address: <xsl:value-of select ="address"/></p>
	   	 <p>*City: <xsl:value-of select ="City"/></p>
	   	 <p>*Postcode: <xsl:value-of select ="postcode"/></p>
	   	 <p>*Country: <xsl:value-of select ="Country"/></p>
	   	 <p>Contains the following doctors: </p>
	   	  	<table border ="1">
	   	 	<th>id</th>
	   	 	<th>name</th>
	   	 	<th>specialization</th>
	   	    <th>registrationNumber</th>
	   	 		<tr>
	   	 			<td><i><xsl:value-of select ="Doctors/Doctor/@id"/></i></td>
			   		<td><i><xsl:value-of select ="Doctors/Doctor/@name"/></i></td>
			   		<td><i><xsl:value-of select ="Doctors/Doctor/specialization"/></i></td>
			   		<td><i><xsl:value-of select ="Doctors/Doctor/registrationNumber"/></i></td>
	   	 		</tr>	   						
   			</table>
   		<br/>
   		<p>Contains the following patients: </p>
	   		<table border ="1">
			   	 <th>id</th>
			   	 <th>name</th>
			   	 <th>birthDate</th>
			   	 <th>gender</th>
			   	 <th>weight</th>
			   	 <th>height</th>
			   	 <th>bloodType</th>
			   	 <th>pathology</th>
			   	 <th>additionDate</th>
			   	 <th>lifeExpectancy</th>
			   	 <th>score</th>
		   	 	<tr>
   			 	   <td><i><xsl:value-of select ="Patients/Patient/@id"/></i></td>
   			 	   <td><i><xsl:value-of select ="Patients/Patient/@name"/></i></td>
   			 	   <td><i><xsl:value-of select ="Patients/Patient/birthDate"/></i></td>
   			 	   <td><i><xsl:value-of select ="Patients/Patient/gender"/></i></td>
   			 	   <td><i><xsl:value-of select ="Patients/Patient/weight"/></i></td>
   			 	   <td><i><xsl:value-of select ="Patients/Patient/height"/></i></td>
   			 	   <td><i><xsl:value-of select ="Patients/Patient/bloodType"/></i></td> 	   	
   			 	   <td><i><xsl:value-of select ="Patients/Patient/pathology"/></i></td> 
   			 	   <td><i><xsl:value-of select ="Patients/Patient/additionDate"/></i></td> 
   			 	   <td><i><xsl:value-of select ="Patients/Patient/lifeExpectancy"/></i></td> 
   			 	   <td><i><xsl:value-of select ="Patients/Patient/score"/></i></td>	 	 	 		 	   		
   			 	</tr>
   			 	</table>
   			 <br/>
   			 <br/>
	</xsl:for-each>    	 	
   	</body>
   </html>
</xsl:template>
</xsl:stylesheet>