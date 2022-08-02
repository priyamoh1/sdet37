package com.crm.vtigerapp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

public class CreateInvoiceAndVerifyGenericUtilityTest {

	public static void main(String[] args) throws IOException, AWTException 
	{

		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		// get the common data from the properties file
		
		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");
//		FileInputStream fis1=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
//		Properties property=new Properties();
//		property.load(fis1); 
//		//get the properties from the propertyfile using getproperty
//		String URL=property.getProperty("url");
//		String USERNAME= property.getProperty("username");
//		String PASSWORD=property.getProperty("password");
		
		//get the data from the excel file
		
//		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\data.xlsx");
//		Workbook wb= WorkbookFactory.create(fis);
//		Sheet sh= wb.getSheet("invoice");
//		org.apache.poi.ss.usermodel.Row r= sh.getRow(1);
//		Cell C= r.getCell(0);
//		String SUBJECT=C.toString();
//		org.apache.poi.ss.usermodel.Row r1= sh.getRow(1);
//		Cell C1= r.getCell(2);
//		String SALESORDER=C1.toString();
//		org.apache.poi.ss.usermodel.Row r4= sh.getRow(1);
//		Cell C4= r.getCell(1);
//		String CONTACTNAME=C4.toString();
//		org.apache.poi.ss.usermodel.Row r5= sh.getRow(1);
//		Cell C5= r.getCell(3);
//		String ORGNAME=C5.toString();
//		org.apache.poi.ss.usermodel.Row r6= sh.getRow(1);
//		Cell C6= r.getCell(7);
//		String ADDRESS=C6.toString();
//		org.apache.poi.ss.usermodel.Row r7= sh.getRow(1);
//		Cell C7= r.getCell(4);
//		String PRODUCTNAME=C7.toString();
//		org.apache.poi.ss.usermodel.Row r8= sh.getRow(1);
//		Cell C8= r.getCell(5);
//		String QUANTITY=C8.toString();
//		org.apache.poi.ss.usermodel.Row r9= sh.getRow(1);
//		Cell C9= r.getCell(6);
//		String PRICE=C8.toString();
		String SUBJECT = eLib.getDataFromExcel("invoice", 1, 0);
		String SALESORDER = eLib.getDataFromExcel("invoice", 1, 2);
		String CONTACTNAME = eLib.getDataFromExcel("invoice", 1, 1);
		String ORGNAME = eLib.getDataFromExcel("invoice", 1, 3);
		String ADDRESS = eLib.getDataFromExcel("invoice", 1, 7);
		String PRODUCTNAME = eLib.getDataFromExcel("invoice", 1, 4);
		String QUANTITY = eLib.getDataFromExcel("invoice", 1, 5);
		String PRICE = eLib.getDataFromExcel("invoice", 1, 6);
		
		
		System.setProperty(IConstants.chromeKey, IConstants.value);
		//WebDriverManager.chromedriver().setup();
		
		//open browser
		WebDriver driver=new ChromeDriver();
		
		// give the waiting condition
		wLib.minimizeTheBrowser(driver);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		
		//maximize the window
		driver.manage().window().maximize();
		
		//enter the URL
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//click ion the invoice module
		
		WebElement ele= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/menuDnArrow.gif\"]"));
		wLib.mouseOverOnElement(driver, ele);
//		Actions act=new Actions(driver);
//		act.moveToElement(ele).perform();
		
		//click on sales order
		driver.findElement(By.name("Invoice")).click();
		//create a new invoice
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		//enter the subject name in the subject textfield
		driver.findElement(By.name("subject")).sendKeys(SUBJECT);
		//click on the sales order lookup icon
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/select.gif\"]")).click();
		
		//switch to the child window
		
		wLib.switchToWindow(driver, "SalesOrder&action");
//		Set<String> childwindow = driver.getWindowHandles();
//		for(String str:childwindow )
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("SalesOrder&action"))
//			{
//				break;
//			}
//		}
		//enter the sales order number in the search textfield
		driver.findElement(By.name("search_text")).sendKeys(SALESORDER);
		//click on the search button
		driver.findElement(By.name("search")).click();
		//click on the sales order created
		driver.findElement(By.xpath("//a[.=\"335\"]")).click();
		
		//switch to parent window
		
		wLib.switchToWindow(driver, "Invoice&action");
