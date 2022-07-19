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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationAndVerifyTest {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		FileInputStream fis1=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		Properties property=new Properties();
		property.load(fis1); 
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
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		//click on organization module
		driver.findElement(By.xpath("//a[.=\"Organizations\"]")).click();
		//create new organization
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys("PRIYA10");
		WebElement list= driver.findElement(By.name("industry"));
		Select sel=new Select(list);
		sel.selectByVisibleText("Electronics");
		WebElement list1= driver.findElement(By.name("accounttype"));
		Select sel1=new Select(list1);
		sel1.selectByVisibleText("Customer");
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		Thread.sleep(1000);
		//confirm whether the organization name is present or not
		String confirm= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(confirm.contains("PRIYA"))
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
		Select sel2=new Select(list2);
		sel2.deselectByVisibleText("Mr.");
		driver.findElement(By.name("firstname")).sendKeys("pintu");
		driver.findElement(By.name("firstname")).sendKeys("moh");
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/select.gif\"]")).click();
		//switch to child windows
		Set<String> childwindow= driver.getWindowHandles();
		for(String Str3:childwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Accounts&action"))
			{
				break;	
			}
		}
		driver.findElement(By.name("search_text")).sendKeys("PRIYA10");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='PRIYA10']")).click();
		//switch to parent windows
		Set<String> parentwindow= driver.getWindowHandles();
		for(String Str3:parentwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Contacts&action"))
			{
				break;	
			}
		}
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
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();

	}

}
