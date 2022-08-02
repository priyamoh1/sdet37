package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage 
{
	//initialization
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaration 

	@FindBy(xpath ="//img[@alt=\"Create Organization...\"]")
	private WebElement neworganization;

	//utilization

	public WebElement getNeworganization() 
	{
		return neworganization;
	}



}
