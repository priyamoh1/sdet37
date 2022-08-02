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

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateVendorAndVerifyGenericsUtilityTest 
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
		

		//		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		//		Properties property=new Properties();
		//		property.load(fis); 
		//		//get the properties from the propertyfile using getproperty
		//		String URL=property.getProperty("url");
		//		String USERNAME= property.getProperty("username");
		//		String PASSWORD=property.getProperty("password");

		System.setProperty(IConstants.chromeKey, IConstants.value);
		//		WebDriverManager.chromedriver().setup();

		//open browser
		WebDriver driver=new ChromeDriver();

		//give the wait duration
		wLib.waitTillPageGetsLoad(driver);
		//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//maximize the window
		wLib.minimizeTheBrowser(driver);
		//		driver.manage().window().maximize();

		//enter the URL
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		WebElement ele= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/menuDnArrow.gif\"]"));
		wLib.mouseOverOnElement(driver, ele);

		//click on vendor
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Vendors&action=index\"]")).click();

		//create new vendor
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();

		driver.findElement(By.name("vendorname")).sendKeys(VENDORNAME);
		String vendortext=driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
		if(vendortext.contains(VENDORNAME))
		{
			System.out.println("vendor is created");
		}
		else
		{
			System.out.println("vendor is not created");
		}

		//click on product module
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Products&action=index\"]")).click();

		//create new product
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();

		//enter product name
		driver.findElement(By.name("productname")).sendKeys(PRODUCTNAME);
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/select.gif\"]")).click();

		//switch to child window
		wLib.switchToWindow(driver, "Vendors&action");
		//		Set<String> childwindow= driver.getWindowHandles();
		//		for(String Str3:childwindow)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Vendors&action"))
		//			{
		//				break;	
		//			}
		//		}

		driver.findElement(By.name("search_text")).sendKeys(VENDORNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='mohanty']")).click();

		//Switch to parent window
		wLib.switchToWindow(driver, "Products&action");
		//		Set<String> parentwindow= driver.getWindowHandles();
		//		for(String Str3:parentwindow)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Products&action"))
		//			{
		//				break;	
		//			}
		//		}

		//click on the save button
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		//check whether product is created or not
		String text=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(text.contains(PRODUCTNAME))
		{
			System.out.println("contact is created");
		}
		else
		{
			System.out.println("contact is not created");
		}

		//logout from vtiger
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		wLib.mouseOverOnElement(driver, ele2);
		//		Actions act2=new Actions(driver);
		//		act2.moveToElement(ele2).perform();
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();

	}

}
