package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
private WebDriver driver;
private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By accPageHeaders = By.cssSelector("div#content h2");
	private By searchIcon = By.cssSelector("div#search button");
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil =  new ElementUtil(driver);	
	}
	
	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		System.out.println("Account page title :"+title);
		return title;
	}
	
	public String getAccountPageUrl() {
		String url = eleUtil.waitForUrl(AppConstants.LONG_DEFAULT_TIME_OUT, AppConstants.ACCOUNT_PAGE_URL_FRACTION);;
		System.out.println("Account page url :"+url);
		return url;
	}
	
	public boolean  isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public boolean  isSearchFieldExist() {
		return eleUtil.waitForElementVisible(searchField, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountSectionsHeaderList() {
		return eleUtil.getAllElementsTextList(accPageHeaders, AppConstants.SMALL_DEFAULT_TIME_OUT);
	}
	
	
	//common page actions:-
	public SearchResultsPage doSearch(String productName)
	{
		System.out.println("searching for : "+productName);
			eleUtil.doSendKeysWithWait(searchField, 10,productName);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		
	}
}
