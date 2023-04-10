package com.wa.ModifiedOnline.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.wa.ModifiedOnline.BaseTestCases.BaseTest;

public class LoginTest extends BaseTest {

	
	@Test
	public void validLoginTest() {
		navBar = loginpage.login("kirankatkar3317@gmail.com", "Kiran@3317");
		boolean result = navBar.verifyJobLinkDisplayed();
		Assert.assertTrue(result);
		boolean result1 = navBar.accountLogoCheck();
		Assert.assertTrue(result1);
	}

	@Test
	public void invalidLoginTest() {

	//	loginpage.loginwithEmail();
		navBar = loginpage.login("kirankatkar3318@gmail.com", "Kiran@3318");
		boolean result = navBar.accountLogoCheck();
		Assert.assertFalse(result);
	}

	@AfterMethod
	public void afterMethod() {
		if (navBar.accountLogoCheck() == true) {
			navBar.clickOnLogout();
			navBar.gotoLogin();
		}

	}
}
