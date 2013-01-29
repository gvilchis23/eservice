package com.bancoazteca.elite.util;

public class Enviroment {
	
	private static boolean isProduccion = false;
	private static boolean isDesarrollo = false;
	private static boolean isPruebas = false;
	
	static {
		
		try {
			
			String enviroment =  System.getProperty("sistema.ambiente");

			if ( enviroment == null ){
				isDesarrollo = true;
			}else{
				if ( enviroment.compareToIgnoreCase("produccion") == 0 ){
					isProduccion = true;
				}
				if ( enviroment.compareToIgnoreCase("pruebas") == 0 ){
					isPruebas = true;
				}
				if ( enviroment.compareToIgnoreCase("desarrollo") == 0  ){
					isDesarrollo = true;
				}
			}
		}catch ( Exception e ){
			e.printStackTrace();
		}
		
	}

	public static boolean isProduccion() {
		return isProduccion;
	}	

	public static boolean isDesarrollo() {
		return isDesarrollo;
	}

	public static boolean isPruebas() {
		return isPruebas;
	}
	
}