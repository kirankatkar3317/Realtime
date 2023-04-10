package com.wa.ModifiedOnline.TestClasses;

import org.openqa.selenium.WebDriver;

import com.wa.ModifiedOnline.BaseClasses.BaseClass;

public class LoginPage extends BaseClass {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private String Email_Textbox = "by.cssSelector=>input[placeholder='Your email']";
	private String Password_Textbox = "by.cssSelector=>input[placeholder='Password']";
	private String Login_Button = "by.cssSelector=>button[type='submit']";
	private String Login_With_Email = "by.cssSelector=>span[class='form-btn']";

	public void loginwithEmail() {

		
		
		elementClick(Login_With_Email, "click on login with email");
	}

	public NavBar login(String email, String password) {

//		WebElement mailTextBox = driver.findElement(By.cssSelector(Email_Textbox));
//		mailTextBox.sendKeys(email);
		
		sendData(Email_Textbox, email, "email ");

		sendData(Password_Textbox, password, "password ");

		elementClick(Login_Button, "Login button click", 2);

		return new NavBar(driver);

	}

}
