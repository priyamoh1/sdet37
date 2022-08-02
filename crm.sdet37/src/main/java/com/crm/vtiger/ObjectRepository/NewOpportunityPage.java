package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class NewOpportunityPage extends WebDriverUtility
{
	//initialization

		public NewOpportunityPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		//declaration 

		@FindBy(name ="potentialname")
		private WebElement potentialname;
		
		@FindBy(name ="opportunity_type")
		private WebElement typedropdown;
		
		@FindBy(name ="leadsource")
		private WebElement leaddropdown;
		
		@FindBy(xpath ="(//img[@src=\"themes/softed/images/select.gif\"])[1]")
		private WebElement clickonorglookup;
		
		@FindBy(xpath ="(//input[@title=\\\"Save [Alt+S]\\\"])[1]\"")
		private WebElement savebutton;
		
		@FindBy(name ="search_text")
		private WebElement searchtext;

		@FindBy(name ="search")
		private WebElement search;

		@FindBy(xpath ="//a[.=\"PRIYA\"]")
		private WebElement clickonsearchresult;
		
		public void opportunitytext(String potentialname1)
		{
			potentialname.sendKeys(potentialname1);
		}
		
		public void orglookup(WebDriver driver,String orgname)
		{
			clickonorglookup.click();
			switchToWindow(driver, "Accounts&action");
			searchtext.sendKeys(orgname);
			search.click();
			clickonsearchresult.click();
			switchToWindow(driver, "Potentials&action");
		}
		public void typedropdown(String type)
		{
			select(type, typedropdown);
		}
		
		public void leadsourcedd(String leaddd)
		{
			select(leaddd, leaddropdown);
		}
		 public void save()
		 {
			 savebutton.click();
		 }

}
