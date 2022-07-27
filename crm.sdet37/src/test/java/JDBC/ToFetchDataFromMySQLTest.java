package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;
public class ToFetchDataFromMySQLTest 
{

	public static void main(String[] args) throws SQLException
	{
		//step1:register to database
		Driver driverref=new com.mysql.jdbc.Driver();
		DriverManager.registerDriver(driverref);
		//step2:connect to mysql
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		//step3:create statement/query
		Statement stat=conn.createStatement();
		String query="select*from students";
		//step4:execute the query
		ResultSet set=stat.executeQuery(query);
		while(set.next())
		{
			System.out.println(set.getString(1)+"\t"+set.getString(2)+"\t"+set.getString(3)+"\t"+set.getString(4)+"\t"+set.getString(5));
		}
		//step5:close the database connection 
		conn.close();
	}

}
