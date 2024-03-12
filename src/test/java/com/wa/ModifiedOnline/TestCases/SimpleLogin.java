package com.wa.ModifiedOnline.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wa.ModifiedOnline.BaseTestCases.BaseTest;

public class SimpleLogin extends BaseTest{

	
	
	@Test
	
	public void simpleLogin() {
		loginPage.loginwithEmail();
		navBar = loginPage.login("kirankatkar3317@gmail.com", "Kiran@3317");
		Assert.assertTrue(navBar.accountLogoCheck());
		navBar.clickOnLogo();
		navBar.clickOnLogout();
		navBar.gotoLogin();
		loginPage.loginwithEmail();

	}
}
