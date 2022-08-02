package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage 
{
	//initialization

		public ContactPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaration 

		@FindBy(xpath ="//img[@alt=\"Create Contact...\"]")
		private WebElement createcontact;

		public WebElement getCreatecontact() 
		{
			return createcontact;
		}
		
		
}
