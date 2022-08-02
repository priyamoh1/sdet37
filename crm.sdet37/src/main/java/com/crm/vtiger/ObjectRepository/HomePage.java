package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility
{
	//initialization

	public HomePage(WebDriver driver)
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

	@FindBy(name ="Invoice")
	private WebElement invoicemodule;

	@FindBy(xpath ="//a[.=\\\"Opportunities\\\"]")
	private WebElement oppertunitiesmodule;

	@FindBy(name ="Sales Order")
	private WebElement salesordermodule;

	@FindBy(name ="Assets")
	private WebElement assetmodule;

	@FindBy(xpath ="//a[@href=\\\"index.php?module=Vendors&action=index\\\"]")
	private WebElement vendormodule;

	@FindBy(xpath ="//a[@href=\\\"index.php?module=Campaigns&action=index\\\"]")
	private WebElement campaignmodule;

	@FindBy(xpath ="//a[.=\"Contacts\"]")
	private WebElement contactsmodule;

	@FindBy(xpath ="//a[.=\"Products\"]")
	private WebElement productsmodule;

	@FindBy(xpath ="//a[@href=\"index.php?module=Users&action=Logout\"]")
	private WebElement logout;

	@FindBy(xpath ="//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement administrator;


	//utilization

	public WebElement getProductsmodule() {
		return productsmodule;
	}


	public WebElement getOrganizationmodule() {
		return organizationmodule;
	}

	public WebElement getDocumentsmodule() {
		return documentsmodule;
	}

	public WebElement getEmailmodule() {
		return emailmodule;
	}

	public WebElement getMoredropdown() {
		return moredropdown;
	}

	public WebElement getInvoicemodule() {
		return invoicemodule;
	}

	public WebElement getOppertunitiesmodule() {
		return oppertunitiesmodule;
	}

	public WebElement getSalesordermodule() {
		return salesordermodule;
	}

	public WebElement getAssetmodule() {
		return assetmodule;
	}

	public WebElement getVendormodule() {
		return vendormodule;
	}

	public WebElement getCampaignmodule() {
		return campaignmodule;
	}

	public WebElement getContactsmodule() {
		return contactsmodule;
	}


	public void logout(WebDriver driver)
	{
		mouseOverOnElement(driver, administrator);
		logout.click();
	}
	public void clickonassetmodule(WebDriver driver)
	{
		mouseOverOnElement(driver, moredropdown);
		assetmodule.click();
	}
	public void clickonvendormodule(WebDriver driver)
	{
		mouseOverOnElement(driver, moredropdown);
		vendormodule.click();	
	}
	public void clickonsaleorder(WebDriver driver)
	{
		mouseOverOnElement(driver, moredropdown);
		salesordermodule.click();
	}
	
	public void clickoncampaign()
	{
		mouseOverOnElement(null, moredropdown);
		campaignmodule.click();
	}

}
