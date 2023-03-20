package com.unity.stepdefs;

import org.testng.SkipException;

import com.unity.browsers.LocalDriverContext;
import com.unity.constants.AppConstants;
import com.unity.pages.UnitySSOLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseSteps {

	UnitySSOLoginPage unitySSOLoginPage = null;

	@Given("^user is on Unity Login Page$")
	public void user_is_on_unity_login_page() {
		if (scenarioData.get().size() == 0) {
			throw new SkipException("***** TestCase : " + scenario.get().getName()
					+ " not found in Test Data Sheet. Please add an entry of this test case. *****\n ");
		}
		LocalDriverContext.getDriver().get(AppConstants.getApplicationURL());
		unitySSOLoginPage = new UnitySSOLoginPage(LocalDriverContext.getDriver());
		unitySSOLoginPage.waitForPageToLoad();
		unitySSOLoginPage.waitForPageToLoad();
	}

	@When("^user logs into Unity with \"([^\"]*)\"$")
	public void user_logs_into_unity_with(String userName) {
		unitySSOLoginPage = new UnitySSOLoginPage(LocalDriverContext.getDriver());
		unitySSOLoginPage.waitForPageToLoad();
		userName = getVariableValue(userName);
		userName = userName.toLowerCase();
		switch (userName) {
		case "operator1":
		case "namrata.mehta":
			unitySSOLoginPage.loginWithOperator1();
			break;
		default:
			throw new AssertionError("Given user is not found");
		}
		unitySSOLoginPage.waitForPageToLoad();
		unitySSOLoginPage.waitForPageToLoad();
	}

}
