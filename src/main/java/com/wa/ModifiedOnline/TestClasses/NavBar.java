package com.wa.ModifiedOnline.TestClasses;

import org.openqa.selenium.WebDriver;

import com.wa.ModifiedOnline.BaseClasses.BaseClass;

import Utilities.Util;

public class NavBar extends BaseClass {

	public WebDriver driver;

	public NavBar(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private String Logout_Button = "by.xpath=>//a[@class='dropdown-item header-links'][normalize-space()='Logout']";
	private String My_Account_Logo = "by.cssSelector=>a[class='nav-link nav-profile-link dropdown-toggle'] img[class='rounded-circle']";
	private String Logout_Popup = "by.cssSelector=>.swal2-confirm.swal2-styled";
	private String Login_Link = "by.xpath=>//div[@class='authentication d-none d-md-block'] //a[contains(text(),'Login')]";
	private String Signup_Link = "by.cssSelector=>div[class='authentication d-none d-md-block'] li:nth-child(1) a:nth-child(1)";
	private String Job_Link = "by.xpath=>//a[@class='header-links'][normalize-space()='Jobs']";
	private String HomePage_Link = "by.cssSelector=>img[alt='logo']";

	public LoginPage gotoLogin() {
		waitforElementToClickable(Login_Link, 3, "login link displayed");

		elementClick(Login_Link, "clicked on login");
		return new LoginPage(driver);
	}

	public void gotoSignup() {

		elementClick(Signup_Link, "clicked on signup");

	}

	public void gotoJobLink() {

		elementClick(Job_Link, "clicked on joblink");
	}

	public boolean isJobLinkDisplayed() {
		return isDisplayed(Job_Link, "Job_Link is displayed");

	}
//
	public boolean verifyJobLinkDisplayed() {
		String text = getText(Job_Link, "job link txt fetched");
		System.out.println(text);
		return Util.verifyTextMatches(text, "Job");

		
	}

	public void gotoHomePageLink() {

		elementClick(HomePage_Link, "clicked on homepage");
	}

	public boolean accountLogoCheck() {
		waitForElementToVisible(My_Account_Logo, 5, "account logo displayed");

		try {
			if (isDisplayed(My_Account_Logo, "account logo displayed")) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("account logo not dispalyed");
			e.printStackTrace();
		}
		return false;
	}

	public boolean accountLogoCheck1() {
		try {
			if (isDisplayed(My_Account_Logo, "account logo displayed")) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("account logo not dispalyed");
			e.printStackTrace();
		}
		return false;
	}

	
	public void clickOnLogo() {
		elementClick(My_Account_Logo,  "click on my account logo");
	}
	
	public void clickOnLogout() { 
	elementClick(Logout_Button, "click on logout");

	elementClick(Logout_Popup, "clicked on logout popup");
	
	}
}