//		Set<String> parentwindow = driver.getWindowHandles();
//		for(String str:parentwindow)
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("Invoice&action"))
//			{
//				break;
//			}
//		}
		
		//click on the contact name lookup icon
		driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[2]")).click();
		
		//switch to child windows
		
		wLib.switchToWindow(driver, "Contacts&action");
//		childwindow=driver.getWindowHandles();
//		for(String str:childwindow)
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("Contacts&action"))
//			{
//				break;
//			}
//		}
		
		//enter the contact in the search textfield
		driver.findElement(By.name("search_text")).sendKeys(CONTACTNAME);
		//click on the search button
		driver.findElement(By.name("search")).click();
		//click on the contact name
		driver.findElement(By.xpath("//a[.=\"HANZO FERRARI1\"]")).click();
		
		//handle the popup
		
		wLib.EnterAndRelease();
//		Robot r2=new Robot();
//		r2.keyPress(KeyEvent.VK_ENTER);
//		r2.keyRelease(KeyEvent.VK_ENTER);
		
		//switch to parent window
		
		wLib.switchToWindow(driver, "Invoice&action");
//		parentwindow=driver.getWindowHandles();
//		for(String str:parentwindow)
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("Invoice&action"))
//			{
//				break;
//			}
//		}
		//click on the organization lookup icon
		driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[3]")).click();
		
		//switch to child windows
		
		wLib.switchToWindow(driver, "Accounts&action");
//		childwindow=driver.getWindowHandles();
//		for(String str:childwindow)
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("Accounts&action"))
//			{
//				break;
//			}
//		}
		
		//enter the sales order number in the search textfield
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		//click on the search button
		driver.findElement(By.name("search")).click();
		//click on the organization name
		driver.findElement(By.xpath("//a[.=\"PRIYA\"]")).click();
		
		//handle the popup
		
		wLib.EnterAndRelease();
//		Robot r3=new Robot();
//		r3.keyPress(KeyEvent.VK_ENTER);
//		r3.keyRelease(KeyEvent.VK_ENTER);
		
		//switch to parent window
		
		wLib.switchToWindow(driver, "Invoice&action");
//		parentwindow=driver.getWindowHandles();
//		for(String str:parentwindow)
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("Invoice&action"))
//			{
//				break;
//			}
//		}
		
		
		//enter the billing address
		driver.findElement(By.name("bill_street")).sendKeys(ADDRESS);
		//click on the copy billing address radio button
		driver.findElement(By.name("cpy")).click();
		//click on the product lookup icon
		driver.findElement(By.xpath("//img[@src=\"themes/images/products.gif\"]")).click();
		
		//switch to the parent window
		wLib.switchToWindow(driver, "Products&action");
//		childwindow=driver.getWindowHandles();
//		for(String str:childwindow)
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("Products&action"))
//			{
//				break;
//			}
//		}
		//enter the product namer in the search textfield
		driver.findElement(By.name("search_text")).sendKeys(PRODUCTNAME);
		//click on the search button
		driver.findElement(By.name("search")).click();
		//click on the product
		driver.findElement(By.xpath("//a[.=\"iphone\"]")).click();
		
		//switch to the parent window
		wLib.switchToWindow(driver, "Invoice&action");
		
//		parentwindow=driver.getWindowHandles();
//		for(String str:parentwindow)
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("Invoice&action"))
//			{
//				break;
//			}
//		}
		//enter the quantity in the quantity textfield
		driver.findElement(By.name("qty1")).sendKeys(QUANTITY);
		driver.findElement(By.name("listPrice1")).clear();
		//enter list price in the list price textfield
		driver.findElement(By.name("listPrice1")).sendKeys(PRICE);
		//click on the save button
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		String text=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(text.contains("iphoneinvoice"))
		{
			System.out.println("invoice is created");
		}
		else
		{
			System.out.println("invoice is not created");
		}
		//logout from the vtiger application
		WebElement ele1= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		wLib.mouseOverOnElement(driver, ele1);
//		Actions act1=new Actions(driver);
//		act1.moveToElement(ele1).perform();
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();

	}

}
