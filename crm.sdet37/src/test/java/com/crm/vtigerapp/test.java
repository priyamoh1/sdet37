package com.crm.vtigerapp;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;

public class test {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");
		System.setProperty(IConstants.chromeKey, IConstants.value);
		WebDriver driver=new ChromeDriver();
		driver.get(URL);
		//enter the username
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//enter the password
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//click on sign in button
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[.='Contacts'])[1]")).click();
		Thread.sleep(1000);
		//create contact
		driver.findElement(By.xpath("//img[@alt=\'Create Contact...\']")).click();
		//handle the dropdownlist
		WebElement list2= driver.findElement(By.name("salutationtype"));
		wLib.getAllOptionsFromDropDown(list2);
	}

}
