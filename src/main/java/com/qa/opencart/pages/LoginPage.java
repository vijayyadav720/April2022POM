package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
   private WebDriver driver;
   private ElementUtil eleUtil;
	//1 By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	
	private By registerLink = By.linkText("Register");
	  
	//2 Page constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3 page actions:-
	
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		System.out.println("Login page title :"+title);
		return title;
	}
	
	@Step("Getting login page url")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForUrl(AppConstants.LONG_DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION);
		System.out.println("login page url :"+url);
		return url;
	}
	
	@Step("is Forgot Passsword link exists")
	public boolean isForgotPwddLinkExist() {
		return eleUtil.waitForElementPresence(forgotPwdLink, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	@Step("Login with username: {0} and password : {1}")
	public AccountsPage doLogin(String username, String password) {
		System.out.println("app creds: " + username + ":" + password);
		eleUtil.doSendKeysWithWait(emailId, AppConstants.MEDIUM_DEFAULT_TIME_OUT, username);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doClick(loginBtn);
		//return eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE,AppConstants.SMALL_DEFAULT_TIME_OUT);
		return new AccountsPage(driver);
	}
	
	@Step("perform search for the product: {0}")
	public SearchResultsPage performSearch(String name) {
		AccountsPage accPage = new AccountsPage(driver);
		return  accPage.doSearch(name);
		
	}
	
	@Step("navigating to register page")
	public RegisterPage goToRegisterPage() {
			eleUtil.doClick(registerLink);
			
		return new RegisterPage(driver);
	}
}
















