package VTIGERAPPL;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClickOnDocumentAndUploadDocumentTest
{

	public static void main(String[] args) throws AWTException, IOException
	{

		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		Properties property=new Properties();
		property.load(fis); 
		//get the properties from the propertyfile using getproperty
		String URL=property.getProperty("url");
		String USERNAME= property.getProperty("username");
		String PASSWORD=property.getProperty("password");
		//String BROWSER=property.getProperty("browser");
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
		driver.findElement(By.xpath("//a[.=\"Documents\"]")).click();
		driver.findElement(By.name("notes_title")).sendKeys("important");
		driver.findElement(By.xpath("//td[@id=\"cke_contents_notecontent\"]")).sendKeys("these are ver importatnt documents");
		driver.findElement(By.xpath("//input[@id=\"filename_I__\"]")).click();
		//upload the file
		StringSelection file=new StringSelection("\"C:\\Users\\-hp-\\Desktop\\SDET-37 MySQL cmds.txt\"");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_ENTER);
		//click on save button
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//check whether the document is created or not
		String document=driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();

		if(document.contains("important"))
		{
			System.out.println("document is created");
		}
		else
		{
			System.out.println("document is not created");
		}
		//logout from the vtiger app
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions act2=new Actions(driver);
		act2.moveToElement(ele2).perform();
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();

	}

}
