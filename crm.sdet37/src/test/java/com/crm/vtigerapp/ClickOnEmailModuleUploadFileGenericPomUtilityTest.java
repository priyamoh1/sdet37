package com.crm.vtigerapp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.ObjectRepository.EmailPage;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClickOnEmailModuleUploadFileGenericPomUtilityTest {

	public static void main(String[] args) throws AWTException, IOException 
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
		
		String SUBJECT = eLib.getDataFromExcel("email", 1, 0);
		String DESC = eLib.getDataFromExcel("email", 1, 3);
		String CONTACTNAME = eLib.getDataFromExcel("contact", 2, 1);

		System.setProperty(IConstants.chromeKey, IConstants.value);

		//open browser
		WebDriver driver=new ChromeDriver();

		//give wit duration
		wLib.waitTillPageGetsLoad(driver);

		//maximize the window
		wLib.minimizeTheBrowser(driver);

		//enter the URL
		driver.get(URL);

		///login to the application
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LogiontoApp(USERNAME, PASSWORD);

		//click on email module
		HomePage homepage = new HomePage(driver);
		homepage.getEmailmodule().clear();
		
		//compose new email
		EmailPage emailpage = new EmailPage(driver);
		emailpage.composenewmail(driver, CONTACTNAME, SUBJECT, DESC);
		
		//check if email is present or not
		String verifyemail=emailpage.getVerifyemail().getText();	
		if(verifyemail.contains(CONTACTNAME))
		{
			System.out.println("email is created ");
		}
		else
		{
			System.out.println("email is not created");
		}

		//logout from v-tiger application
		homepage.logout(driver);
	}

}
