package com.greenmeows.jdiva.utils;

import java.io.File;

public class FileUtils {
	
	public static String getSongFolder() {
		File pwd = new File(System.getProperty("user.dir"));
		String mainfolder = pwd.getParentFile().toString();
		return mainfolder+"\\"+"core"+"\\"+"assets"+"\\"+"songs";
	}
	
}
