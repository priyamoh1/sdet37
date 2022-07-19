package CONTACT;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactnameAndChooseFromOrgAndVerifyTest {

	public static void main(String[] args) throws IOException
	{
		WebDriver driver=null;
		//fetch the common data from properties file
		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		Properties property=new Properties();
		property.load(fis);

		//get the properties from the propertyfile using getproperty
		String URL=property.getProperty("url");
		String USERNAME= property.getProperty("username");
		String PASSWORD=property.getProperty("password");
		String BROWSER=property.getProperty("browser");


		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//open browser
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//open browser
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		FileInputStream fis1=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\data.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh= wb.getSheet("organization");
		org.apache.poi.ss.usermodel.Row r= sh.getRow(1);
		Cell C= r.getCell(2);
		String CONTACTNAME=C.toString();



		//WebDriverManager.chromedriver().setup();
		//open browser
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//maximize the window
		driver.manage().window().maximize();
		//enter the URL
		driver.get(URL);
		//enter the username
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//enter the password
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//click on the sign in button;
		driver.findElement(By.id("submitButton")).click();
		//click on the contact module button
		driver.findElement(By.xpath("//a[.=\"Contacts\"]")).click();
		//click on the new contact button
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		//handle first name dropdownlist
		WebElement ty= driver.findElement(By.name("salutationtype"));
		Select sel=new Select(ty);
		sel.selectByVisibleText("Mr.");
		//enter the data into the firstname textfield
		driver.findElement(By.name("firstname")).sendKeys(CONTACTNAME);
		//enter the data into the last name textfield
		driver.findElement(By.name("lastname")).sendKeys("FERRARI1");
		//click on the organiuztion lookup icon
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/select.gif\"]")).click();
		//switch to the child windows
		Set<String> win = driver.getWindowHandles();
		for(String str:win)
		{
			driver.switchTo().window(str);
			String title=driver.getTitle();
			if(title.contains("Accounts&action"))
			{
				break;
			}
		}
		//enter the data into the search text field
		driver.findElement(By.name("search_text")).sendKeys("PRIYA");
		//click on the search button
		driver.findElement(By.name("search")).click();
		//click on the desired result
		driver.findElement(By.xpath("//a[.=\"PRIYA\"]")).click();
		//switch to the parent windows
		Set<String> parent = driver.getWindowHandles();
		for(String str:parent)
		{
			driver.switchTo().window(str);
			String title=driver.getTitle();
			if(title.contains("Contacts&action"))
			{
				break;
			}
		}
		//click on the save button
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//verify whether the contact is created or not
		String verify=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(verify.contains("FERRARI1"))
		{
			System.out.println("conatct is created");
		}
		else
		{
			System.out.println("contact is not created");
		}
		//mouse hover on the logout icon
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions act2=new Actions(driver);
		act2.moveToElement(ele2).perform();
		//click on the signout link
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();
	}

}
