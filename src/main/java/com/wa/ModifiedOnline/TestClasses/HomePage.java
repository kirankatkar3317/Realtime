package com.wa.ModifiedOnline.TestClasses;

import org.openqa.selenium.WebDriver;

import com.wa.ModifiedOnline.BaseClasses.BaseClass;

public class HomePage extends BaseClass {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}




}