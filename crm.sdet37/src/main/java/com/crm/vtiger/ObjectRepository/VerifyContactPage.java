package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyContactPage
{
	//initialization
	public VerifyContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaration 

	@FindBy(xpath ="//span[@class=\"dvHeaderText\"]")
	private WebElement verify;
	
	//utilization

	public WebElement getVerify() 
	{
		return verify;
	}

}
