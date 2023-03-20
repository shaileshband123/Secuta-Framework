package com.unity.runner;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import com.unity.constants.AppConstants;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"rerun:report/rerun.txt" }, features = "src/test/resources", glue = "com.unity.stepdefs")
public class RunCucumberTest extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@AfterSuite(alwaysRun = true)
	public void openReport() {
		try {
			// Opening current report file
			File reportFile = new File(AppConstants.USER_DIRECTORY + "/report/SparkReport.html");
			Desktop desktop = Desktop.getDesktop();
			if (reportFile.exists())
				desktop.open(reportFile);

			// Backing up current report file with current timestamp
			File reportBackup = new File(
					AppConstants.USER_DIRECTORY + "/report/archive/SparkReport_" + AppConstants.APPLICATION_ENV + "_"
							+ new SimpleDateFormat("dd-MM_HH-mm-ss").format(new Date()) + ".html");
			FileUtils.copyFile(reportFile, reportBackup);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("", e.getCause());
		}
	}
}