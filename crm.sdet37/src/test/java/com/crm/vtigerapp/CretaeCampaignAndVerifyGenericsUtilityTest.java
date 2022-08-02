package com.crm.vtigerapp;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CretaeCampaignAndVerifyGenericsUtilityTest {

	public static void main(String[] args) throws IOException
	{
		JavaUtility jLib=new JavaUtility(); 
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		//String BROWSER = fLib.getpropertyKeyValue("browser");
		String URL = fLib.getpropertyKeyValue("url");
		String USERNAME = fLib.getpropertyKeyValue("username");	
		String PASSWORD = fLib.getpropertyKeyValue("password");

		//fetch the data from the excel sheet
		String CAMPAIGNNAME = eLib.getDataFromExcel("campaign", 1, 0);
		String PRODUCT = eLib.getDataFromExcel("product", 1, 0);
		//		String CUSTOMERNAME = eLib.getDataFromExcel("invoice", 1, 5);
		//		String ASSETNAME = eLib.getDataFromExcel("invoice", 1, 0);
		//		String INVOICENUMBER = eLib.getDataFromExcel("invoice", 1, 4);
		//		String PRODUCTNAME = eLib.getDataFromExcel("invoice", 1, 1);


		//		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		//		Properties property=new Properties();
		//		property.load(fis); 
		//		//get the properties from the property file using getproperty
		//		String URL=property.getProperty("url");
		//		String USERNAME= property.getProperty("username");
		//		String PASSWORD=property.getProperty("password");

		WebDriverManager.chromedriver().setup();

		//open browser
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//maximize the window
		driver.manage().window().maximize();
		//enter the URL
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//mouse over on the more module
		WebElement ele= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/menuDnArrow.gif\"]"));
		wLib.mouseOverOnElement(driver, ele);
		//		Actions act=new Actions(driver);
		//		act.moveToElement(ele).perform();

		//click on campaign
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Campaigns&action=index\"]")).click();
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		driver.findElement(By.name("campaignname")).sendKeys(CAMPAIGNNAME);
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/select.gif\"]")).click();

		//switch to child windows
		wLib.switchToWindow(driver, "Products&action");
		//		Set<String> childwindow= driver.getWindowHandles();
		//		for(String Str3:childwindow)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Products&action"))
		//			{
		//				break;	
		//			}
		//		}

		driver.findElement(By.name("search_text")).sendKeys(PRODUCT);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='iphone']")).click();

		//switch to parent window
		wLib.switchToWindow(driver, "Campaigns&action");
		//		Set<String> parentwindow= driver.getWindowHandles();
		//		for(String Str3:parentwindow)
		//		{
		//			driver.switchTo().window(Str3);
		//			String title=driver.getTitle();
		//			if(title.contains("Campaigns&action"))
		//			{
		//				break;	
		//			}
		//		}
		//save the campaign
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		String vendortext=driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();

		if(vendortext.contains(PRODUCT))
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
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();
	}

}
