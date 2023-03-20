package com.unity.utilities;

import static io.restassured.RestAssured.get;

import org.testng.Assert;

import com.unity.constants.AppConstants;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WebServicesHandler {
	String resourceUrl = null;

	/**
	 * This constructor is used to set base url and port of the service
	 */
	public WebServicesHandler(String resourceUrl) {
		switch (AppConstants.APPLICATION_ENV.toLowerCase()) {
		case "qc191":
			RestAssured.baseURI = AppConstants.SERVICE_BASE_URL_QC191;
			RestAssured.port = AppConstants.SERVICE_PORT_QC191;
			break;
		// add cases for different environments
		default:
			Assert.fail("*****Web service enviroment is not properly set*****");
			break;
		}
		RestAssured.urlEncodingEnabled = false;
		this.resourceUrl = resourceUrl;
	}

	protected Response getResponse() {
		Response response = get(resourceUrl);
		Assert.assertEquals(response.getStatusCode(), 200, "!! Invalid Service URL !!\n" + resourceUrl);
		return response;
	}
}