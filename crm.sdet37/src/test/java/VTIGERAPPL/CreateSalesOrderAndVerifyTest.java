package VTIGERAPPL;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSalesOrderAndVerifyTest {

	public static void main(String[] args) throws IOException
	{

		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		Properties property=new Properties();
		property.load(fis); 
		//get the properties from the propertyfile using getproperty
		String URL=property.getProperty("url");
		String USERNAME= property.getProperty("username");
		String PASSWORD=property.getProperty("password");

		WebDriverManager.chromedriver().setup();
		//open browser
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//maximize the window
		driver.manage().window().maximize();
		//enter the URL
		driver.get(URL);
		//enter the username
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
		Set<String> childwindow= driver.getWindowHandles();
		for(String Str3:childwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Potentials&action"))
			{
				break;	
			}
		}
		//enter data into search textfield
		driver.findElement(By.name("search_text")).sendKeys("demo");
		//click on search button
		driver.findElement(By.name("search")).click();
		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"demo\"]")).click();
		//switch to parent window
		Set<String> parentwindow= driver.getWindowHandles();
		for(String Str3:parentwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Potentials&action"))
			{
				break;	
			}
		}
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
		Set<String> childwindow2= driver.getWindowHandles();
		for(String Str3:childwindow2)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Contacts&action"))
			{
				break;	
			}
		}
		//enter the data into the search field
		driver.findElement(By.name("search_text")).sendKeys("hjgjhgkj");
		//click on search button
		driver.findElement(By.name("search")).click();
		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"hjgjhgkj\"]")).click();
		//switch to parent window
		Set<String> parentwindow2= driver.getWindowHandles();
		for(String Str3:parentwindow2)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("SalesOrder&action"))
			{
				break;	
			}
		}
		//clcik on the organization lookup icon
		driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[4]")).click();
		//switch to the child window
		Set<String> childwindow3= driver.getWindowHandles();
		for(String Str3:childwindow3)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Accounts&action"))
			{
				break;	
			}
		}
		//enter the data into the textfield
		driver.findElement(By.name("search_text")).sendKeys("PRIYA");
		//click on search button
		driver.findElement(By.name("search")).click();
		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"PRIYA\"]")).click();
		//enter the data into the street textfield
		driver.findElement(By.name("bill_street")).sendKeys("bengaluru");
		//enter the data into the pobox textfield
		driver.findElement(By.name("bill_pobox")).sendKeys("bengaluru");
		//enter the data into the city textfield
		driver.findElement(By.name("bill_city")).sendKeys("bengaluru");
		//enter the data into the state textfield
		driver.findElement(By.name("bill_state")).sendKeys("bengaluru");
		//enter the data into the pin code textfield
		driver.findElement(By.name("bill_code")).sendKeys("123456");
		//enter the data into the country textfield
		driver.findElement(By.name("bill_country")).sendKeys("INDIA");
		//click on the billing address copy radio button
		driver.findElement(By.name("cpy")).click();
		//click on the product lookup icon
		driver.findElement(By.xpath("//img[@src=\"themes/images/products.gif\"]")).click();
		//switch to the child window
		Set<String> childwindow4= driver.getWindowHandles();
		for(String Str3:childwindow4)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Products&action"))
			{
				break;	
			}
		}
		//enter the data into the textfield
		driver.findElement(By.name("search_text")).sendKeys("iphone");
		//click on search button
		driver.findElement(By.name("search")).click();
		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"iphone\"]")).click();
		//swith to the parent window
		Set<String> parentwindow3= driver.getWindowHandles();
		for(String Str3:parentwindow3)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("SalesOrder&action"))
			{
				break;	
			}
		}
		//enter the data into the quantity texfield
		driver.findElement(By.name("qty1")).sendKeys("100");
		//clear the textfield
		driver.findElement(By.name("listPrice1")).clear();
		//enter the data into the list price textfield
		driver.findElement(By.name("listPrice1")).sendKeys("100");
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
		Actions act2=new Actions(driver);
		act2.moveToElement(ele2).perform();
		//click on the signout link
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();

	}

}
