package com.eliasnogueira.parametros.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Utils {

	public static String lerPropriedade(String nomePropriedade) {
		String valorPropriedade = null;
		
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/test/resources/config.properties"));
			
			valorPropriedade = properties.getProperty(nomePropriedade);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valorPropriedade;
	}
}
