package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	//initialization

	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaration 

	@FindBy(name ="user_name")
	private WebElement usernameedit;

	@FindBy(name ="user_password")
	private WebElement passwordedit;

	@FindBy(id ="submitButton")
	private WebElement subbutton;

	//utilization

	public WebElement getUsernameedit() 
	{
		return usernameedit;
	}

	public WebElement getPasswordedit()
	{
		return passwordedit;
	}

	public WebElement getSubbutton() 
	{
		return subbutton;
	}

	public void LogiontoApp(String username,String password)
	{
		usernameedit.sendKeys(username);
		passwordedit.sendKeys(password);
		subbutton.click();
	}

}
