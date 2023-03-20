package com.unity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.unity.constants.AppConstants;
import com.unity.customelements.CustomElementFieldDecorator;
import com.unity.customelements.UnityWebElement;
import com.unity.utilities.Utilities;

public class UnitySSOLoginPage extends BaseTestPage {

	WebDriver driver;

	public UnitySSOLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new CustomElementFieldDecorator(driver, driver), this);
	}

	// login form
	@FindBy(xpath = "//*[@name='UserName']")
	public UnityWebElement textBoxUserID;

	@FindBy(xpath = "//*[@name='Password']")
	public UnityWebElement textBoxPassword;

	@FindBy(xpath = "//*[@class='btn btn-default']")
	public UnityWebElement buttonSignIn;

	@FindBy(xpath = "(//*[@class='stylink'])[2]")
	public UnityWebElement selectYEDC;

	public void waitForPageToLoad() {
		startTime = System.currentTimeMillis();
		if (super.isPageLoaded(driver))
			try {
				endTime = System.currentTimeMillis();
				pageLoadTime = (endTime - startTime) / 1000;
			} catch (Exception e) {
				Assert.fail("Expected page did not load\n");
			}
		else {
			Assert.fail("Timed out after " + AppConstants.PAGE_LOAD_TIMEOUT + " seconds\n");
		}
	}

	public void loginWithOperator1() 
	{
		Utilities.wait(3000);
		textBoxUserID.sendKeys("namrata.mehta");
		textBoxPassword.sendKeys("Admin@123");
		buttonSignIn.click();
		selectYEDC.click();
		
	}

}
