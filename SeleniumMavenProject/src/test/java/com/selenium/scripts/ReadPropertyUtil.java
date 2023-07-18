package com.selenium.scripts;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyUtil {
	
	public static String getProperty(String strProperty) throws IOException {
		FileReader fr = new FileReader("config.properties");
		Properties prop = new Properties();
		prop.load(fr);
		return prop.getProperty(strProperty);
	}

}
