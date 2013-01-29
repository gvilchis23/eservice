package com.bancoazteca.elite.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class GeneradorRFC {
	private static final Logger log = Logger.getLogger(GeneradorRFC.class);

	private static HashMap<String, String> properties;
	
	/**
	 * @param nombre Nombre simple o compuesto de la persona
	 * @param apaterno Apellido Paterno  
	 * @param amaterno Apellido Materno
	 * @param fechaNacimiento Fecha de nacimiento con el formato yyyy-mm-dd
	 * @return {@link GeneradorRFC};
	 * */
	public static String obtenRFC(String nombre, String aPaterno, String aMaterno, String fechaNacimiento){
		String prefijo = "";
		String fecha = "";
		String rfc = "";
		
		properties = getProperties();
		if( properties == null ){
			return "";
		}
		prefijo = getPrefijo(formatString(nombre), formatString( aPaterno), formatString( aMaterno ) );
		fecha = getFechaFormateada(fechaNacimiento);
		
		if( !prefijo.equalsIgnoreCase("") && !fecha.equals("")  ){
			rfc = prefijo + fecha;
		}
		log.info("RFC generado: " + rfc);
		return rfc;
	}
	
	
	private static String getFechaFormateada(String fechaNacimiento){
		String fechaRFC = "";
		NumberFormat formateofecha = new DecimalFormat("00");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;
		Calendar calendario = Calendar.getInstance();
		
		try {
			fecha = formato.parse(fechaNacimiento);
			calendario.setTimeInMillis( fecha.getTime()  );	
			int anio = calendario.get(Calendar.YEAR) ;
			int mes = calendario.get(Calendar.MONTH) + 1;
			int dia = calendario.get(Calendar.DAY_OF_MONTH);
			fechaRFC =  String.valueOf(anio).substring(2) 
						+ formateofecha.format(mes)
						+ formateofecha.format(dia);
		} catch (Exception e) {
			log.info("No se pudo procesar la fecha del RFC");
//			e.printStackTrace();
		}
		return fechaRFC;
	}
	
	private static String getPrefijo(String nombre, String aPaterno, String aMaterno){
		String palabrotas = properties.get( "palabrotas" );
		String vocales = properties.get( "vocales" );
		Pattern patronVocales = Pattern.compile(vocales);
		Matcher matcher = null;
		String articulos = properties.get( "articulos" );
		String otrosArticulos = properties.get( "otrosArticulos" );
		String nombresComunes = properties.get( "nombresComunes" );
		String primeraPaterno = properties.get( "primeraPaterno" );
		String rfc = "";
		
		try{
			
			patronVocales = Pattern.compile(vocales);
			
			//Quitar espacios externos
			nombre = nombre.trim().toUpperCase();
			aPaterno = aPaterno.trim().toUpperCase();
			aMaterno = aMaterno.trim().toUpperCase();
			
			//Quitar los articulos de los apellidos
			nombre = nombre.replaceAll( articulos, "");
			aPaterno = aPaterno.replaceAll(articulos, "");
			aMaterno = aMaterno.replaceAll(articulos, "");
			
			aPaterno = aPaterno.replaceAll(otrosArticulos, "");
			aMaterno = aMaterno.replaceAll(otrosArticulos, "");
			
			//Elimina nombres comunes
			nombre = nombre.replaceAll( nombresComunes , "");
			nombre = nombre.replaceAll( articulos, "");
			nombre = nombre.replaceAll( otrosArticulos, "");
			
			primeraPaterno = "" + aPaterno.charAt(0);
			aPaterno = aPaterno.substring(1);
			matcher = patronVocales.matcher(aPaterno);	
			matcher.matches();
			rfc += primeraPaterno+ matcher.group(2) + aMaterno.charAt(0) + nombre.charAt(0);
		}catch (Exception e) {
			log.info("No se pudo procesar el prefijo del RFC");
			e.getStackTrace();
		}
		
		if(rfc.matches(palabrotas)){
			rfc = primeraPaterno+ "X" + aMaterno.charAt(0) + nombre.charAt(0);
		}
		
		return rfc;
	}
	
	private static String formatString(String s) {
		String temp = "";
		
		if(s == null || s.equals("")){
			return "";
		}		
		temp = formatStringOptional(s);
        return temp;
    }
	
	private static String formatStringOptional(String s){
		
			s = s.replaceAll( properties.get("regExpA").toLowerCase() , properties.get("vocalA").toLowerCase() );
			s = s.replaceAll( properties.get("regExpE").toLowerCase() , properties.get("vocalE").toLowerCase() );
			s = s.replaceAll( properties.get("regExpI").toLowerCase() , properties.get("vocalI").toLowerCase() );
			s = s.replaceAll( properties.get("regExpO").toLowerCase() , properties.get("vocalO").toLowerCase() );
		    s = s.replaceAll( properties.get("regExpU").toLowerCase() , properties.get("vocalU").toLowerCase() );
		    
		    s = s.replaceAll( properties.get("regExpA").toUpperCase() , properties.get("vocalA").toUpperCase() );
		    s = s.replaceAll( properties.get("regExpE").toUpperCase() , properties.get("vocalE").toUpperCase() );
		    s = s.replaceAll( properties.get("regExpI").toUpperCase() , properties.get("vocalI").toUpperCase() );
		    s = s.replaceAll( properties.get("regExpO").toUpperCase() , properties.get("vocalO").toUpperCase() );
		    s = s.replaceAll( properties.get("regExpU").toUpperCase() , properties.get("vocalU").toUpperCase() );    
		    
		    return s;	
	}
	
	private static HashMap<String , String> getProperties() {		
			try {
				properties = new HashMap<String, String>();
				properties.put( "palabrotas" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.restricciones") );
				properties.put( "vocales" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.vocales") );
				properties.put( "articulos" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.articulos") );
				properties.put( "otrosArticulos" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.otrosArticulos") );
				properties.put( "nombresComunes" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.nombresComunes") );
				
				properties.put( "vocalA" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.constantes.a") );
				properties.put( "vocalE" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.constantes.e") );
				properties.put( "vocalI" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.constantes.i") );
				properties.put( "vocalO" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.constantes.o") );
				properties.put( "vocalU" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.constantes.u") );
				
				properties.put( "regExpA" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.vocal.a") );
				properties.put( "regExpE" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.vocal.e") );
				properties.put( "regExpI" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.vocal.i") );
				properties.put( "regExpO" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.vocal.o") );
				properties.put( "regExpU" , PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.vocal.u") );
				
				properties.put( "regExpAcentos", PropertiesService.getInstance().getPropertie("rfc.properties", "rfc.rules.acentos") );

			} catch (IOException e) {
				log.info("Ocurrio un problema al cargar el properties de RFC");
				e.printStackTrace();
				return null;
			}
		return properties;
	}

}
