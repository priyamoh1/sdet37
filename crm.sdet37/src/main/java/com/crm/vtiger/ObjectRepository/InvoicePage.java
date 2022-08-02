package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicePage 
{
	//initialization

		public InvoicePage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		//declaration 

		@FindBy(xpath ="//a[.=\"Organizations\"]")
		private WebElement organizationmodule;

		@FindBy(xpath ="//a[.=\\\"Documents\\\"]")
		private WebElement documentsmodule;

		@FindBy(xpath ="//a[.=\\\"Email\\\"]")
		private WebElement emailmodule;

		@FindBy(xpath ="//img[@src=\\\"themes/softed/images/menuDnArrow.gif\\\"]")
		private WebElement moredropdown;
}
