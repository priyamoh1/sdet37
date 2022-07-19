package ASSIGNMENTREACTAPP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class FetchdataTest {

	public static void main(String[] args) throws SQLException 
	{
		//step1:register to database
		Driver d=new Driver();
		DriverManager.registerDriver(d);
		//step2:connect to mysql
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		//step3:create statement/query
		Statement stat= con.createStatement();
		String query="select*from project";
		//step4:execute the query
		ResultSet set= stat.executeQuery(query);
		while(set.next())
		{
			System.out.println(set.getString(1)+"\t"+set.getString(2)+"\t"+set.getString(3)+"\t"+set.getString(4)+"\t"+set.getString(5)+"\t"+set.getString(6));
		}
		//step5:close the database connection 
		con.close();
	}

}
