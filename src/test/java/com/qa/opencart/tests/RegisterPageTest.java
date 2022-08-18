package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		regPage = loginPage.goToRegisterPage();
	}
	
	public static String randomEmail() {
		Random random = new Random();
		String email = "automation"+random.nextInt(500)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegExcelData() {
		Object regData[][] = ExcelUtils.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	@Test
	public void userRegTest(String firstName, String lastName, String phone, String password, String subscribe) {
		boolean succesFlag = regPage.userRegistration(firstName, lastName, randomEmail(), phone, password, subscribe);
		regPage.goToRegisterPage();
		Assert.assertTrue(succesFlag);
	}
	
}
