package com.unity.constants;

import java.util.Properties;

import org.testng.Assert;

import com.unity.utilities.AppConfig;

/**
 * This class sets all the given properties to constants
 * 
 * @author e5562312
 *
 */
public final class AppConstants {
	private AppConstants() {
		throw new IllegalStateException("AppConstants class");
	}

	static Properties props = AppConfig.propertyReader();

	private static String applicationURL = null;

	public static final String USER_DIRECTORY = System.getProperty("user.dir");
	public static final String APPLICATION_ENV = props.getProperty("application.env");
	public static final String UPLOAD_FOLDER_PATH = System.getProperty("user.dir") + "\\test-data\\upload\\";
	public static final String DOWNLOAD_FOLDER_PATH = System.getProperty("user.home") + "/Downloads/";

	public static final String BROWSER_TYPE = props.getProperty("browser.type");
	public static final String TESTRAIL_BASE_URL = props.getProperty("testrail.baseurl");
	public static final Boolean TESTRAIL_UPDATE_FLAG = Boolean.valueOf(props.getProperty("testrail.update.flag"));
	public static final String TESTRAIL_RUNID = props.getProperty("testrail.runid");
	public static final String TESTRAIL_EMAIL = props.getProperty("testrail.email");
	public static final String TESTRAIL_APIKEY = props.getProperty("testrail.apikey");

	// Timeouts
	public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(props.getProperty("pageLoad.timeout"));
	public static final int FRAME_LOAD_TIMEOUT = Integer.parseInt(props.getProperty("frameload.timeout"));
	public static final int POPUP_TIMEOUT = Integer.parseInt(props.getProperty("popup.timeout"));
	public static final int SCRIPT_TIMEOUT = Integer.parseInt(props.getProperty("script.timeout"));
	public static final int WAIT_TIMEOUT = Integer.parseInt(props.getProperty("wait.timeout"));
	public static final int IPP_TIMEOUT = Integer.parseInt(props.getProperty("IPP.timeout"));

	// Web Services constants
	public static final String SERVICE_BASE_URL_QC191 = props.getProperty("service.baseurl.qc191");
	public static final int SERVICE_PORT_QC191 = Integer.parseInt(props.getProperty("port.qc191"));

	// set application URl
	public static String getApplicationURL() {
		switch (APPLICATION_ENV.toLowerCase()) {
		case "qc191":
			applicationURL = props.getProperty("application.url.qc191");
			break;
		// add cases for different environments
		default:
			Assert.fail("Incorrect environment");
			break;
		}
		return applicationURL;
	}

	// Set DB properties
	public static final String UNITY_AO_QC191 = props.getProperty("unity.ao.qc191");
}