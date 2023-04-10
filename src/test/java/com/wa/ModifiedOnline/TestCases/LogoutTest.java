package com.wa.ModifiedOnline.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.wa.ModifiedOnline.BaseTestCases.BaseTest;
import com.wa.ModifiedOnline.BaseTestCases.CheckPoint;

public class LogoutTest extends BaseTest {

	@Test
	public void validLoginTest() {
		navBar = loginpage.login("kirankatkar3317@gmail.com", "Kiran@3317");
		boolean result = navBar.verifyJobLinkDisplayed();
		CheckPoint.check("test-01", result, "joblink verification");
		//Assert.assertFalse(result);
		boolean result1 = navBar.accountLogoCheck();
		CheckPoint.checkFinal("test-01", result1, "accountlogo verification");
		//Assert.assertFalse(result1);
	}

	@Test(enabled = false)
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
