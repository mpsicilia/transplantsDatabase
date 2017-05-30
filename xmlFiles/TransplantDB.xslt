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
   	 place and the doctors that work on them in a really graphical way.</p>
   	 <!-- This is an empty line before we actually put our tables on it -->
   	 <br/>
   	 <p>This is a table for the doctors: </p>
   	    <table border ="1">
	   	 	<th>id</th>
	   	 	<th>nameOfDoctor</th>
	   	 	<th>specialization</th>
	   	    <th>registrationNumber</th>
	   	    <th>Hospitals</th>
	   	 		<xsl:for-each select= "TransplantDatabase/Hospitals/Hospital/Doctors/Doctor">
	   	 			<tr>
	   	 				<td><i><xsl:value-of select ="@id"/></i></td>
			   			<td><i><xsl:value-of select ="@nameOfDoctor"/></i></td>
			   			<td><i><xsl:value-of select ="specialization"/></i></td>
			   			<td><i><xsl:value-of select ="registrationNumber"/></i></td>
			   			<!-- <td><i><xsl:value-of select ="Hospitals/Hospital/@id"/></i></td>-->
	   	 			</tr>	   						
   				</xsl:for-each>
   		</table>
   	 <br/>
   	 <p>This is a table for the hospitals: </p>
  	 <!-- This is going to be the table for a hospital -->
   	 <table border ="1">
	   	 <th>id</th>
	   	 <th>name</th>
	   	 <th>phoneNumber</th>
	   	 <th>address</th>
	   	 <th>City</th>
	   	 <th>postcode</th>
	   	 <th>Country</th>
   			 <xsl:for-each select="TransplantDatabase/Hospitals/Hospital">
   			 	<!-- For each table row -->
   			 	<tr>
   			 	   <td><i><xsl:value-of select ="@id"/></i></td>
   			 	   <td><i><xsl:value-of select ="@name"/></i></td>
   			 	   <td><i><xsl:value-of select ="phoneNumber"/></i></td>
   			 	   <td><i><xsl:value-of select ="address"/></i></td>
   			 	   <td><i><xsl:value-of select ="City"/></i></td>
   			 	   <td><i><xsl:value-of select ="postcode"/></i></td>
   			 	   <td><i><xsl:value-of select ="Country"/></i></td> 	   			 	   		
   			 	</tr>   			 
   			 </xsl:for-each> 		
   	 	</table>
   	 <br/>
   	 <p>This is a table for the patients: </p>
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
   			 <xsl:for-each select="TransplantDatabase/Hospitals/Hospital/Patients/Patient">
   			 	<!-- For each table row -->
   			 	<tr>
   			 	   <td><i><xsl:value-of select ="@id"/></i></td>
   			 	   <td><i><xsl:value-of select ="@name"/></i></td>
   			 	   <td><i><xsl:value-of select ="birthDate"/></i></td>
   			 	   <td><i><xsl:value-of select ="gender"/></i></td>
   			 	   <td><i><xsl:value-of select ="weight"/></i></td>
   			 	   <td><i><xsl:value-of select ="height"/></i></td>
   			 	   <td><i><xsl:value-of select ="bloodType"/></i></td> 	   	
   			 	   <td><i><xsl:value-of select ="pathology"/></i></td> 
   			 	   <td><i><xsl:value-of select ="additionDate"/></i></td> 
   			 	   <td><i><xsl:value-of select ="lifeExpectancy"/></i></td> 
   			 	   <td><i><xsl:value-of select ="score"/></i></td> 	 	 	 	 		 	   		
   			 	</tr>   			 
   			 </xsl:for-each> 		
   	 	</table>
   	</body>
   </html>
</xsl:template>
</xsl:stylesheet>