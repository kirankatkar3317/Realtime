package com.wa.ModifiedOnline.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wa.ModifiedOnline.BaseTestCases.BaseTest;

import Utilities.DataProviders;

public class TestRandom extends BaseTest{
	@Test(priority=1,dataProvider = "validLogin",dataProviderClass = DataProviders.class)
	void LoginDatadriven(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		
		loginPage.login(useremail, pwd);
		Assert.assertTrue(navBar.accountLogoCheck());
		navBar.clickOnLogo();
		navBar.clickOnLogout();
		navBar.gotoLogin();
		loginPage.loginwithEmail();
	}
	
	
	@Test(priority=2,dataProvider = "invalidLogin",dataProviderClass = DataProviders.class)
	void InvalidLoginDatadriven(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		
		loginPage.login(useremail, pwd);
		Assert.assertFalse(navBar.accountLogoCheck1());

	}
	
}
