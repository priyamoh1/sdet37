package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage 
{
	//initialization

	public CampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//declaration 

	@FindBy(xpath ="//img[@src=\"themes/softed/images/btnL3Add.gif\"]")
	private WebElement newcampaign;

	// utilization
	
	public WebElement getOrganizationmodule()
	{
		return newcampaign;
	}
	
	public void clickonnewcampaign()
	{
		newcampaign.click();
	}




}
