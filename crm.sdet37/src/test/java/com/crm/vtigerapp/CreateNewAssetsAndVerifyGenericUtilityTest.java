package com.crm.vtigerapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateNewAssetsAndVerifyGenericUtilityTest {

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
//		Cell C= r.getCell(2);
//		String SERIALNUMBER=C.toString();
//		org.apache.poi.ss.usermodel.Row r1= sh.getRow(1);
//		Cell C1= r.getCell(4);
//		String TRACKINGNUMBER=C1.toString();
//		org.apache.poi.ss.usermodel.Row r4= sh.getRow(1);
//		Cell C4= r.getCell(5);
//		String CUSTOMERNAME=C4.toString();
//		org.apache.poi.ss.usermodel.Row r5= sh.getRow(1);
//		Cell C5= r.getCell(5);
//		String ASSETNAME=C5.toString();
//		org.apache.poi.ss.usermodel.Row r6= sh.getRow(1);
//		Cell C6= r.getCell(3);
//		String INVOICENUMBER=C6.toString();
//		org.apache.poi.ss.usermodel.Row r7= sh.getRow(1);
//		Cell C7= r.getCell(1);
//		String PRODUCTNAME=C7.toString();
		
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
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		//maximize the window
		wLib.minimizeTheBrowser(driver);
//		driver.manage().window().maximize();
		
		//enter the URL
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		
		//click on the asset module
		WebElement ele= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/menuDnArrow.gif\"]"));
		wLib.mouseOverOnElement(driver, ele);
//		Actions act=new Actions(driver);
//		act.moveToElement(ele).perform();
		
		//click on sales order
		driver.findElement(By.name("Assets")).click();
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[1]")).click();
		
		//switch to the child windows
		wLib.switchToWindow(driver, "Products&action");
//		Set<String> childwindow = driver.getWindowHandles();
//		for(String str:childwindow )
//		{
//			driver.switchTo().window(str);
//			String title=driver.getTitle();
//			if(title.contains("Products&action"))
//			{
//				break;
//			}
//		}
		//enter the sales order number in the search textfield
				driver.findElement(By.name("search_text")).sendKeys(PRODUCTNAME);
				//click on the search button
				driver.findElement(By.name("search")).click();
				//click on the sales order created
				driver.findElement(By.xpath("//a[.=\"iphone\"]")).click();
				
				//switch to parent window
				wLib.switchToWindow(driver, "Assets&action");
//				Set<String> parentwindow = driver.getWindowHandles();
//				for(String str:parentwindow)
//				{
//					driver.switchTo().window(str);
//					String title=driver.getTitle();
//					if(title.contains("Assets&action"))
//					{
//						break;
//					}
//				}
				
				driver.findElement(By.name("serialnumber")).sendKeys(SERIALNUMBER);
				driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[2]")).click();
				
				//switch to the child windows
				wLib.switchToWindow(driver, "Invoice&action");
				
//				Set<String> childwindow1 = driver.getWindowHandles();
//				for(String str:childwindow1)
//				{
//					driver.switchTo().window(str);
//					String title=driver.getTitle();
//					if(title.contains("Invoice&action"))
//					{
//						break;
//					}
//				}
				//enter the sales order number in the search textfield
				driver.findElement(By.name("search_text")).sendKeys(INVOICENUMBER);
				//click on the search button
				driver.findElement(By.name("search")).click();
				//click on the sales order created
				driver.findElement(By.xpath("//a[.=\"iphoneinvoice\"]")).click();
				
				//switch to parent window
				wLib.switchToWindow(driver, "Invoice&action");
//				Set<String> parentwindow1 = driver.getWindowHandles();
//				for(String str:parentwindow1)
//				{
//					driver.switchTo().window(str);
//					String title=driver.getTitle();
//					if(title.contains("Invoice&action"))
//					{
//						break;
//					}
//				}
				driver.findElement(By.name("shippingtrackingnumber")).sendKeys(TRACKINGNUMBER);
				driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[3]")).click();
				
				//switch to the child windows
				wLib.switchToWindow(driver, "Accounts&action");
//				Set<String> childwindow2 = driver.getWindowHandles();
//				for(String str:childwindow2)
//				{
//					driver.switchTo().window(str);
//					String title=driver.getTitle();
//					if(title.contains("Accounts&action"))
//					{
//						break;
//					}
//				}
				
				
				//enter the sales order number in the search textfield
				driver.findElement(By.name("search_text")).sendKeys(CUSTOMERNAME);
				//click on the search button
				driver.findElement(By.name("search")).click();
				//click on the sales order created
				driver.findElement(By.xpath("//a[.=\"PRIYA\"]")).click();
				
				//switch to parent window
				wLib.switchToWindow(driver, "Assets&action");
//				Set<String> parentwindow2 = driver.getWindowHandles();
//				for(String str:parentwindow2)
//				{
//					driver.switchTo().window(str);
//					String title=driver.getTitle();
//					if(title.contains("Assets&action"))
//					{
//						break;
//					}
//				}
				
				driver.findElement(By.name("assetname")).sendKeys(ASSETNAME);
				String text=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
				if(text.contains(ASSETNAME))
				{
					System.out.println("asset is created");
				}
				else
				{
					System.out.println("asset is not created");
				}
				//logout from the vtiger application
				WebElement ele1= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
				wLib.mouseOverOnElement(driver, ele1);
//				Actions act1=new Actions(driver);
//				act1.moveToElement(ele1).perform();
				driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();
				
		

	}

}
