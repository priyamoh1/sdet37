package com.crm.vtigerapp;

import java.io.IOException;
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
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import com.crm.vtiger.ObjectRepository.NewSalesOrderPage;
import com.crm.vtiger.ObjectRepository.SalesOrderInfoPage;
import com.crm.vtiger.ObjectRepository.SalesOrderPage;

public class CreateSalesOrderAndVerifyGenericsUtilityPomTest {

	public static void main(String[] args) throws IOException
	{

		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		//get the properties from the property file using getproperty
		//String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");

		//fetch the data form the excel sheet
		String BILLSTREET = eLib.getDataFromExcel("salesorder", 1, 10);
		String POBOX = eLib.getDataFromExcel("salesorder", 1, 13);
		String BILLCITY = eLib.getDataFromExcel("salesorder", 1, 12);
		String STATE = eLib.getDataFromExcel("salesorder", 1, 11);
		String BILLCODE = eLib.getDataFromExcel("salesorder", 1, 14);
		String COUNTRY = eLib.getDataFromExcel("salesorder", 1, 9);
		String QUANTITY = eLib.getDataFromExcel("salesorder", 1, 7);
		String LISTPRICE = eLib.getDataFromExcel("salesorder", 1, 8);
		String PRODUCTNAME = eLib.getDataFromExcel("product", 1, 0);
		String ORGANIZATIONNAME = eLib.getDataFromExcel("Organization", 1, 10);
		String SUBJECTNAME = eLib.getDataFromExcel("salesorder", 1, 0);
		String OPPORTUNITYNAME = eLib.getDataFromExcel("opportunities", 1, 0);
		String QUOTESNAME = eLib.getDataFromExcel("quotes", 1, 0);

		System.setProperty(IConstants.chromeKey, IConstants.value);


		//open browser
		WebDriver driver=new ChromeDriver();

		//give the waiting duration
		wLib.waitTillPageGetsLoad(driver);

		//maximize the window
		wLib.minimizeTheBrowser(driver);

		//enter the URL
		driver.get(URL);

		LoginPage loginpage = new LoginPage(driver);
		loginpage.LogiontoApp(USERNAME, PASSWORD);

		//mouse hover on more options and click on the sales order
		HomePage homepage = new HomePage(driver);
		homepage.clickonsaleorder(driver);

		//create new sales order
		SalesOrderPage saleorder = new SalesOrderPage(driver);
		saleorder.getNewsalesorder().click();
		//enter the dat into the textfield
		NewSalesOrderPage newsalesorder = new NewSalesOrderPage(driver);
		newsalesorder.subjecttextfield(SUBJECTNAME);

		//click on opportunity lookup and enter all the data
		newsalesorder.oppportunitylkp(driver, OPPORTUNITYNAME);

		//click on the quotes lookup and enter all the data
		newsalesorder.quotelookup(driver, QUOTESNAME);

		//click on the organization lookup and enter all the data
		newsalesorder.oppportunitylkp(driver, ORGANIZATIONNAME);


		//enter the data into the address fields
		newsalesorder.enteraddres(BILLSTREET, POBOX, BILLCITY, STATE, BILLCODE, COUNTRY);

		//click on the product lookup icon and enter all the data
		newsalesorder.clickonproductlkp(driver, PRODUCTNAME);

		//enter the data into the quantity tex field
		newsalesorder.quantity(QUANTITY);

		//enter the data into the list price text field
		newsalesorder.enterlistprice(LISTPRICE);

		//click on the save button
		newsalesorder.clickonsavebutton();

		//check whether sales order is created or not
		SalesOrderInfoPage saleoderinfo = new SalesOrderInfoPage(driver);
		String info=saleoderinfo.getVerifysalesorder().getText();
		if(info.contains("335 "))
		{
			System.out.println("sales order is created");
		}
		else
		{
			System.out.println("sales order  is not created");
		}

		//mouse hover on the logout icon and click on the logout link
		homepage.logout(driver);


	}

}
