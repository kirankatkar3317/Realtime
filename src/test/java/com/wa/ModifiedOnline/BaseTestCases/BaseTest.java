package com.wa.ModifiedOnline.BaseTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.wa.ModifiedOnline.TestClasses.FilterPage;
import com.wa.ModifiedOnline.TestClasses.HomePage;
import com.wa.ModifiedOnline.TestClasses.LoginPage;
import com.wa.ModifiedOnline.TestClasses.NavBar;
import com.wa.ModifiedOnline.TestClasses.SearchPage;

import Utilities.Commons;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseTest {

	public WebDriver driver;

	protected LoginPage loginpage;
	protected HomePage homePage;
	protected NavBar navBar;
	protected SearchPage searchpage;
	protected FilterPage filterPage;


	@BeforeClass
	@Parameters({ "browser" })
	public LoginPage commonSetup(String browser) {
		driver = WebDriverFactory.getInstance().getDriver(browser);
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get(Commons.BASE_URL);
		navBar = new NavBar(driver);
		loginpage = navBar.gotoLogin();
		loginpage.loginwithEmail();
//cross browser testing with selenium grid and browserstack cloud utility		
		return loginpage;

	}
	
	@BeforeMethod
	public void methodSetup() {
		CheckPoint.clearMap();
	}

	
	@AfterClass
	public void commonTeardown() {
		// driver.quit();
		WebDriverFactory.getInstance().exitDriver();
	}

}
