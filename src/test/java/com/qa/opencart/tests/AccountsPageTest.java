package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic -200: Design the account page feature for open cart application")
@Story("US -201: Design accunt page features")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		
		//help us to load some basic features before our class will do any test
		//or it is getting account page class object from doLogin method
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertEquals(accPage.isLogoutLinkExist(),true);
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actSecHeadersList = accPage.getAccountSectionsHeaderList();
		System.out.println("======actual headers======"+ actSecHeadersList);
		Collections.sort(AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
		Collections.sort(actSecHeadersList);
		Assert.assertEquals(actSecHeadersList, AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
	}
	
	
	
}











