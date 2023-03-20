package com.unity.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.unity.browsers.DriverFactory;
import com.unity.browsers.LocalDriverContext;
import com.unity.constants.AppConstants;
import com.unity.utilities.TestRailClient;
import com.unity.utilities.Utilities;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

	private Logger log;

	@Before
	public void setup(Scenario scenario) {
		log = LogManager.getLogger(scenario.getName());
		WebDriver driver = DriverFactory.getDriver(AppConstants.BROWSER_TYPE);
		driver.manage().window().maximize();
		LocalDriverContext.setDriver(driver);
		BaseSteps baseSteps = new BaseSteps();
		baseSteps.setScenario(scenario);
		log.info("Started execution of scenario : > **** " + scenario.getName() + " ****");
	}

	@AfterStep
	public void addScreenshotOnFailure(Scenario scenario) {
		if (scenario.isFailed()) {
			log.info("Scenario Failed... Taking screenshot");
			try {
				byte[] screenshot = Utilities.takeFullScreenShot(LocalDriverContext.getDriver());
				scenario.attach(screenshot, "image/png", scenario.getName());
			} catch (Exception e) {
				log.info("Couldn't capture screenshot");
				Assert.fail("Failed to capture screenshot", e.getCause());
			}
		}
	}

	@After
	public void tearDown(Scenario scenario) {
		log.info("Finished execution of scenario : > **** " + scenario.getName() + " ****");
		log.info("[ Closing driver ]");
		if (LocalDriverContext.getDriver() != null) {
			LocalDriverContext.getDriver().quit();
		}

		// updating status in TestRail
		if (Boolean.TRUE.equals(AppConstants.TESTRAIL_UPDATE_FLAG)) {
			TestRailClient client = new TestRailClient(AppConstants.TESTRAIL_BASE_URL, AppConstants.TESTRAIL_EMAIL,
					AppConstants.TESTRAIL_APIKEY);
			client.updateTestRail(scenario.getName(), scenario.getStatus().toString());
		}
	}
}
