package com.crm.vtiger.automate;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {

	public static void main(String[] args)
	{
		WebDriverManager.chromedriver().setup();
		//open browser
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//maximize the window
		driver.manage().window().maximize();
		//enter the url
		driver.get("http://localhost:8084/");
		//enter username
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		//enter password
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		//sign in to the welcome page
		driver.findElement(By.xpath("//button[.=\"Sign in\"]")).click();
		//click in the projects button or link
		driver.findElement(By.xpath("//a[.=\"Projects\"]")).click();
		List<WebElement> lists= driver.findElements(By.xpath("//tr/../..//td"));
		System.out.println(lists);
		//System.out.println(lists.get);
		//System.out.println(str);
		//System.out.println(values);
//		if(str.equalsIgnoreCase(values))
//		{
//			System.out.println("data is created");
//			
//		}
//		else
//			  System.out.println("not created");
		
	}

}
