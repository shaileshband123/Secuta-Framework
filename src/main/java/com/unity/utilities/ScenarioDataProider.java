package com.unity.utilities;

import java.util.Map;

import org.testng.Assert;

import com.unity.constants.AppConstants;

public class ScenarioDataProider {

	private ScenarioDataProider() {
		throw new IllegalStateException("Utility Class");
	}

	public static Map<String, String> getData(String scenarioName) {
		ExcelUtilities excelUtils = null;
		try {
			switch (AppConstants.APPLICATION_ENV.toLowerCase()) {
			case "tst181":
				excelUtils = new ExcelUtilities(AppConstants.USER_DIRECTORY + "/test-data/UnityTestData_TST_181.xlsx");
				break;
			case "tst191":
				excelUtils = new ExcelUtilities(AppConstants.USER_DIRECTORY + "/test-data/UnityTestData_TST_181.xlsx");
				break;
			case "qc181":
			case "qc191":
			case "qc201":
				excelUtils = new ExcelUtilities(AppConstants.USER_DIRECTORY + "/test-data/UnityTestData_QC.xlsx");
				break;
			case "tst191.ad":
				excelUtils = new ExcelUtilities(
						AppConstants.USER_DIRECTORY + "/test-data/UnityTestData_TST_191_AD.xlsx");
				break;
			case "tst191.td":
				excelUtils = new ExcelUtilities(
						AppConstants.USER_DIRECTORY + "/test-data/UnityTestData_TST_191_TD.xlsx");
				break;
			case "uat":
			excelUtils = new ExcelUtilities(
					AppConstants.USER_DIRECTORY + "/test-data/UnityTestData_UAT.xlsx");
			break;
			default:
				Assert.fail("*****Enviroment is not properly set*****");
				break;
			}
			return Utilities.getData(scenarioName, excelUtils);
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
			return null;
		}
	}
}
