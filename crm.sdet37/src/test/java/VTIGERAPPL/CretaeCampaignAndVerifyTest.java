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

public class CretaeCampaignAndVerifyTest {

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
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		WebElement ele= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/menuDnArrow.gif\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
		//click on campaign
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Campaigns&action=index\"]")).click();
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		driver.findElement(By.name("campaignname")).sendKeys("myphone");
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/select.gif\"]")).click();
		//switch to child windows
		Set<String> childwindow= driver.getWindowHandles();
		for(String Str3:childwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Products&action"))
			{
				break;	
			}
		}
		driver.findElement(By.name("search_text")).sendKeys("iphone");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='iphone']")).click();
		//switch to parent window
		Set<String> parentwindow= driver.getWindowHandles();
		for(String Str3:parentwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Campaigns&action"))
			{
				break;	
			}
		}
		//save the campaign
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		String vendortext=driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();

		if(vendortext.contains("myphone"))
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
