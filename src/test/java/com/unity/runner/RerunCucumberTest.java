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

@CucumberOptions(plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, features = "@report/rerun.txt", glue = "com.unity.stepdefs", tags = "not(@report or @pass or @fail or @hold)")
public class RerunCucumberTest extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@AfterSuite(alwaysRun = true)
	public void openReport() {
		try {
			// Opening current report file
			File reportFile = new File(AppConstants.USER_DIRECTORY + "/report/AutomationReport.html");
			Desktop desktop = Desktop.getDesktop();
			if (reportFile.exists())
				desktop.open(reportFile);

			// Backing up current report file with current timestamp
			File reportBackup = new File(
					AppConstants.USER_DIRECTORY + "/report/archive/AutomationReport_" + AppConstants.APPLICATION_ENV
							+ "_" + new SimpleDateFormat("dd-MM_HH-mm-ss").format(new Date()) + ".html");
			FileUtils.copyFile(reportFile, reportBackup);

		} catch (Exception e) {
			Assert.fail("", e.getCause());
		}
	}
}
