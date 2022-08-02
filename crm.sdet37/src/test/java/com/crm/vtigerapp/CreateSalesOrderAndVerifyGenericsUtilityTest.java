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

public class CreateSalesOrderAndVerifyGenericsUtilityTest {

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
		String ORGANIZATIONNAME = eLib.getDataFromExcel("Organization", 1, 11);


		//		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		//		Properties property=new Properties();
		//		property.load(fis); 
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

		//enter the user name
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);

		//enter the password
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

		//click on sign in button
		driver.findElement(By.id("submitButton")).click();

		//mouse hover on more options
		WebElement ele= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/menuDnArrow.gif\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();

		//click on sales order
		driver.findElement(By.name("Sales Order")).click();

		//create new sales order
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		driver.findElement(By.name("subject")).sendKeys("java");
		driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[1]")).click();

		//switch to child window
		wLib.switchToWindow(driver, "Potentials&action");
		//		Set<String> childwindow= driver.getWindowHandles();
		//		for(String Str3:childwindow)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Potentials&action"))
		//			{
		//				break;	
		//			}
		//		}

		//enter data into search text field
		driver.findElement(By.name("search_text")).sendKeys("demo");

		//click on search button
		driver.findElement(By.name("search")).click();

		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"demo\"]")).click();

		//switch to parent window
		wLib.switchToWindow(driver, "Potentials&action");
		//		Set<String> parentwindow= driver.getWindowHandles();
		//		for(String Str3:parentwindow)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Potentials&action"))
		//			{
		//				break;	
		//			}
		//		}

		//			driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[2]")).click();
		//			//switch to child window
		//			Set<String> childwindow1= driver.getWindowHandles();
		//			for(String Str3:childwindow1)
		//			{
		//				driver.switchTo().window(Str3);
		//				String title=driver.getTitle();
		//				if(title.contains("Quotes&action"))
		//				{
		//					break;	
		//				}
		//			}


		//switch to child window
		wLib.switchToWindow(driver, "Contacts&action");
		//		Set<String> childwindow2= driver.getWindowHandles();
		//		for(String Str3:childwindow2)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Contacts&action"))
		//			{
		//				break;	
		//			}
		//		}

		//enter the data into the search field
		driver.findElement(By.name("search_text")).sendKeys("hjgjhgkj");

		//click on search button
		driver.findElement(By.name("search")).click();

		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"hjgjhgkj\"]")).click();

		//switch to parent window
		wLib.switchToWindow(driver, "SalesOrder&action");
		//		Set<String> parentwindow2= driver.getWindowHandles();
		//		for(String Str3:parentwindow2)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("SalesOrder&action"))
		//			{
		//				break;	
		//			}
		//		}

		//click on the organization lookup icon
		driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[4]")).click();

		//switch to the child window
		wLib.switchToWindow(driver, "Accounts&action");
		//		Set<String> childwindow3= driver.getWindowHandles();
		//		for(String Str3:childwindow3)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Accounts&action"))
		//			{
		//				break;	
		//			}
		//		}

		//enter the data into the text field
		driver.findElement(By.name("search_text")).sendKeys(ORGANIZATIONNAME);

		//click on search button
		driver.findElement(By.name("search")).click();

		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"PRIYA\"]")).click();

		//enter the data into the street text field
		driver.findElement(By.name("bill_street")).sendKeys(BILLSTREET);

		//enter the data into the pobox text field
		driver.findElement(By.name("bill_pobox")).sendKeys(POBOX);

		//enter the data into the city text field
		driver.findElement(By.name("bill_city")).sendKeys(BILLCITY);

		//enter the data into the state text field
		driver.findElement(By.name("bill_state")).sendKeys(STATE);

		//enter the data into the pin code text field
		driver.findElement(By.name("bill_code")).sendKeys(BILLCODE);

		//enter the data into the country text field
		driver.findElement(By.name("bill_country")).sendKeys(COUNTRY);

		//click on the billing address copy radio button
		driver.findElement(By.name("cpy")).click();

		//click on the product lookup icon
		driver.findElement(By.xpath("//img[@src=\"themes/images/products.gif\"]")).click();

		//switch to the child window
		wLib.switchToWindow(driver, "Products&action");
		//		Set<String> childwindow4= driver.getWindowHandles();
		//		for(String Str3:childwindow4)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Products&action"))
		//			{
		//				break;	
		//			}
		//		}


		//enter the data into the text field
		driver.findElement(By.name("search_text")).sendKeys(PRODUCTNAME);

		//click on search button
		driver.findElement(By.name("search")).click();

		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"iphone\"]")).click();

		//switch to the parent window
		wLib.switchToWindow(driver, "SalesOrder&action");
		//		Set<String> parentwindow3= driver.getWindowHandles();
		//		for(String Str3:parentwindow3)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("SalesOrder&action"))
		//			{
		//				break;	
		//			}
		//		}

		//enter the data into the quantity tex field
		driver.findElement(By.name("qty1")).sendKeys(QUANTITY);

		//clear the text field
		driver.findElement(By.name("listPrice1")).clear();

		//enter the data into the list price text field
		driver.findElement(By.name("listPrice1")).sendKeys(LISTPRICE);

		//click on the save button
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();

		//check whether sales order is created or not
		String oppertunity=driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
		if(oppertunity.contains("335 "))
		{
			System.out.println("sales order is created");
		}
		else
		{
			System.out.println("sales order  is not created");
		}

		//mouse hover on the logout icon
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		wLib.mouseOverOnElement(driver, ele2);
		//		Actions act2=new Actions(driver);
		//		act2.moveToElement(ele2).perform();
		//click on the sign out link
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();

	}

}
