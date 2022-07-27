package com.crm.comcast.genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DataBaseUtility 
{
	static Driver driverref;
	static Connection connection;
	static ResultSet result;
	/**
	 * this method is used to connect to DB
	 * @param DBname
	 */
	public void connectToDb(String DBname)
	{
		try 
		{
			driverref= new Driver();
			DriverManager.registerDriver(driverref);
			connection=DriverManager.getConnection(IConstants.DbUrl+DBname,IConstants.username,IConstants.password);

		} catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	/**
	 * this method is used to close the connection
	 */
	public void closeDB()
	{
		try {
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	/**
	 * this method is used to execute query
	 * @param query
	 * @param columnNum
	 * @param expectedData
	 * @return
	 * @throws SQLException
	 */
	public boolean executeQuery(String query,int columnNum,String expectedData) throws SQLException
	{
		result=connection.createStatement().executeQuery(query);
		boolean flag=false;
		while(result.next())
		{
			if(result.getString(columnNum).equals(expectedData))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("data is present");
			return flag;
		}
		else
		{
			System.out.println("data is not present");
			return flag;
		}

	}
	/**
	 * this method is used to execute update
	 * @param query
	 * @throws SQLException
	 */
	public void executeUpdate(String query) throws SQLException
	{
		int res=connection.createStatement().executeUpdate(query);
		if(res==1)
		{
			System.out.println("data is updated");
		}
		else
		{
			System.out.println("data is not updated");
		}
	}

}

