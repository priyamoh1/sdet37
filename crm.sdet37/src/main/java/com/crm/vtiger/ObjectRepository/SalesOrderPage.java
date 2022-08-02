package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesOrderPage
{
	//initialization

	public SalesOrderPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//declaration 

	@FindBy(xpath ="//img[@src=\\\"themes/softed/images/btnL3Add.gif\\\"]")
	private WebElement newsalesorder;


	public WebElement getNewsalesorder() 
	{
		return newsalesorder;
	}


}
