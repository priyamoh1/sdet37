package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityPage
{
	//initialization

	public OpportunityPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//declaration 

	@FindBy(xpath ="//img[@src=\"themes/softed/images/btnL3Add.gif\"]")
	private WebElement newopportunity;

	//utilization
	public WebElement getNewopportunity() {
		return newopportunity;
	}
	public void clickonnewopportunity()
	{
		newopportunity.click();
	}


}
