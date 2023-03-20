package com.unity.browsers;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class RemoteDriver implements Driver {

	protected Logger log = LogManager.getLogger();

	/**
	 * This method provides an implementation of getDriver() method for
	 * RemoteWebDriver used for selenium Grid
	 * 
	 * @author e5562312 neerajM
	 */
	public WebDriver getDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.WINDOWS);
		try {
			log.info("[ Initializing remote driver ]");
			return new RemoteWebDriver(new URL("http://10.253.185.129:8090/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			Assert.fail("Remote Server URL is not correct", e.getCause());
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return null;
	}
}
