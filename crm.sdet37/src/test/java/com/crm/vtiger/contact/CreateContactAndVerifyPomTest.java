package com.crm.vtiger.contact;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactAndVerifyPomTest {

	public static void main(String[] args) throws IOException
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility(); 
		
		String SALUTATION = eLib.getDataFromExcel("contact", 1, 4);
		String FIRSTNAME = eLib.getDataFromExcel("contact", 1, 0);
		String LASTNAME = eLib.getDataFromExcel("contact", 1, 1);
		
		//get the properties from the property file using getproperty
		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");	
		
		//String BROWSER=property.getProperty("browser");
		WebDriverManager.chromedriver().setup();
		
		//open browser
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	
		//maximize the window
		driver.manage().window().maximize();
		
		//enter the URL
		driver.get(URL);
		
		//enter the username
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[.=\"Contacts\"]")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		WebElement ty= driver.findElement(By.name("salutationtype"));
		wLib.select(SALUTATION, ty);
		driver.findElement(By.name("firstname")).sendKeys(FIRSTNAME);
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify whether the organization is created or not
		String verify=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(verify.contains("FERRARI"))
		{
			System.out.println("conatct is created");
		}
		else
		{
			System.out.println("contact is not created");
		}
		//mouse hover on the logout icon
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		wLib.mouseOverOnElement(driver, ele2);
		
		//click on the signout link
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();


	}

}
