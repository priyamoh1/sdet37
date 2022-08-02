package com.crm.vtiger.organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationGenericsAndVerifyLogOutTest {

	public static void main(String[] args) throws IOException 
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

//		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
//		Properties property=new Properties();
//		property.load(fis); 
		//get the properties from the propertyfile using getproperty
		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");	




		//		  String URL=property.getProperty("url"); String USERNAME=
		//		  property.getProperty("username");ee String
		//		  PASSWORD=property.getProperty("password");

		System.setProperty(IConstants.chromeKey, IConstants.value);
		//  WebDriverManager.chromedriver().setup();
		
		//open browser
		WebDriver driver=new ChromeDriver();
		
		// give the the waiting condition
		wLib.waitTillPageGetsLoad(driver);
		/* driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4)); */
		
		//maximize the window
		wLib.minimizeTheBrowser(driver);
		//driver.manage().window().maximize();
		
		//enter the URL
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		//click on organization module
		driver.findElement(By.xpath("//a[.=\"Organizations\"]")).click();
		//create new organization
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();	
		driver.findElement(By.name("accountname")).sendKeys("BMW");
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		String verify=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(verify.contains("BMW"))
		{
			System.out.println("org is created");
		}
		else
		{
			System.out.println("org is not created");
		}
		//mouse hover on the logout icon
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		wLib.mouseOverOnElement(driver, ele2);
		//Actions act2=new Actions(driver);
		//act2.moveToElement(ele2).perform();
		//click on the signout link
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();

	}

}
