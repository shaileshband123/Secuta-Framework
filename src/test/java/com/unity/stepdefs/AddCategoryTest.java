package com.unity.stepdefs;

import org.testng.SkipException;

import com.unity.browsers.LocalDriverContext;
import com.unity.constants.AppConstants;
import com.unity.pages.AddCategoryPage;
import com.unity.pages.AddInstructionsPage;
import com.unity.pages.UnitySSOLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddCategoryTest extends BaseSteps
{

	AddCategoryPage addCategoryPage = null;
	
	@Then("^User creates category$")
	public void User_creates_category() throws InterruptedException 
	{
		if (scenarioData.get().size() == 0) 
		{
			throw new SkipException("***** TestCase : " + scenario.get().getName()
					+ " not found in Test Data Sheet. Please add an entry of this test case. *****\n ");
		}
		addCategoryPage = new AddCategoryPage(LocalDriverContext.getDriver());
		addCategoryPage.waitForPageToLoad();
		
		addCategoryPage.addCategory();
		addCategoryPage.waitForPageToLoad();
	}


}
