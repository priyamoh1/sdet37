package com.crm.vtiger.automate;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.mysql.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomatermgTest {

	public static void main(String[] args) throws InterruptedException, AWTException, SQLException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		Properties property=new Properties();
		property.load(fis); 
		//get the properties from the propertyfile using getproperty
		String URL=property.getProperty("url1");
		String USERNAME= property.getProperty("username1");
		String PASSWORD=property.getProperty("password1");
		//String BROWSER=property.getProperty("browser");
		String projectname="PINTUMOH";
		String projectmanager="MOHANTY";
		Connection con=null;
		WebDriverManager.chromedriver().setup();
		//open browser
		WebDriver driver=new ChromeDriver();
		//maximize the window
		driver.manage().window().maximize();
		//enter the url
		driver.get(URL);
		//enter username
		driver.findElement(By.id("usernmae")).sendKeys(USERNAME);
		//enter password
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		//sign in to the welcome page
		driver.findElement(By.xpath("//button[.=\"Sign in\"]")).click();
		Thread.sleep(1000);
		//click in the projects button or link
		driver.findElement(By.xpath("//a[.=\"Projects\"]")).click();
		Thread.sleep(1000);
		//creating new project
		driver.findElement(By.xpath("//span[.=\"Create Project\"]")).click();
		Thread.sleep(1000);
		//enetr project name
		driver.findElement(By.name("projectName")).sendKeys(projectname);
		//enter project manager name
		driver.findElement(By.name("createdBy")).sendKeys(projectmanager);
		Thread.sleep(1000);
		//selecting project status
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		//click on add project
		driver.findElement(By.cssSelector("input[class=\"btn btn-success\"]")).click();
		try 
		{
			//get register for my sql db
			Driver driverref=new Driver();
			DriverManager.registerDriver(driverref);
			//connect to mysql db using url
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			//create a statement/query
			Statement stat= con.createStatement();
			//write a query
			String query="select*from project";
			//fetch all the column data
			ResultSet set= stat.executeQuery(query);
			//verify the data in db
			//boolean flag=false;
			while(set.next())
			{
				String allprojects=set.getString(2);
				if(allprojects.contains(projectmanager))
				{
					System.out.println("project is created in db");
					//flag=true;
					break;
				}
			}
			/*
			 * if(flag) { System.out.println("project is created in db"); } else {
			 * System.out.println("project is not created"); }
			 */
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			//close db connection
			con.close();
		}
		//close the browser
		driver.quit();

	}

}
