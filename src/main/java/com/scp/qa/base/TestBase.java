package com.scp.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;

	public TestBase(){
		
		try {
			prop = new Properties();
			File propertyfile = new File("Resource/F1.properties");
			FileInputStream readpropertyfile = null;
			readpropertyfile = new FileInputStream(propertyfile);
			prop.load(readpropertyfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
