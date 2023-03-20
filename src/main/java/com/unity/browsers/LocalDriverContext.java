package com.unity.browsers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class LocalDriverContext {

	private LocalDriverContext() {
		throw new IllegalStateException("Driver Class");
	}

	private static Map<Integer, WebDriver> driverMap = new HashMap<>();

	public static WebDriver getDriver() {
		return driverMap.get((int) (Thread.currentThread().getId()));
	}

	public static void setDriver(WebDriver driver) {
		driverMap.put((int) (Thread.currentThread().getId()), driver);
	}
}
