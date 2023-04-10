package com.wa.ModifiedOnline.TestClasses;

import org.openqa.selenium.WebDriver;

import com.wa.ModifiedOnline.BaseClasses.BaseClass;

public class SearchPage extends BaseClass{

	
	public WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		super(driver);
		driver = this.driver;
	}
	
	private String Search_TextBox = "by.xpath=>//input[@id='search']";
		
	
	public void sendTextToSearchBox() {
		sendData(Search_TextBox, "Data Analyst", "data sent to serchbox");
	}
	
	
}
