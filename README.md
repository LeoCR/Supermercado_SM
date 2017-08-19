# JSF JPA RESTFUl CRUD Primefaces with MySQL


Simple CRUD ,MySQL , JPA ,Primefaces for Glasfish in Netbeans.

This Code was build with these tutorials:
https://netbeans.org/kb/docs/websvc/rest.html#create-services-from-db
https://netbeans.org/kb/docs/web/jsf20-crud.html 
For persist Restful Web Services from Database.

Using the Glasfish Version 4.1.2 Full Platform ( https://javaee.github.io/glassfish/download )
#Setp by Setp

1.Import this Database: https://github.com/LeoCR/Supermercado_SM/blob/master/supermarket_sm_sql/jpa_ddl/final_ddl.sql in your favorite MySQL DBAs ,such as MySQL Workbench or in your phpAdmin(XAMPP,MAMPP,WAMPP).

2.Make sure that you have the MySQL driver in your Glasfish Library Folder ( For example : C:\Program Files\glassfish4\glassfish\lib ) ,so if you havent copy and paste this file.jar:
http://www.java2s.com/Code/JarDownload/com.mysql/com.mysql.jdbc_5.1.5.jar.zip  ,See this bug before continue :
https://computingat40s.wordpress.com/how-to-setup-a-jdbc-connection-in-glassfish/

3.Edit the glassfish-resources.xml for set your MySQL Credentials such as : Password , Username ,ServerName,Port ,URL  (https://github.com/LeoCR/Supermercado_SM/blob/master/supermarket_sm/supermercado-sm/src/main/webapp/WEB-INF/glassfish-resources.xml ) ,and make sure that all the credentials works.

4.Review your Glasfish libraries and make sure that you have fixed the library org.eclipse.persistence.moxy.jar on the root of your Glasfish Server $FOLDER_INSTALL_GLASSFISH/glassfish/modules/org.eclipse.persistence.moxy.jar ,because the Glasfish have this Bug:
https://bugs.eclipse.org/bugs/show_bug.cgi?id=463169  ,so for fix it I replaced the org.eclipse.persistence.moxy.jar for this new one https://mega.nz/#!7UghGLJI!jQVt3CguE0GHFmsV_2xbaQzTDKMCjCeBQ9neFIhQgYU   and the Bug of Glasfish was solved.

5.In your Glassfish Admin Console create an JDBC Pool Connection called 'mysql_supermarket_sm_rootPool' set your MySQL Credentials such as : Password , Username ,ServerName,Port ,URL equals to glassfish-resources.xml ,then Add new Property and set the driverClass and in the Value write com.mysql.jdbc.Driver.

6.Then Open your Netbeans and Open this Maven Web Project: https://github.com/LeoCR/Supermercado_SM/tree/master/supermarket_sm/supermercado-sm
and Right Click on the project ,then select Clean and Build.

7.Make the deployment manually so then open your Glasfish Admin Console,going to Applications and click on Add New ,then upload your supermarket_sm.war ,if you having errors review your database connections or glassfish-resources.xml .

8.The visit http://localhost:8080/supermercado-sm/admin/panel/administracion.xhtml or 
 http://localhost:8080/supermercado-sm/index.xhtml
 
9.Review your console in your browser ,that should not be mistaken console errors.
 
 


