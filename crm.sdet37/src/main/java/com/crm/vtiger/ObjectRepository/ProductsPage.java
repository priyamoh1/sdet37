package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage 
{
	//initialization

	public ProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaration 
	@FindBy(xpath ="//img[@src=\"themes/softed/images/btnL3Add.gif\"")
	private WebElement newvendor;

	public WebElement getNewvendor() 
	{
		return newvendor;
	}

}
