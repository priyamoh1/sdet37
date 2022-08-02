package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage 
{
	//initialization

		public ProductInfoPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaration 
		@FindBy(xpath ="//span[@class=\"dvHeaderText\"]")
		private WebElement productverify;

		public WebElement getProductverify() 
		{
			return productverify;
		}


}
