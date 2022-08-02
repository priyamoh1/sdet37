package com.crm.vtigerapp;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.ObjectRepository.DocumentPage;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import com.crm.vtiger.ObjectRepository.NewDocumentPage;

public class ClickOnDocumentAndUploadDocumentGenericsPomUtilityTest
{

	public static void main(String[] args) throws AWTException, IOException
	{

		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");	
		
		String NOTETITLE = eLib.getDataFromExcel("documents", 1, 0);
		String NOTEBOX = eLib.getDataFromExcel("documents", 1, 1);
		String CUSTOMERTYPE = eLib.getDataFromExcel("documents", 3, 10);

		System.setProperty(IConstants.chromeKey, IConstants.value);

		//open browser
		WebDriver driver=new ChromeDriver();

		wLib.waitTillPageGetsLoad(driver);

		//maximize the window
		wLib.minimizeTheBrowser(driver);

		//enter the URL
		driver.get(URL);

		///login to the application
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LogiontoApp(USERNAME, PASSWORD);

		//click on document module
		HomePage homepage = new HomePage(driver);
		homepage.getDocumentsmodule().click();
		
		//create new document
		DocumentPage documents = new DocumentPage(driver);
		documents.getDocumentsmodule().click();
		NewDocumentPage newdoc = new NewDocumentPage(driver);
		newdoc.createnewdoc(NOTETITLE, NOTEBOX);
		
		//click on save button
		newdoc.getSavebutton().click();
		
		//check whether the document is created or not
		String document=newdoc.getVerify().getText();

		if(document.contains(NOTETITLE))
		{
			System.out.println("document is created");
		}
		else
		{
			System.out.println("document is not created");
		}

		//logout from the vtiger app
		homepage.logout(driver);

	}

}
