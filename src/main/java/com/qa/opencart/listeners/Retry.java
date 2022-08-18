package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
private int count =0;
private static int maxTry = 3;
	@Override
	public boolean retry(ITestResult iTestResult) {
		if(!iTestResult.isSuccess()) { //check if test not succeed
			if(count< maxTry) { // check if max count retry is reached
				count++;
				iTestResult.setStatus(ITestResult.FAILURE); //mark test as failed
				return true; //tells testNG to re-run the test
			}else {
				iTestResult.setStatus(ITestResult.FAILURE); //If maxCount reached, test marked as failed
			}
		}else {
			iTestResult.setStatus(ITestResult.SUCCESS); //If test passes, TestNG marks it as passed
		}
		return false;
	}

}
