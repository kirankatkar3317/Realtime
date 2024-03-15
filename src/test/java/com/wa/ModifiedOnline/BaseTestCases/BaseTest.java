package com.wa.ModifiedOnline.BaseTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.wa.ModifiedOnline.TestClasses.FilterPage;
import com.wa.ModifiedOnline.TestClasses.HomePage;
import com.wa.ModifiedOnline.TestClasses.LoginPage;
//import com.wa.ModifiedOnline.TestClasses.loginPage;
import com.wa.ModifiedOnline.TestClasses.NavBar;
import com.wa.ModifiedOnline.TestClasses.ResultPage;
import com.wa.ModifiedOnline.TestClasses.SearchPage;

import Utilities.Commons;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.messages.types.Duration;

@SuppressWarnings("unused")
public class BaseTest {

	public WebDriver driver;

	protected LoginPage loginPage;
	protected HomePage homePage;
	protected NavBar navBar;
	protected SearchPage searchpage;
	protected FilterPage filterPage;
	protected ResultPage resultPage;


	@BeforeClass 
	@Parameters({ "browser" })
	public LoginPage commonSetup(String browser) {
		driver = WebDriverFactory.getInstance().getDriver(browser);
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
		driver.get(Commons.BASE_URL);
		navBar = new NavBar(driver);
		loginPage = navBar.gotoLogin();
		loginPage.loginwithEmail();

		
		//		loginPage = navBar.gotoLogin();
//		loginPage.loginwithEmail();
//cross browser testing with selenium grid and browserstack cloud utility		
		return loginPage;

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
