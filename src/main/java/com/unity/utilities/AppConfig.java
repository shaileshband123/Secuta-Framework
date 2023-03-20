package com.unity.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AppConfig {

	private AppConfig() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * This method is used to read the property file
	 * 
	 * @author e5562312 neerajM
	 * 
	 * @return property file object
	 */
	public static Properties propertyReader() {
		FileReader reader;
		Properties prop = new Properties();
		try {
			reader = new FileReader("application.properties");
			prop.load(reader);
		} catch (IOException e) {
			Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, "The properties file could not be loaded", e);
		}
		return prop;
	}
}
