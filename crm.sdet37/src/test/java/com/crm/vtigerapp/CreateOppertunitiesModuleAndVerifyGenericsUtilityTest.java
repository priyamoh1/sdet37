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

public class CreateOppertunitiesModuleAndVerifyGenericsUtilityTest {

	public static void main(String[] args) throws IOException 
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		//get the properties from the propertyfile using getproperty
		String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");

		//		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		//		Properties property=new Properties();
		//		property.load(fis); 
		//		String URL=property.getProperty("url");
		//		String USERNAME= property.getProperty("username");
		//		String PASSWORD=property.getProperty("password");

		//get the datta from the excel sheet
		String TYPE = eLib.getDataFromExcel("opportunities", 1, 3);
		String LEADSOURCE = eLib.getDataFromExcel("opportunities", 4, 4);
		String CUSTOMERNAME = eLib.getDataFromExcel("invoice", 1, 5);
		String ASSETNAME = eLib.getDataFromExcel("invoice", 1, 0);
		String INVOICENUMBER = eLib.getDataFromExcel("invoice", 1, 4);
		String PRODUCTNAME = eLib.getDataFromExcel("invoice", 1, 1);

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
		//enter the username
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//enter the password
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//click on sign in button
		driver.findElement(By.id("submitButton")).click();
		//click on opportunities module
		driver.findElement(By.xpath("//a[.=\"Opportunities\"]")).click();
		//create new opportunities
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		//enter into opportunity text field
		driver.findElement(By.name("potentialname")).sendKeys("Demo");
		//click on the org lookup icon
		driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[1]")).click();

		//switch to child window

		wLib.switchToWindow(driver, "Accounts&action");
		//		Set<String> child window= driver.getWindowHandles();
		//		for(String Str3:child window)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Accounts&action"))
		//			{
		//				break;	
		//			}
		//		}

		//enter in the search textfield
		driver.findElement(By.name("search_text")).sendKeys("PRIYA");
		//click on search button
		driver.findElement(By.name("search")).click();
		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"PRIYA\"]")).click();

		//switch to the parent window
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


		//select from type dropdown

		WebElement ele= driver.findElement(By.name("opportunity_type"));
		wLib.select(TYPE, ele);
		//		Select sel =new Select(ele);
		//		sel.selectByVisibleText(TYPE);

		//select from lead source drop down
		WebElement ele1= driver.findElement(By.name("leadsource"));
		wLib.select(LEADSOURCE, ele1);
		//		Select sel1 =new Select(ele1);
		//		sel1.selectByVisibleText(LEADSOURCE);
		//driver.findElement(By.xpath("src=\"themes/softed/images/btnL3Calendar.gif\"")).click();
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();

		//check whether opportunity is created or not
		String oppertunity=driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
		if(oppertunity.contains("demo"))
		{
			System.out.println("campaign is created");
		}
		else
		{
			System.out.println("campaign is not created");
		}

		//logout from the v-tiger application
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		wLib.mouseOverOnElement(driver, ele2);
		//		Actions act2=new Actions(driver);
		//		act2.moveToElement(ele2).perform();
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();	
	}

}
