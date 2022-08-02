package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class NewCampaignPage extends WebDriverUtility
{
	//initialization

		public NewCampaignPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		//declaration 

		@FindBy(name ="search_text")
		private WebElement searchtext;

		@FindBy(name ="search")
		private WebElement search;

		@FindBy(xpath ="//a[.=\"iphone\"]")
		private WebElement clickonsearchresult;
		
		@FindBy(name ="campaignname")
		private WebElement campaignnametextfield;

		@FindBy(xpath ="//a[.=\\\"Documents\\\"]")
		private WebElement documentsmodule;

		@FindBy(xpath ="//a[.=\\\"Email\\\"]")
		private WebElement emailmodule;

		@FindBy(xpath ="//img[@src=\\\"themes/softed/images/menuDnArrow.gif\\\"]")
		private WebElement moredropdown;
		
		@FindBy(xpath ="//img[@src=\"themes/softed/images/select.gif\"]")
		private WebElement productlookup;
		
		@FindBy(xpath ="//input[@title=\\\"Save [Alt+S]\\\"]")
		private WebElement savebutton;
		
		
		
		//utilization

		public WebElement getCampaignnametextfield() {
			return campaignnametextfield;
		}
		

		public void campaigntextfield(String campaignname)
		{
			campaignnametextfield.sendKeys(campaignname);
		}
		
		public void productlookup(String productname,WebDriver driver)
		{
			productlookup.click();
			switchToWindow(driver, "Products&action");
			searchtext.sendKeys(productname);
			search.click();
			clickonsearchresult.click();
			switchToWindow(driver, "Campaigns&action");
		}
		
		public void clickonsavebutton()
		{
			savebutton.click();
		}
		
		
}
