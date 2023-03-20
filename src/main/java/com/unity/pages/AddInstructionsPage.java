package com.unity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.unity.constants.AppConstants;
import com.unity.customelements.CustomElementFieldDecorator;
import com.unity.customelements.UnityWebElement;
import com.unity.utilities.Utilities;

public class AddInstructionsPage extends BaseTestPage {

	WebDriver driver;

	public AddInstructionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new CustomElementFieldDecorator(driver, driver), this);
	}

	// login form
	@FindBy(xpath = "(//span[@class='rtsTxt'])[2]")
	public UnityWebElement setUpBtn;

	@FindBy(xpath = "//a[@href='Instructions']/span")
	public UnityWebElement instructionsBtn;

	@FindBy(xpath = "(//button[@class='k-button'])[1]")
	public UnityWebElement addBtn;

	@FindBy(xpath = "//div[@class='InstName']/input")
	public UnityWebElement instructionName;

	@FindBy(xpath = "//lt-div[@class='lt-highlighter__wrapper']")
	public UnityWebElement instructionDescription;
	
	@FindBy(xpath = "//button[@class='amSaveButton k-button']")
	public UnityWebElement saveBtn;
	
	@FindBy(xpath = "//div[@id='dialogSubmit']/div[2]/div/button")
	public UnityWebElement instructionPopUp;
	
	
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

	public void verifyUserIsOnInstructionPage() 
	{
		Utilities.wait(3000);

		setUpBtn.click();
		
		instructionsBtn.click();
		
	}
	
	public void addInstructions()
	{
		Utilities.wait(3000);
		
		addBtn.click();
		
		instructionName.sendKeys("Text");
		
		/*
		 * instructionDescription.sendKeys("Text");
		 * 
		 * saveBtn.click();
		 * 
		 * instructionPopUp.click();
		 */
	}
	
	public void verifyInstructions()
	{
		Utilities.wait(3000);
	}

}
