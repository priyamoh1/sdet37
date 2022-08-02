package com.crm.vtiger.contact;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.ObjectRepository.ContactPage;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import com.crm.vtiger.ObjectRepository.NewContactPage;
import com.crm.vtiger.ObjectRepository.VerifyContactPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactAndVerifyGenericsPomTest {

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
		
		HomePage homepage = new HomePage(driver);
		LoginPage loginpage = new LoginPage(driver);
		ContactPage contact = new ContactPage(driver);
		NewContactPage createcontact = new NewContactPage(driver);
		VerifyContactPage verifycontact = new VerifyContactPage(driver);
		
		//login to the application
		loginpage.LogiontoApp(USERNAME, PASSWORD);
		
		//click on the contact module
		homepage.getContactsmodule().click();
		
		//create a new contact
		contact.getCreatecontact().click();
	    createcontact.createnewcontactandsave(SALUTATION, FIRSTNAME, LASTNAME);
	    
		//verify whether the organization is created or not
		String verifycont=verifycontact.getVerify().getText();
		if(verifycont.contains(FIRSTNAME))
		{
			System.out.println("contact is created");
		}
		else
		{
			System.out.println("contact is not created");
		}
		
		//mouse hover on the logout icon and logout
		homepage.logout(driver);
		


	}

}
