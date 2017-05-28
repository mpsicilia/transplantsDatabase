<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <!-- First we are going to give a title to our page, and it is going to appear in bold letter -->
   <head><title><b>Database for organs transplant.</b></title></head>
   <body>
   	 <h3>Purpose of this database:</h3>
   	 <p>This database was created in order to speed up transplantations in order to facilitated work in hospitals.</p>
   	 <!-- This is an empty line before we actually put our tables on it -->
   	 <br/>
  	 <!-- This is going to be the table for a hospital -->
   	 <table border ="1">
	   	 <th>id</th>
	   	 <th>name</th>
	   	 <th>phoneNumber</th>
	   	 <th>address</th>
	   	 <th>City</th>
	   	 <th>postcode</th>
	   	 <th>Country</th>
   			 <xsl:for-each select="TransplantDatabase/Hospital"></xsl:for-each>
   	 </table>
   </body>
   </html>
</xsl:template>
</xsl:stylesheet>