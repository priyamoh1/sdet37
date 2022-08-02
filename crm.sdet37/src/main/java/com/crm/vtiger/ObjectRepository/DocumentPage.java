package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentPage
{
	//initialization

		public DocumentPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		//declaration 

		@FindBy(xpath ="//a[.=\"Documents\"]")
		private WebElement newdocumentsmodule;

		@FindBy(xpath ="//a[.=\\\"Email\\\"]")
		private WebElement emailmodule;

		@FindBy(xpath ="//img[@src=\\\"themes/softed/images/menuDnArrow.gif\\\"]")
		private WebElement moredropdown;

		public WebElement getDocumentsmodule() {
			return newdocumentsmodule;
		}

		public WebElement getEmailmodule() {
			return emailmodule;
		}

		public WebElement getMoredropdown() {
			return moredropdown;
		}
		
}
