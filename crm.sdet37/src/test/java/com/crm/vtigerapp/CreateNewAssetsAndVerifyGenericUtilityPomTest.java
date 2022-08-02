package com.crm.vtigerapp;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.ObjectRepository.AssetInfoPage;
import com.crm.vtiger.ObjectRepository.AssetsPage;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import com.crm.vtiger.ObjectRepository.NewAssetsPage;

public class CreateNewAssetsAndVerifyGenericUtilityPomTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");	

		//fetching the data from the excel sheet

		String SERIALNUMBER = eLib.getDataFromExcel("invoice", 1, 2);
		String TRACKINGNUMBER = eLib.getDataFromExcel("invoice", 1, 4);
		String CUSTOMERNAME = eLib.getDataFromExcel("invoice", 1, 5);
		String ASSETNAME = eLib.getDataFromExcel("invoice", 1, 0);
		String INVOICENUMBER = eLib.getDataFromExcel("invoice", 1, 4);
		String PRODUCTNAME = eLib.getDataFromExcel("invoice", 1, 1);

		System.setProperty(IConstants.chromeKey, IConstants.value);
		//WebDriverManager.chromedriver().setup();


		//open browser
		WebDriver driver=new ChromeDriver();

		//give the waiting duration
		wLib.waitTillPageGetsLoad(driver);

		//maximize the window
		wLib.minimizeTheBrowser(driver);

		///login to the application
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LogiontoApp(USERNAME, PASSWORD);

		//click on the asset module
		HomePage homepage = new HomePage(driver);
		homepage.clickonassetmodule(driver);
        
		//click on new asset icon
		
		AssetsPage asset = new AssetsPage(driver);
		asset.newasset().click();
		
		//create new asset
		NewAssetsPage newasset = new NewAssetsPage(driver);
        newasset.createnewasset(driver, ASSETNAME, PRODUCTNAME, SERIALNUMBER, INVOICENUMBER, TRACKINGNUMBER, PRODUCTNAME);
		
		//verify asset is created or not
        AssetInfoPage assetinfo = new AssetInfoPage(driver);
		String text=assetinfo.getAssetverify().getText();
		if(text.contains(ASSETNAME))
		{
			System.out.println("asset is created");
		}
		else
		{
			System.out.println("asset is not created");
		}
		
		//logout from the v-tiger application
		homepage.logout(driver);
	}

}
