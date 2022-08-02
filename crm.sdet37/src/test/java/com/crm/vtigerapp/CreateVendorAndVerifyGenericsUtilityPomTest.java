package com.crm.vtigerapp;

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
import com.crm.vtiger.ObjectRepository.NewProductsPage;
import com.crm.vtiger.ObjectRepository.NewVendorsPage;
import com.crm.vtiger.ObjectRepository.ProductInfoPage;
import com.crm.vtiger.ObjectRepository.VendorsPage;

public class CreateVendorAndVerifyGenericsUtilityPomTest 
{

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

		//fetch the data from excel sheet
		String VENDORNAME = eLib.getDataFromExcel("vendor", 1, 0);
		String PRODUCTNAME = eLib.getDataFromExcel("product", 1, 0);

		System.setProperty(IConstants.chromeKey, IConstants.value);

		//open browser
		WebDriver driver=new ChromeDriver();

		//give the wait duration
		wLib.waitTillPageGetsLoad(driver);

		//maximize the window
		wLib.minimizeTheBrowser(driver);

		///login to the application
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LogiontoApp(USERNAME, PASSWORD);

		//create a new vendor
		HomePage homepage = new HomePage(driver);
		homepage.clickonvendormodule(driver);

		//create new vendor
		VendorsPage vendor = new VendorsPage(driver);
		vendor.getNewvendor().click();

		NewVendorsPage newvendor = new NewVendorsPage(driver);
		String text = newvendor.getVerifyvendor().getText();
		if(text.contains(VENDORNAME))
		{
			System.out.println("vendor is created");
		}
		else
		{
			System.out.println("vendor is not created");
		}

		//click on product module
		homepage.getProductsmodule().click();
		NewProductsPage newproduct = new NewProductsPage(driver);
		//create new product
		newproduct.getProduct().click();

		//enter product name
		newproduct.getProductname().sendKeys(PRODUCTNAME);
		newproduct.getVendorlookup().click();

		//switch to child window
		newproduct.switchtochild(driver);

		//enter data in vendor lookup
		newproduct.getProductname().sendKeys(VENDORNAME);
		newproduct.getSearchbutton().click();
		newproduct.getVendorname().click();

		//Switch to parent window
		newproduct.switchtoparentwindow(driver);

		//click on the save button
		newproduct.getSavebutton().click();

		//check whether product is created or not
		ProductInfoPage verify = new ProductInfoPage(driver);
		text=verify.getProductverify().getText();
		if(text.contains(PRODUCTNAME))
		{
			System.out.println("contact is created");
		}
		else
		{
			System.out.println("contact is not created");
		}

		//logout from v-tiger
		homepage.logout(driver);


	}

}
