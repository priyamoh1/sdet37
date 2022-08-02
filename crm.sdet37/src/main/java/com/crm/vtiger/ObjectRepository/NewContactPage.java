package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class NewContactPage extends WebDriverUtility
{
	//initialization

		public NewContactPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaration 
		@FindBy(name ="salutationtype")
		private WebElement salutation;

		@FindBy(name ="firstname")
		private WebElement fisrtname;

		@FindBy(name ="lastname")
		private WebElement lastname;

		@FindBy(xpath ="//input[@title=\"Save [Alt+S]\"]")
		private WebElement savebutton;
		
		@FindBy(xpath ="//img[@src=\"themes/softed/images/select.gif\"]")
		private WebElement orglookup;
		
		@FindBy(name ="search_text")
		private WebElement searchtext;
		
		@FindBy(name ="search")
		private WebElement searchbutton;
		
		@FindBy(xpath ="//a[.=\"PRIYA\"]")
		private WebElement selectorgname;
		
		//utilization
		public WebElement getSalutation() {
			return salutation;
		}

		public WebElement getSearchtext() {
			return searchtext;
		}

		public WebElement getSearchbutton() {
			return searchbutton;
		}

		public WebElement getSelectorgname() {
			return selectorgname;
		}

		public WebElement getOrglookup() {
			return orglookup;
		}

		public WebElement getFisrtname() {
			return fisrtname;
		}

		public WebElement getLastname() {
			return lastname;
		}

		public WebElement getSavebutton() {
			return savebutton;
		}
		
		public void createnewcontactandsave(String salutationtype,String firstname,String lastname)
		{
			select(salutationtype, salutation);
			getFisrtname().sendKeys(firstname);
			getLastname().sendKeys(lastname);
			getSavebutton().click();		
		}
		
		public void createnewcontactwithorgnamesave(WebDriver driver, String salutationtype,String firstname,String lastname,String orgname)
		{
			select(salutationtype, salutation);
			getFisrtname().sendKeys(firstname);
			getLastname().sendKeys(lastname);
			getOrglookup().click();
			switchToWindow(driver, "Accounts&action");
			getSearchtext().sendKeys(orgname);
			getSearchbutton().click();
			getSelectorgname().click();
			switchToWindow(driver, "Contacts&action");
			getSavebutton().click();		
		}
		
}
