package com.unity.stepdefs;

import org.testng.SkipException;

import com.unity.browsers.LocalDriverContext;
import com.unity.constants.AppConstants;
import com.unity.pages.AddInstructionsPage;
import com.unity.pages.UnitySSOLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddInstructionsTest extends BaseSteps
{

	AddInstructionsPage addInstructionsPage = null;
	
	@Given("^user is on Instruction page$")
	public void user_is_on_Instruction_page() 
	{
		if (scenarioData.get().size() == 0) 
		{
			throw new SkipException("***** TestCase : " + scenario.get().getName()
					+ " not found in Test Data Sheet. Please add an entry of this test case. *****\n ");
		}
		
		addInstructionsPage = new AddInstructionsPage(LocalDriverContext.getDriver());
		addInstructionsPage.waitForPageToLoad();
		
		addInstructionsPage.verifyUserIsOnInstructionPage();
		addInstructionsPage.waitForPageToLoad();
	}
	
	@When("^add details about instruction$")
	public void add_details_about_instruction() 
	{
		if (scenarioData.get().size() == 0) 
		{
			throw new SkipException("***** TestCase : " + scenario.get().getName()
					+ " not found in Test Data Sheet. Please add an entry of this test case. *****\n ");
		}
		addInstructionsPage = new AddInstructionsPage(LocalDriverContext.getDriver());
		addInstructionsPage.waitForPageToLoad();
		
		addInstructionsPage.addInstructions();
		addInstructionsPage.waitForPageToLoad();

	}

	@Then("^Instruction should get created$")
	public void Instruction_should_get_created() 
	{
		if (scenarioData.get().size() == 0) 
		{
			throw new SkipException("***** TestCase : " + scenario.get().getName()
					+ " not found in Test Data Sheet. Please add an entry of this test case. *****\n ");
		}
		addInstructionsPage = new AddInstructionsPage(LocalDriverContext.getDriver());
		addInstructionsPage.waitForPageToLoad();
		
		addInstructionsPage.verifyInstructions();
		addInstructionsPage.waitForPageToLoad();
	}


}
