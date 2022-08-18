package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
  
@Epic("Epic -100: Design the login page feature for open cart application")
@Story("US -101: Design login page features with login , forgot password, title , url")
public class LoginPageTest  extends BaseTest{
		@Test
		@Description("TC_01login page Title Test")
		@Severity(SeverityLevel.NORMAL)
		public void loginPageTitleTest() {
			String actTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		
		}
	
		@Test 
		@Description("TC_02: Login Page Url Test")
		@Severity(SeverityLevel.NORMAL)
		public void loginPageUrlTest() {
			String actUrl = loginPage.getLoginPageUrl();
			Assert.assertEquals(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), true);
		}
		
		@Test
		@Description("TC_03: verify forgot password link exists on the login page")
		@Severity(SeverityLevel.CRITICAL)
		public void forgotPwdLinkExistTest() {
			Assert.assertEquals(loginPage.isForgotPwddLinkExist(), true);
		}
		
		@Test
		@Description("TC_04: verify user is able to login with correct user name and password")
		@Severity(SeverityLevel.BLOCKER)
		public void loginTest() {
			
			//used for login page details but it will check the title for the account page bcz it will go to account page once we 
			//login and it will return the account class page object
			accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}
		
		@Test(enabled=false)
		public void loginPageFooterTest() {
			System.out.println("Login Page Footer Test");
		}
		
		

}
