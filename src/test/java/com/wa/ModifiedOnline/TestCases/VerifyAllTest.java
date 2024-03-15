package com.wa.ModifiedOnline.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wa.ModifiedOnline.BaseTestCases.BaseTest;
import com.wa.ModifiedOnline.TestClasses.FilterPage;
import com.wa.ModifiedOnline.TestClasses.SearchPage;

import Utilities.Commons;
import Utilities.ExcelUtility;

public class VerifyAllTest extends BaseTest {

	@DataProvider(name = "getLoginTestData")
	public Object[][] getLoginTestData() {
		Object[][] testData = ExcelUtility.getTestData(Commons.LOGINTESTWITHALLTESTS_TABLENAME);
		return testData;
	}

	@BeforeClass
	public void setExcelFile() throws IOException {
		ExcelUtility.getExcelFile(Commons.EXCEL_PATH_EXCELDRIVEN, Commons.SHEET_2);
		
	}

	@Test(dataProvider = "getLoginTestData", priority=1, enabled=false)
	public void validLoginTest(String email, String password) {
		loginPage.login(email, password);
		Assert.assertTrue(navBar.accountLogoCheck());

	}

	@Test(priority=2)
	public void getSearchResults() {
		navBar.gotoJobLink();
		filterPage = new FilterPage(driver);
		filterPage.sendDataOnCompanyDropdown("pro");
		filterPage.selectfromCompanyDropdown("Promilio");
		resultPage = filterPage.clickOnFilterApplyButton();
		Assert.assertEquals(resultPage.gettingNoResult(), "No Jobs found");
		filterPage.clickOnFilterClearButton();
		
	}

}
