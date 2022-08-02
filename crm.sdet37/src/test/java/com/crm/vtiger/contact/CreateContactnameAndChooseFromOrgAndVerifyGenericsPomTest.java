package com.crm.vtiger.contact;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.ObjectRepository.ContactPage;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import com.crm.vtiger.ObjectRepository.NewContactPage;
import com.crm.vtiger.ObjectRepository.VerifyContactPage;

public class CreateContactnameAndChooseFromOrgAndVerifyGenericsPomTest {

	public static void main(String[] args) throws IOException
	{
		WebDriver driver=null;
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility(); 

		String SALUTATION = eLib.getDataFromExcel("contact", 1, 4);
		String FIRSTNAME = eLib.getDataFromExcel("contact", 1, 0);
		String LASTNAME = eLib.getDataFromExcel("contact", 1, 1);
		String ORGNAME = eLib.getDataFromExcel("organization", 1, 11);


		//get the properties from the property file using getproperty
		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");	


		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			System.setProperty(IConstants.chromeKey, IConstants.value);
			//open browser
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			System.setProperty(IConstants.chromeKey, IConstants.value);
			//open browser
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}

		System.setProperty(IConstants.chromeKey, IConstants.value);
		
		//open browser
		driver=new ChromeDriver();
		
		//give the waiting duration
		wLib.waitTillPageGetsLoad(driver);

		//maximize the window
		wLib.maximizeThepage(driver);

		//enter the URL
		driver.get(URL);
		
		///login to the application
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LogiontoApp(USERNAME, PASSWORD);

		//create a new contact
		HomePage homepage = new HomePage(driver);
		ContactPage contact = new ContactPage(driver);
		NewContactPage createcontact = new NewContactPage(driver);
		contact.getCreatecontact().click();
		createcontact.createnewcontactwithorgnamesave(driver, SALUTATION, FIRSTNAME, LASTNAME, ORGNAME);

		//verify whether the contact is created or not
		VerifyContactPage verifycontact = new VerifyContactPage(driver);
		String contactverify= verifycontact.getVerify().getText();
		if(contactverify.contains(FIRSTNAME))
		{
			System.out.println("conatct is created");
		}
		else
		{
			System.out.println("contact is not created");
		}
		
		//mouse hover on the logout icon click on the logout link
		homepage.logout(driver);

	}

}
