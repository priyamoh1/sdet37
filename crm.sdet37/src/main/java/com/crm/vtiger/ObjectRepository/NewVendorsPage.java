package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewVendorsPage 
{
	//initialization

		public NewVendorsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		//declaration 

		@FindBy(name ="vendorname")
		private WebElement vendorname;
		
		@FindBy(xpath ="//span[@class=\"lvtHeaderText\"]")
		private WebElement verifyvendor;
		
		//utilization
		public WebElement getVendorname() 
		{
			return vendorname;
		}

		public WebElement getVerifyvendor() 
		{
			return verifyvendor;
		}

		
}
