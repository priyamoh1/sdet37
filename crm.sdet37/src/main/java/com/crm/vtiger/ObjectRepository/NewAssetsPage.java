package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class NewAssetsPage extends WebDriverUtility
{
	//initialization

		public NewAssetsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//declaration 

		@FindBy(name ="asset_no")
		private WebElement assetname;

		@FindBy(xpath ="(//img[@src=\\\"themes/softed/images/select.gif\\\"])[1]")
		private WebElement productlookup;
		
		@FindBy(xpath ="(//img[@src=\\\"themes/softed/images/select.gif\\\"])[2]")
		private WebElement invoicelookup;
		
		@FindBy(xpath ="(//img[@src=\\\"themes/softed/images/select.gif\\\"])[3]")
		private WebElement customernamelookup;

		@FindBy(name ="search_text")
		private WebElement searchtext;
		
		@FindBy(name ="search")
		private WebElement searchbutton;
		
		@FindBy(xpath ="//a[.=\"iphone\"]")
		private WebElement clickonproduct;
		
		@FindBy(xpath ="//a[.=\"iphoneinvoice\"]")
		private WebElement clickoninvoice;
		
		@FindBy(xpath ="//a[.=\\\"PRIYA\\\"]")
		private WebElement clickonorgname;
		
		@FindBy(xpath ="//span[@class=\\\"dvHeaderText\\\"]")
		private WebElement assetverify;
		
		@FindBy(xpath ="//input[@title=\"Save [Alt+S]\"")
		private WebElement savebutton;
		

		public WebElement getAssetverify() {
			return assetverify;
		}


		@FindBy(name ="serialnumber")
		private WebElement serialnum;
		
		@FindBy(name ="shippingtrackingnumber")
		private WebElement trackingnum;

		public WebElement getAssetname() {
			return assetname;
		}

		public WebElement getProductlookup() {
			return productlookup;
		}

		

		public WebElement getSerialnum() {
			return serialnum;
		}
		
		public void createnewasset(WebDriver driver,String assetname1,String productname1,String serialnumber,String invoicenum,String trackingnumber,String orgname)
		{
			assetname.sendKeys(assetname1);
			productlookup.click();
			switchToWindow(driver, "Products&action");
			searchtext.sendKeys(productname1);
			searchbutton.click();
			clickonproduct.click();
			switchToWindow(driver, "Assets&action");
			serialnum.sendKeys(serialnumber);
			invoicelookup.click();
			switchToWindow(driver, "Invoice&action");
			searchtext.sendKeys(invoicenum);
			searchbutton.click();
			clickoninvoice.click();
			switchToWindow(driver, "Assets&action");
			trackingnum.sendKeys(trackingnumber);
			customernamelookup.click();
			switchToWindow(driver, "Accounts&action");
			searchtext.sendKeys(orgname);
			searchbutton.click();
			clickonorgname.click();
			switchToWindow(driver, "Assets&action");
			savebutton.click();
			
					
		}
	
}
