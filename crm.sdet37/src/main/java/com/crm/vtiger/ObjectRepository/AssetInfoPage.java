package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssetInfoPage 
{
	//initialization

	public AssetInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaration 
	@FindBy(xpath ="//span[@class=\\\"dvHeaderText\\\"]")
	private WebElement assetverify;
	
	//utilization
	public WebElement getAssetverify() {
		return assetverify;
	}
}
