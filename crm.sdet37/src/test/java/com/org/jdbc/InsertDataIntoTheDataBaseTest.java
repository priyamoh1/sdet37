package com.org.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class InsertDataIntoTheDataBaseTest {

	public static void main(String[] args) throws SQLException 
	{
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		Statement stat=conn.createStatement();
		String query="insert into students values('pintu1','moh1','rmg56','1991/08/02','male')";
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
