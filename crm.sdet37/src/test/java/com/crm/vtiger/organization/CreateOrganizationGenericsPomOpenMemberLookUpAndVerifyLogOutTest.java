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
import com.crm.vtiger.ObjectRepository.OrganizationPage;
import com.crm.vtiger.ObjectRepository.VerifyOrganizationPage;

public class CreateOrganizationGenericsPomOpenMemberLookUpAndVerifyLogOutTest {

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

		//get the data from the excel sheet
		String ORGNAME = eLib.getDataFromExcel("Organization", 2, 11);
		String INDUSTRY = eLib.getDataFromExcel("Organization", 9, 8);
		String ACCOUNTTYPE = eLib.getDataFromExcel("Organization", 3, 10);

		System.setProperty(IConstants.chromeKey, IConstants.value);

		//open browser
		WebDriver driver=new ChromeDriver();
		
		//give the waiting duration
        wLib.waitTillPageGetsLoad(driver);

		//maximize the window
		wLib.maximizeThepage(driver);

		//enter the URL
		driver.get(URL);

		HomePage homepage = new HomePage(driver);
		LoginPage loginpage = new LoginPage(driver);
		OrganizationPage organization = new OrganizationPage(driver);
		NewOrganizationPage neworg = new NewOrganizationPage(driver);
		VerifyOrganizationPage verifyorg = new VerifyOrganizationPage(driver);

		//login to the v-tiger application
		loginpage.LogiontoApp(USERNAME, PASSWORD);

		//click on organization module
		homepage.getOrganizationmodule().click();

		//create new organization
		organization.getNeworganization().click();
		neworg.createNewOrganization(driver, ORGNAME, INDUSTRY, ACCOUNTTYPE);

		//verify whether the organization is created or not
		verifyorg.Verify(ORGNAME);

		//mouse over on the administrator icon and click on logout link
		homepage.logout(driver);


	}

}
