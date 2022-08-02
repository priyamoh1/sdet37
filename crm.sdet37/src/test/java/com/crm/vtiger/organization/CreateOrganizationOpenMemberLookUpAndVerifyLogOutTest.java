package com.crm.vtiger.organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationOpenMemberLookUpAndVerifyLogOutTest {

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
		//click on organization module
		driver.findElement(By.xpath("//a[.=\"Organizations\"]")).click();
		//create new organization
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();	
		//enter the data into the organization name textfield
		driver.findElement(By.name("accountname")).sendKeys("SUZUKI");
		//handle industry dropdownlist
		WebElement industyr= driver.findElement(By.name("industry"));
		Select sel=new Select(industyr);
		sel.deselectByVisibleText("Communications");
		//handle type dropsownlist
		WebElement acc= driver.findElement(By.name("accounttype"));
		Select sel1=new Select(acc);
		sel1.deselectByVisibleText("Investor");
		//click on the save button
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//verify whether the organization is created or not
		String verify=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(verify.contains("SUZUKI"))
		{
			System.out.println("org is created");
		}
		else
		{
			System.out.println("org is not created");
		}
		//mouse hover on the logout icon
		WebElement ele2= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions act2=new Actions(driver);
		act2.moveToElement(ele2).perform();
		//click on the signout link
		driver.findElement(By.xpath("//a[@href=\"index.php?module=Users&action=Logout\"]")).click();


	}

}
