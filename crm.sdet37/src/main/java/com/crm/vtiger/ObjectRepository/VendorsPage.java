package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsPage 
{
	//initialization

		public VendorsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaration 
		@FindBy(xpath ="//img[@src=\"themes/softed/images/btnL3Add.gif\"")
		private WebElement newvendor;
		
        // utilization
		public WebElement getNewvendor() 
		{
			return newvendor;
		}
		
}
