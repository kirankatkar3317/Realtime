package Cucumber.StepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.wa.ModifiedOnline.BaseTestCases.BaseTest;
import com.wa.ModifiedOnline.BaseTestCases.CheckPoint;
import com.wa.ModifiedOnline.TestClasses.LoginPage;
import com.wa.ModifiedOnline.TestClasses.NavBar;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinitionLogin extends BaseTest {

	protected WebDriver driver;
	private NavBar navbar;
	private LoginPage loginPage;

//	@Before
//	public void setupbrowser() {
//		commonSetup("chrome");
//	}
//	

//	
//	@AfterStep
//	public void afterSteps() {
//		methodSetup()
//	}

	@Given("open website click on Login link")
	public void open_website_click_on_log_in_link() {
		loginPage =commonSetup("chrome");
		loginPage.loginwithEmail();
	}

	@Then("^login using username (.*) and password (.*)$")
	public void login_using_username_something_and_password_something(String String1, String String2) {
		navbar = loginPage.login(String1, String2);
	}

	@Then("in login page check login logo is present")
	public void in_login_page_check_login_logo_is_present() {
		boolean check = navbar.accountLogoCheck();
		CheckPoint.check("Test-02", check, "Account Logo Check");
	}

	@And("click on logout link")
	public void click_on_logout_link() {
		navbar.clickOnLogo();
		navbar.clickOnLogout();
	}

	@After
	public void exitBrowser() {
		commonTeardown();
	}
}
