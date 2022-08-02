package com.crm.vtiger.automate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchDataFromReactAppTest {

	public static void main(String[] args) throws SQLException
	{
		
		String project_name="LENSKART";
		Connection con=null;
		//get register for my sql db
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		//connect to mysql db using url
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		//create a statement/query
		Statement stat= con.createStatement();
		//write a query
			String query="insert into project values('TY_PROJECT048','CHITTARANJAN','02/03/22','"+project_name+"','CREATED',8)";
			//fetch all the column data
			 int update= stat.executeUpdate(query);
			//verify the data in db
			if(update==1)
			{
			  System.out.println("data is inserted");
			  
			}

		try 
		{
			
			
			WebDriverManager.chromedriver().setup();
			//open browser
			WebDriver driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
			//maximize the window
			driver.manage().window().maximize();
			//enter the url
			driver.get("http://localhost:8084/");
			//enter username
			driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
			//enter password
			driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
			//sign in to the welcome page
			driver.findElement(By.xpath("//button[.=\"Sign in\"]")).click();
			//click in the projects button or link
			driver.findElement(By.xpath("//a[.=\"Projects\"]")).click();
			List<WebElement> lists= driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
			for(WebElement web:lists)
			{
				String alldata=web.getText();
				if(alldata.contains(project_name))
				{
					System.out.println("data is present");
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			con.close();
			
		}
		
	}

}
