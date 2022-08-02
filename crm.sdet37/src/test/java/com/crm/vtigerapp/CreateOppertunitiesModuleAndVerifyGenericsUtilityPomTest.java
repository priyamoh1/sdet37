package com.crm.vtigerapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import com.crm.vtiger.ObjectRepository.NewOpportunityPage;
import com.crm.vtiger.ObjectRepository.OpportunityInfoPage;
import com.crm.vtiger.ObjectRepository.OpportunityPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOppertunitiesModuleAndVerifyGenericsUtilityPomTest {

	public static void main(String[] args) throws IOException 
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		//get the properties from the propertyfile using getproperty
		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");

		//get the datta from the excel sheet
		String TYPE = eLib.getDataFromExcel("opportunities", 1, 3);
		String OPPORTUNITYNAME = eLib.getDataFromExcel("opportunities", 1, 0);
		String LEADSOURCE = eLib.getDataFromExcel("opportunities", 4, 4);
		String CUSTOMERNAME = eLib.getDataFromExcel("invoice", 1, 5);
		String ASSETNAME = eLib.getDataFromExcel("invoice", 1, 0);
		String INVOICENUMBER = eLib.getDataFromExcel("invoice", 1, 4);
		String PRODUCTNAME = eLib.getDataFromExcel("invoice", 1, 1);
		String ORGNAME = eLib.getDataFromExcel("Organization", 1, 10);

		System.setProperty(IConstants.chromeKey, IConstants.value);

		//open browser
		WebDriver driver=new ChromeDriver();
		//give the waiting duration
		wLib.waitTillPageGetsLoad(driver);

		//maximize the window
		wLib.minimizeTheBrowser(driver);
		
		//sign in to the application
		LoginPage login = new LoginPage(driver);
		login.LogiontoApp(USERNAME, PASSWORD);
		
		//click on opportunities module
		HomePage home = new HomePage(driver);
		home.getOppertunitiesmodule().click();
		
		//create new opportunities
		OpportunityPage opportunity = new OpportunityPage(driver);
		opportunity.clickonnewopportunity();
		
		//enter into opportunity text field
		NewOpportunityPage newopportunity = new NewOpportunityPage(driver);
		newopportunity.opportunitytext(OPPORTUNITYNAME);
		
		//click on the org lookup icon and all the data
		newopportunity.orglookup(driver, ORGNAME);
		
		//select from type dropdown
		newopportunity.typedropdown(TYPE);
		
		//select from lead source drop down
		newopportunity.leadsourcedd(LEADSOURCE);

		//click on the save button
		newopportunity.save();

		//check whether opportunity is created or not
		OpportunityInfoPage opportunityinfo = new OpportunityInfoPage(driver);
		String text=opportunityinfo.getVerify().getText();
		if(text.contains("demo"))
		{
			System.out.println("campaign is created");
		}
		else
		{
			System.out.println("campaign is not created");
		}

		//logout from the v-tiger application
			home.logout(driver);
	}

}
