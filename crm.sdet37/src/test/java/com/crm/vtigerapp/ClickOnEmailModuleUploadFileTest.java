package com.crm.vtigerapp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

public class ClickOnEmailModuleUploadFileTest {

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
		//enter the username
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//enter the password
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//click on sign in button
		driver.findElement(By.id("submitButton")).click();
		//click on email module
		driver.findElement(By.xpath("//a[.=\"Email\"]")).click();
		//compose new email
		driver.findElement(By.xpath("//a[.=\"Compose\"]")).click();
		//switch to child window
		Set<String> childwindow= driver.getWindowHandles();
		for(String Str3:childwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Emails&action"))
			{
				break;	
			}
		}
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/select.gif\"]")).click();
		//switch to sub child window
		Set<String> subchildwindow= driver.getWindowHandles();
		for(String Str3:subchildwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Products&action"))
			{
				break;	
			}
		}
		//enter in the search textfield
		driver.findElement(By.name("search_text")).sendKeys("hjgjhgkj");
		//click on search button
		driver.findElement(By.name("search")).click();
		//click on the contact 
		driver.findElement(By.xpath("//a[.=\"klkj hjgjhgkj\"]")).click();
		//enter in subject textfield
		driver.findElement(By.name("subject")).sendKeys("email");
		//upload file
		driver.findElement(By.id("my_file_element")).click();
		StringSelection file=new StringSelection("\"C:\\Users\\-hp-\\Desktop\\SDET-37 MySQL cmds.txt\"");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_ENTER);
		//write in description
		driver.findElement(By.xpath("//td[@id=\"cke_contents_description\"]")).sendKeys("documents will be sent too with the message");
		//switch to parent windows
		Set<String> parentwindow= driver.getWindowHandles();
		for(String Str3:parentwindow)
		{
			driver.switchTo().window(Str3);
			String title=driver.getTitle();
			if(title.contains("Emails&action"))
			{
				break;	
			}
		}
		//check if email is present or not
		String document=driver.findElement(By.xpath("//td[.=\"email \"]")).getText();	
		if(document.contains("email "))
		{
			System.out.println("document is created");
		}
		else
		{
			System.out.println("document is not created");
		}
		//logout from vtiger
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions act2=new Actions(driver);
		act2.moveToElement(ele2).perform();
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();	
	}

}
