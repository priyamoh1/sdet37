package com.crm.vtiger.organization;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import com.crm.vtiger.ObjectRepository.NewOrganizationPage;

public class CreateOrganizationAndVerifyLogOutGenericsPomTest {

	public static void main(String[] args) throws IOException 
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		//get the properties from the property file using getproperty
		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");	

		String INDUSTRYDROPDOWN = eLib.getDataFromExcel("Organization", 9, 8);
		String ORGANIZATIONNAME = eLib.getDataFromExcel("Organization", 1, 10);
		String CUSTOMERTYPE = eLib.getDataFromExcel("Organization", 3, 10);

		System.setProperty(IConstants.chromeKey, IConstants.value);

		//open browser
		WebDriver driver=new ChromeDriver();

		// give the the waiting condition
		wLib.waitTillPageGetsLoad(driver);


		//maximize the window
		wLib.minimizeTheBrowser(driver);


		///login to the application
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LogiontoApp(USERNAME, PASSWORD);

		//click on organization module
		HomePage homepage = new HomePage(driver);
		homepage.getOrganizationmodule().click();
		
		//create new organization
		NewOrganizationPage organization = new NewOrganizationPage(driver);
		organization.createNewOrganization(driver, ORGANIZATIONNAME, INDUSTRYDROPDOWN, CUSTOMERTYPE);
		
		//verify whether the organization is created or not
		String verify=organization.getVerify().getText();
		if(verify.contains(ORGANIZATIONNAME))
		{
			System.out.println("org is created");
		}
		else
		{
			System.out.println("org is not created");
		}

		//mouse hover on the logout icon
		homepage.logout(driver);

	}

}
