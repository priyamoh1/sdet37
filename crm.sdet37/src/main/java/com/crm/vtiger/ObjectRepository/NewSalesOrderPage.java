package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class NewSalesOrderPage extends WebDriverUtility
{
	//initialization

	public NewSalesOrderPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//declaration 

	@FindBy(name ="subject")
	private WebElement subjecttextfield;

	@FindBy(xpath ="(//img[@src=\"themes/softed/images/select.gif\"])[1]")
	private WebElement opportunitylookup;

	@FindBy(xpath ="(//img[@src=\"themes/softed/images/select.gif\"])[2]")
	private WebElement quotelkp;

	@FindBy(xpath ="(//img[@src=\"themes/softed/images/select.gif\"])[4]")
	private WebElement organizationlkp;

	@FindBy(xpath ="//img[@src=\\\"themes/images/products.gif\\\"]")
	private WebElement productlkp;

	@FindBy(xpath ="(//input[@title=\\\"Save [Alt+S]\\\"])[1]")
	private WebElement savebutton;



	@FindBy(name ="search_text")
	private WebElement searchtext;

	@FindBy(name ="search")
	private WebElement search;

	@FindBy(xpath ="//a[.=\"demo\"]")
	private WebElement clickonsearchresult;

	@FindBy(xpath ="//a[.=\"hjgjhgkj\"]")
	private WebElement clickonqoutesearchresult;

	@FindBy(xpath ="//a[.=\"PRIYA\"]")
	private WebElement clickonorgname;

	@FindBy(xpath ="//a[.=\"iphone\"]")
	private WebElement clickonproduct;

	@FindBy(name ="bill_street")
	private WebElement street;

	@FindBy(name ="bill_pobox")
	private WebElement pobox;

	@FindBy(name ="bill_city")
	private WebElement billcity;

	@FindBy(name ="bill_state")
	private WebElement billstate;

	@FindBy(name ="bill_code")
	private WebElement billcode;

	@FindBy(name ="bill_country")
	private WebElement billcountry;

	@FindBy(name ="cpy")
	private WebElement clickoncopyadd;

	@FindBy(name ="qty1")
	private WebElement quantity;

	@FindBy(name ="listPrice1")
	private WebElement clearlistprice;

	@FindBy(name ="listPrice1")
	private WebElement listprice;





	//utilization
	public WebElement getClickonsearchresult() {
		return clickonsearchresult;
	}
	public void setClickonsearchresult(WebElement clickonsearchresult) {
		this.clickonsearchresult = clickonsearchresult;
	}
	public WebElement getSearchtext() {
		return searchtext;
	}
	public WebElement getSearch() {
		return search;
	}
	public WebElement getSubjecttextfield() {
		return subjecttextfield;
	}
	public WebElement getOpportunitylookup() {
		return opportunitylookup;
	}

	public void subjecttextfield(String subjectname)
	{
		subjecttextfield.sendKeys(subjectname);
	}
	public void oppportunitylkp(WebDriver driver,String demo)
	{
		opportunitylookup.click();
		switchToWindow(driver, "Potentials&action");
		searchtext.sendKeys(demo);
		search.click();
		clickonsearchresult.click();
		switchToWindow(driver, "SalesOrder&action");				
	}

	public void quotelookup(WebDriver driver,String quotenum)
	{
		quotelkp.click();
		switchToWindow(driver, "Contacts&action");
		searchtext.sendKeys(quotenum);
		search.click();
		clickonqoutesearchresult.click();
		switchToWindow(driver, "SalesOrder&action");
	}

	public void organizationlkp(WebDriver driver,String orgname)
	{
		organizationlkp.click();
		switchToWindow(driver, "Accounts&action");
		searchtext.sendKeys(orgname);
		search.click();
		clickonorgname.click();
		switchToWindow(driver, "SalesOrder&action");	
	}

	public void enteraddres(String billstreet,String pobox1,String city,String state,String code,String country)
	{
		street.sendKeys(billstreet);
		pobox.sendKeys(pobox1);
		billcity.sendKeys(city);
		billstate.sendKeys(state);
		billcode.sendKeys(code);
		billcountry.sendKeys(country);
		clickoncopyadd.click();
	}

	public void clickonproductlkp(WebDriver driver,String productname)
	{
		productlkp.click();
		switchToWindow(driver, "Products&action");
		searchtext.sendKeys(productname);
		search.click();
		clickonproduct.click();
		switchToWindow(driver, "SalesOrder&action");	
	}

	public void quantity(String pquantity)
	{
		quantity.sendKeys(pquantity);
	}

	public void enterlistprice(String plistprice)
	{
		clearlistprice.clear();
		listprice.sendKeys(plistprice);
	}
	public void clickonsavebutton()
	{
		savebutton.click();
	}
}
