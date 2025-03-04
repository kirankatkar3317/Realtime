package com.wa.ModifiedOnline.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wa.ModifiedOnline.BaseTestCases.BaseTest;
import com.wa.ModifiedOnline.TestClasses.NavBar;

import Utilities.Commons;
import Utilities.ExcelUtility;

public class LogInOutTest extends BaseTest {

	@DataProvider(name = "getLoginTestData")
	public Object[][] getLoginTestData() {
		Object[][] testData = ExcelUtility.getTestData(Commons.LOGINTEST_TABLENAME1);
		return testData;
	}

	@BeforeClass
	public void setExcelFile() throws IOException {
		ExcelUtility.getExcelFile(Commons.EXCEL_PATH_BOOK1, Commons.SHEET_1);
	}

	@Test(dataProvider = "getLoginTestData")
	public void validLoginTest(String email, String password) {
		loginPage.loginwithEmail();
		loginPage.login(email, password);
		Assert.assertTrue(navBar.accountLogoCheck());
		navBar.clickOnLogo();
		navBar.clickOnLogout();
		navBar.gotoLogin();
		loginPage.loginwithEmail();

	}

//	keep this for later use
//	@Test()
//	public void validLoginTest1(String email, String password) {
//		loginpage.login(email, password);
//		Assert.assertTrue(navBar.accountLogoCheck());
//		navBar.clickOnLogo();
//		navBar.clickOnLogout();
//	}

	@Test()
	public void validLogutTest() {
//		navBar.got
//		Assert.assertTrue(navBar.accountLogoCheck());
//		navBar.clickOnLogout();
	}

}
