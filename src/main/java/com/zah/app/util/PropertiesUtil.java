package com.zah.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static Properties load(String name) {
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return props;
	}

	public static String getProperty(Properties props, String key) {
		try {
			return props.getProperty(key);
		} catch (Exception e) {
		}
		return "";
	}

}
