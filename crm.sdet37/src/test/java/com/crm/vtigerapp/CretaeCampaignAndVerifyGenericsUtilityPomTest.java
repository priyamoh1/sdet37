package com.crm.vtigerapp;

import java.io.IOException;
import java.time.Duration;
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
import com.crm.vtiger.ObjectRepository.CampaignPage;
import com.crm.vtiger.ObjectRepository.CampaignsInfoPage;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import com.crm.vtiger.ObjectRepository.NewCampaignPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CretaeCampaignAndVerifyGenericsUtilityPomTest {

	public static void main(String[] args) throws IOException
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		//String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");

		//fetch the data from the excel sheet
		String CAMPAIGNNAME = eLib.getDataFromExcel("campaign", 1, 0);
		String PRODUCT = eLib.getDataFromExcel("product", 1, 0);

		System.setProperty(IConstants.chromeKey, IConstants.value);

		//open browser
		WebDriver driver=new ChromeDriver();
		
		//give the waiting duration
		wLib.waitTillPageGetsLoad(driver);
		
		//maximize the window
		wLib.maximizeThepage(driver);
		
		//login to the application
		LoginPage login = new LoginPage(driver);
		login.LogiontoApp(USERNAME, PASSWORD);
		
		//mouse over on the more and click on the campaign module
		HomePage home = new HomePage(driver);
		home.clickoncampaign();
		
		//click on campaign
		CampaignPage campaign = new CampaignPage(driver);
		campaign.clickonnewcampaign();
		
		//enter campaign name
		NewCampaignPage newcamp = new NewCampaignPage(driver);
		newcamp.campaigntextfield(CAMPAIGNNAME);
		
		//click on the product lookup icon and enter all the data
		newcamp.productlookup(PRODUCT, driver);
		
		//save the campaign
		CampaignsInfoPage campinfo = new CampaignsInfoPage(driver);
		String info=campinfo.getCampverify().getText();
		if(info.contains(PRODUCT))
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
