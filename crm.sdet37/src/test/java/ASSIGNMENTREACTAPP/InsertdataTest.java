package ASSIGNMENTREACTAPP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertdataTest {

	public static void main(String[] args) throws SQLException 
	{
		
		com.mysql.jdbc.Driver driverref=new com.mysql.jdbc.Driver();
		DriverManager.registerDriver(driverref);
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement stat=conn.createStatement();
		 String query="insert into project values('TY_PROJECT005','CHITTARANJAN','02/03/22','LENSKART','CREATED',8)";
		int result= stat.executeUpdate(query);
		
		  if(result==1)
		  { 
			  System.out.println("data is created"); 
			  } 
		  else
		  {
		  System.out.println("data is not created");
		  }
		 
		 
		conn.close();
	}
}


