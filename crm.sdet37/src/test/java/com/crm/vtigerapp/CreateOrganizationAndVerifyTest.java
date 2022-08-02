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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationAndVerifyTest {

	public static void main(String[] args) throws InterruptedException, IOException 
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

		String INDUSTRYDROPDOWN = eLib.getDataFromExcel("Organization", 9, 8);
		String ORGANIZATIONNAME = eLib.getDataFromExcel("Organization", 1, 9);
		String CUSTOMERTYPE = eLib.getDataFromExcel("Organization", 3, 10);
		String SALUTATIONTYPE = eLib.getDataFromExcel("contact", 1, 3);
		String FIRSTNAME = eLib.getDataFromExcel("contact", 1, 0);
		String LASTNAME = eLib.getDataFromExcel("contact", 1, 1);

		//		FileInputStream fis1=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		//		Properties property=new Properties();
		//		property.load(fis1); 
		//		String URL=property.getProperty("url");
		//		String USERNAME= property.getProperty("username");
		//		String PASSWORD=property.getProperty("password");

		System.setProperty(IConstants.chromeKey, IConstants.value);
		//		WebDriverManager.chromedriver().setup();

		//open browser
		WebDriver driver=new ChromeDriver();

		//give the waiting duration
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
		//click on organization module
		driver.findElement(By.xpath("//a[.=\"Organizations\"]")).click();
		//create new organization
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(ORGANIZATIONNAME);

		//select from the industry drop down
		WebElement list= driver.findElement(By.name("industry"));
		wLib.select(INDUSTRYDROPDOWN, list);

		//select  from the customer type drop down
		WebElement list1= driver.findElement(By.name("accounttype"));
		wLib.select(CUSTOMERTYPE, list1);

		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		Thread.sleep(1000);
		//confirm whether the organization name is present or not
		String confirm= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(confirm.contains(ORGANIZATIONNAME))
		{
			System.out.println("org is created");

		}
		else
		{
			System.out.println("org not created");
		}
		//click on the contact module
		driver.findElement(By.xpath("(//a[.='Contacts'])[1]")).click();
		Thread.sleep(1000);
		//create contact
		driver.findElement(By.xpath("//img[@alt=\'Create Contact...\']")).click();

		//handle the dropdownlist
		WebElement list2= driver.findElement(By.name("salutationtype"));
		wLib.select(SALUTATIONTYPE, list2);

		driver.findElement(By.name("firstname")).sendKeys(FIRSTNAME);
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/select.gif\"]")).click();

		//switch to child windows
		wLib.switchToWindow(driver, "Accounts&action");
		//		Set<String> childwindow= driver.getWindowHandles();
		//		for(String Str3:childwindow)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Accounts&action"))
		//			{
		//				break;	
		//			}
		//		}


		driver.findElement(By.name("search_text")).sendKeys(ORGANIZATIONNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='PRIYA10']")).click();

		//switch to parent windows
		wLib.switchToWindow(driver, "Contacts&action");
		//		Set<String> parentwindow= driver.getWindowHandles();
		//		for(String Str3:parentwindow)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Contacts&action"))
		//			{
		//				break;	
		//			}
		//		}


		//click on save button
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//check contact is created or not
		String text=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(text.contains("hjgjhgkj klkj"))
		{
			System.out.println("contact is created");
		}
		else
		{
			System.out.println("contact is not created");
		}
		WebElement ele= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		wLib.mouseOverOnElement(driver, ele);
		//		Actions act=new Actions(driver);
		//		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();

	}

}
