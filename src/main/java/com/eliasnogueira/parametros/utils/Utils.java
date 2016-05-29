package com.eliasnogueira.parametros.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Utils {

	public static String lerPropriedade(String propriedade) {
		String valor = null;
		
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/test/resources/config.properties"));
			
			valor = properties.getProperty(propriedade);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valor;
	}
}
