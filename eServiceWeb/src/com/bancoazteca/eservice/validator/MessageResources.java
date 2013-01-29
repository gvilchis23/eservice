package com.bancoazteca.eservice.validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageResources {
	private static Properties properties;
	private static final String FILE = "validator-errors.properties";
	
	private static void init(){		
		try {
			InputStream inStream = null;
			MessageResources.properties = new Properties();
			ClassLoader classLoader = MessageResources.class.getClassLoader();
			inStream = classLoader.getResourceAsStream(FILE);
			MessageResources.properties.load(inStream);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public static String getPropertie(String key){
		if (properties==null){
			init();
		}
		String value = properties.getProperty(key); 		
		return value;
	}
}
