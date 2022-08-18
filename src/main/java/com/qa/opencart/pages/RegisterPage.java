package com.qa.opencart.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeNo = By.cssSelector("label.radio-inline input[value='1']");
	private By subscribeYes = By.cssSelector("label.radio-inline input[value='0']");
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value = 'Continue']");
	
	private By successMessg = By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean userRegistration(String firstName, String lastName ,String email,  String telephone , String password , String subscribe) {
		eleUtil.doSendKeysWithWait(this.firstName, AppConstants.MEDIUM_DEFAULT_TIME_OUT, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password,password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.toLowerCase().equalsIgnoreCase("yes"))
			eleUtil.doClick(this.subscribeYes);
		else
			eleUtil.doClick(this.subscribeNo);
		eleUtil.doClick(this.agreeCheckBox);
		eleUtil.doClick(this.continueButton);
	
		String actSuccessMesg  = eleUtil.waitForElementVisible(this.successMessg,AppConstants.MEDIUM_DEFAULT_TIME_OUT)
													.getText();
		
		if(actSuccessMesg.equalsIgnoreCase(AppConstants.REGISTER_SUCCESS_MESSG)) {
			return true;
		}
		
		return false;
	}
	
	public void goToRegisterPage()
	{
		eleUtil.doClick(this.logoutLink);
		eleUtil.doClick(this.registerLink);
	}
	
}











