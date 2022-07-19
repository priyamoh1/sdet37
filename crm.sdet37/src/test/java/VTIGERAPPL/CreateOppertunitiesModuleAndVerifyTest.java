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

public class CreateOppertunitiesModuleAndVerifyTest {

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
		//click on opportunities module
		driver.findElement(By.xpath("//a[.=\"Opportunities\"]")).click();
		//create new opportunities
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		//enter into opportunity textfield
		driver.findElement(By.name("potentialname")).sendKeys("Demo");
		//click on the org lookup icon
		driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[1]")).click();
		//switch to child window
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
		//enter in the search textfield
		driver.findElement(By.name("search_text")).sendKeys("PRIYA");
		//click on search button
		driver.findElement(By.name("search")).click();
		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"PRIYA\"]")).click();
		//switch to the parent window
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
		//select from type dropsown
		WebElement ele= driver.findElement(By.name("opportunity_type"));
		Select sel =new Select(ele);
		sel.selectByVisibleText("Existing Business");
		//select from lead source dropdown
		WebElement ele1= driver.findElement(By.name("leadsource"));
		Select sel1 =new Select(ele1);
		sel1.selectByVisibleText("Employee");
		//driver.findElement(By.xpath("src=\"themes/softed/images/btnL3Calendar.gif\"")).click();
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();
		//check whetaher oppertunity is created or not
		String oppertunity=driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
		if(oppertunity.contains("demo"))
		{
			System.out.println("campaign is created");
		}
		else
		{
			System.out.println("campaign is not created");
		}
		//logout from the vtiger app
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions act2=new Actions(driver);
		act2.moveToElement(ele2).perform();
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();	
	}

}
