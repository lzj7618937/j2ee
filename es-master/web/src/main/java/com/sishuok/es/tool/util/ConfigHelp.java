package com.sishuok.es.tool.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelp {

	private static String filePath = "/media/jaylee/App/workspaces/git/es-master/web/src/main/java/com/sishuok/es/tool/config.properties";

	public static String getValue(String key) {
		return readValue(filePath, key);
	}

	// 根据key读取value
	private static String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(in);
			String value = props.getProperty(key).trim();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void browsePath(String strPath) {
		String[] execString = new String[2];
		String filePath = null;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("windows")) {
			// Window System;
			execString[0] = "explorer";
			try {
				filePath = strPath.replace("/", "\\");
			} catch (Exception ex) {
				filePath = strPath;
			}
		} else {
			// Unix or Linux;
			execString[0] = "netscape";
			filePath = strPath;
		}

		execString[1] = filePath;
		try {
			Runtime.getRuntime().exec(execString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
