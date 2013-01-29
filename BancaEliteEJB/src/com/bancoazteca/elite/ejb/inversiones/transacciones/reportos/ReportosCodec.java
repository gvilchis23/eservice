package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos;

import static com.bancoazteca.elite.ejb.inversiones.InversionesGenericException.LEVEL_WEB_SERVICES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaResponseTO;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;

public final class ReportosCodec
{
	public static final String SIN_REGISTROS="AR001";
	private static final String EXITO_ALTA_CLIENTE="AC001";
	private static final String CLIENTE_ALTA_ANTERIIOR="AC002";

	public String encode(Map<String,String> mapa,String[] keysRequest)
	{
		StringBuilder encodedData=new StringBuilder();

		for (String key : keysRequest) {
			if(mapa.containsKey(key))
			{
				encodedData.append(Formatter.removeSpacesLeftRight(key));
				encodedData.append("=");
				String valor = Formatter.removeSpacesLeftRight(mapa.get(key));
				if(Validator.isEmptyData(valor) ){
					valor = "null";
				}
				encodedData.append(valor);
				encodedData.append("|");
			}
		}
		encodedData.deleteCharAt(encodedData.length()-1);
		return encodedData.toString();
	}

	public Map<String,String> decode(String respuesta,String[] keys) throws InversionesGenericException
	{
		respuesta=respuesta.replaceAll("<Salida>","");
		respuesta=respuesta.replaceAll("</Salida>","");

		MessageElement messageElement=null;

		try {
			messageElement = XMLDecode.buildXMLMessageElement(respuesta);
		} catch (XmlDecodeException e) {
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_WEB_SERVICES);
			exceptionTO.setMessage("Lo sentimos, existió un error de comunicación con el departamento de Tesorería.");
			throw new InversionesGenericException(exceptionTO);
		}

		respuesta=messageElement.item(0).getNodeValue();
		boolean isValid = validaRespuesta( respuesta );
		Map<String, String> mapa = new HashMap<String, String>();
		if(isValid){
			String[] datos = respuesta.split("\\|");

			for (int i = 0; i < datos.length; i++) {
				mapa.put(keys[i], datos[i]);
			}
		}
		
		
		
		return mapa;
	}

	public Map<String,String> decodeValidacionPlazo(String respuesta,String[] keyseResp)throws InversionesGenericException{
		String patternCodigo = "\\d{4}";
		String patternDescInicio = "\\<\\s*descripcion\\s*\\>";
		String patternDescFin = "\\<\\s*\\/\\s*descripcion\\s*\\>";
		
		//Busca codigo de respuesta
		Pattern pattern = Pattern.compile( patternCodigo );
		Matcher matcher = pattern.matcher( respuesta );
		String codigo = null;
		String desc = null;
		if( matcher.find() ){
			codigo = respuesta.substring( matcher.start(), matcher.end() );
		}
		
		//Busca la descripcion
		pattern = Pattern.compile( patternDescInicio );
		matcher = pattern.matcher( respuesta );
		int indexInicio = 0, indexFin = 0;
		if( matcher.find() ){
			indexInicio = matcher.end();
		}
		
		indexFin = indexInicio;
		pattern = Pattern.compile( patternDescFin );
		matcher = pattern.matcher( respuesta );
		if( matcher.find() ){
			indexFin = matcher.start();
		}
		desc = respuesta.substring( indexInicio, indexFin );

		Map<String,String> map=new HashMap<String, String>();
		map.put( keyseResp[0],desc );
		map.put("codigo",codigo);
		return map;
	}
	
	public List<Map<String,String>> decodeComprobante(String respuesta,String[] keyseResp)throws InversionesGenericException{

		MessageElement messageElement=null;

		List<Map<String,String>>listaMapas=null;
		respuesta=respuesta.replaceAll("<Salida>","");
		respuesta=respuesta.replaceAll("</Salida>","");
		try {
			messageElement = XMLDecode.buildXMLMessageElement(respuesta);
		} catch (XmlDecodeException e) {
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_WEB_SERVICES);				
			exceptionTO.setMessage("Error: Lo sentimos por el momento no es posible procesar la petición.");
			throw new InversionesGenericException(exceptionTO);
		}
		respuesta=messageElement.item(0).getNodeValue();
		boolean valido=validaRespuesta(respuesta);
		if(valido){ 

			String datosGenerales=messageElement.item(0).getNodeValue()+messageElement.item(1).getLastChild().getFirstChild().getNodeValue();

			datosGenerales="<?xml version='1.0' ?><Comprobante>"+datosGenerales+"</Comprobante>";

			String keysGenerales[]=new String[24];

			for (int i = 0; i < keysGenerales.length; i++) {
				keysGenerales[i]=keyseResp[i];
			}

			String[] keysAsignaciones=new String[12];
			for (int i = 0; i < keysAsignaciones.length; i++) {
				keysAsignaciones[i]=keyseResp[i+24];
			}

			listaMapas=new ArrayList<Map<String,String>>();

			Map<String,String>datosMapa=decode(datosGenerales,keysGenerales);

			listaMapas.add(datosMapa);

			NodeList list=messageElement.item(1).getChildNodes();
			String asignacion=null;
			
			for (int i = 0; i < list.getLength()-1; i++) {
				asignacion="<?xml version='1.0' ?>"+list.item(i);
				datosMapa=decode(asignacion,keysAsignaciones);
				listaMapas.add(datosMapa);
			}
		}
		
		return listaMapas;

	}

	public List<Map<String,String>> decodeResumen(String respuesta, String[] keys)throws InversionesGenericException{

		MessageElement messageElement=null;

		respuesta=respuesta.replaceAll("<Salida>","");
		respuesta=respuesta.replaceAll("</Salida>","");
		int inicio = respuesta.indexOf("<?");
		int fin = respuesta.indexOf("?>") + 2;
		String encabezado = respuesta.substring(inicio, fin);
		String parceo = respuesta.substring(fin);
		if(parceo.length()>0){
			respuesta = encabezado + "<taz>" + parceo + "</taz>";
		}		
		List<Map<String,String>> arregloMaps = new  ArrayList<Map<String,String>>();
		try {
			
			messageElement = XMLDecode.buildXMLMessageElement(respuesta);
		} catch (XmlDecodeException e) {
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_WEB_SERVICES);				
			exceptionTO.setMessage("Lo sentimos, existió un error de comunicación con el departamento de Tesorería.");
			throw new InversionesGenericException(exceptionTO);
		}
		respuesta=messageElement.item(0).getFirstChild().getNodeValue();
		boolean valido=validaRespuesta(respuesta);
		if(valido){
			try {
				Iterator<MessageElement> it = messageElement.getChildren().iterator();
				while (it.hasNext()) {
					MessageElement me = null;
					me = it.next();
					String contenido2 = me.getAsString().toString();
					contenido2 = encabezado + contenido2;

					Map<String, String> mapa = decode(contenido2, keys);
					arregloMaps.add(mapa);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arregloMaps;
	}

	public List<Map<String,String>> decodeConciliacion(String respuesta, String[] keys)throws InversionesGenericException{
		String[] keysAviso = {"CODIGO","DESCRIPCION"};
		MessageElement messageElement=null;

		respuesta=respuesta.replaceAll("<Salida>","");
		respuesta=respuesta.replaceAll("</Salida>","");
		int inicio = respuesta.indexOf("<?");
		int fin = respuesta.indexOf("?>") + 2;
		String encabezado = respuesta.substring(inicio, fin);
		String parceo = respuesta.substring(fin);
		if(parceo.length()>0){
			respuesta = encabezado + "<taz>" + parceo + "</taz>";
		}		
		List<Map<String,String>> arregloMaps = new  ArrayList<Map<String,String>>();
		try {
			
			messageElement = XMLDecode.buildXMLMessageElement(respuesta);
			
			respuesta=messageElement.item(0).getFirstChild().getNodeValue();
			boolean valido=validaRespuesta(respuesta);
			if(valido){
				
				String[] datos = respuesta.split("\\|");
				Iterator<MessageElement> it = messageElement.getChildren().iterator();
				while (it.hasNext()) {
					MessageElement me = null;
					me = it.next();
					String contenido2 = me.getAsString().toString();
					contenido2 = encabezado + contenido2;
					if (datos.length == 2){
						Map<String, String> mapa = decode(contenido2, keysAviso);
						arregloMaps.add(mapa);
					}else{
						Map<String, String> mapa = decode(contenido2, keys);
						arregloMaps.add(mapa);
					}
				}
				
			}
			
		} catch (XmlDecodeException e) {
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_WEB_SERVICES);				
			exceptionTO.setMessage("Lo sentimos, existió un error de comunicación con el departamento de Tesorería.");
			throw new InversionesGenericException(exceptionTO);
		} catch (InversionesGenericException e) {
			throw e;
		} catch (Exception e) {
			throw new InversionesGenericException();
		}
		
		return arregloMaps;
	}

	public ReportosEstadoCuentaResponseTO decodeEstadoCuenta(String xmlRespuesta, String[] keys)throws InversionesGenericException{
		ReportosEstadoCuentaResponseTO estadoCuentaResponseTO = null;
		List<Map<String,String>> arregloMapsDetalle = new  ArrayList<Map<String,String>>();
		List<Map<String,String>> arregloMapsReportos = new  ArrayList<Map<String,String>>();
		List<Map<String,String>> arregloMapsDirecto = new  ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>();
		String[] keysAviso = {"CODIGO","DESCRIPCION"};
		MessageElement messageElement=null;

		xmlRespuesta=xmlRespuesta.replaceAll("<Salida>","");
		xmlRespuesta=xmlRespuesta.replaceAll("</Salida>","");
		int inicio = xmlRespuesta.indexOf("<?");
		int fin = xmlRespuesta.indexOf("?>") + 2;
		String encabezadoXml = xmlRespuesta.substring(inicio, fin);
		String cuerpoXml = xmlRespuesta.substring(fin);
		if(cuerpoXml.length()>0){
			xmlRespuesta = encabezadoXml + "<taz>" + cuerpoXml + "</taz>";
		}		
		
		try {
			messageElement = XMLDecode.buildXMLMessageElement( xmlRespuesta );
			String xml = messageElement.item(0).getFirstChild().getNodeValue();
			boolean valido=validaRespuesta(xml);
			if(valido){
//				String[] datos = xml.split("\\|");
					
				String[] keysEncabezado = new String[ 11 ];
				String[] keysDetalle = new String[ 12 ];
				String[] keysResumen = new String[ 7 ];
				String[] keysEstructura = new String[ 12 ];
				String[] keysMovimientosDirecto = new String[ 14 ];
				String[] keysMovimientosReporto = new String[ 12 ];
				String[] keysTotalMovimientosReporto = new String[ 2 ];
				
				int count = 0;
				for( int i = 0; i < keys.length; i++ ){
					if( ( i >= 0 ) && ( i <= 10 ) ){
						keysEncabezado[count] = keys[i];
						count ++;
					} else if( i == 11 ){
						count = 0;
						keysDetalle[count] = keys[i];
						count ++;
					} else if( ( i >= 12 ) && ( i <= 22 ) ){
						keysDetalle[count] = keys[i];
						count ++;
					} else if( i == 23 ){
						count = 0;
						keysResumen[count] = keys[i];
						count ++;
					} else if( ( i >= 24 ) && ( i <= 29 ) ){
						keysResumen[count] = keys[i];
						count ++;
					} else if( i == 30 ){
						count = 0;
						keysEstructura[count] = keys[i];
						count ++;
					} else if( ( i >= 31 ) && ( i <= 41 ) ){
						keysEstructura[count] = keys[i];
						count ++;
					} else if( i == 42 ){
						count = 0;
						keysMovimientosDirecto[count] = keys[i];
						count ++;
					} else if( ( i >= 43 ) && ( i <= 55 ) ){
						keysMovimientosDirecto[count] = keys[i];
						count ++;
					} else if( i == 56 ){
						count = 0;
						keysMovimientosReporto[count] = keys[i];
						count ++;
					} else if( ( i >= 57 ) && ( i <= 67 ) ){
						keysMovimientosReporto[count] = keys[i];
						count ++;
					} else {
						System.out.println("\n\n Existio un error al parcear la cadena de Tesorería para el Estado de Cuenta");
					} 
				}
				
				int posicion = keysMovimientosReporto.length;
				keysTotalMovimientosReporto[0] = keysMovimientosReporto[posicion-2];
				keysTotalMovimientosReporto[1] = keysMovimientosReporto[posicion-1];
				
				xml = messageElement.toString();
				
				inicio = xml.indexOf( "<Encabezado>" );
				fin = xml.indexOf( "</Encabezado>" ) + 13;
				cuerpoXml = encabezadoXml + xml.substring( inicio, fin );
				System.out.println("Encabezado: " + cuerpoXml);
				map.putAll(decode( cuerpoXml, keysEncabezado ));
				
				inicio = xml.indexOf( "<Detalle>" );
				fin = xml.indexOf( "</Detalle>" ) + 10;
				cuerpoXml = encabezadoXml + xml.substring( inicio, fin );
				messageElement = XMLDecode.buildXMLMessageElement( cuerpoXml );
				System.out.println("Detalle: " + cuerpoXml);
				arregloMapsDetalle = getMapaAtributos( messageElement, keysDetalle );				
				
				inicio = xml.indexOf( "<Resumen>" );
				fin = xml.indexOf( "</Resumen>" ) + 10;
				cuerpoXml = encabezadoXml + xml.substring( inicio, fin );
				System.out.println("Resumen: " + cuerpoXml);
				map.putAll(decode( cuerpoXml, keysResumen ));
				
				inicio = xml.indexOf( "<Estructura>" );
				fin = xml.indexOf( "</Estructura>" ) + 13;
				cuerpoXml = encabezadoXml + xml.substring( inicio, fin );
				System.out.println("Estructura: " + cuerpoXml);
				map.putAll(decode( cuerpoXml, keysEstructura ));
				
				inicio = xml.indexOf( "<MovimientosDirecto>" );
				fin = xml.indexOf( "</MovimientosDirecto>" ) + 21;
				if( ( inicio != -1 ) && ( fin != -1 ) ) {
					cuerpoXml = encabezadoXml + xml.substring( inicio, fin );
					messageElement = XMLDecode.buildXMLMessageElement( cuerpoXml );
					System.out.println("MovimientosDirecto: " + messageElement.getAsString());
					arregloMapsDirecto = getMapaAtributos( messageElement, keysMovimientosDirecto );
				}
				
				inicio = xml.indexOf( "<MovimientosReporto>" );
				fin = xml.indexOf( "</MovimientosReporto>" ) + 21;
				if( ( inicio != -1 ) && ( fin != -1 ) ) {
					cuerpoXml = encabezadoXml + xml.substring( inicio, fin );
					messageElement = XMLDecode.buildXMLMessageElement( cuerpoXml );
					Node messageElementTotales = messageElement.getElementsByTagName("Totales").item(0);
					messageElement.removeChild(messageElementTotales);
					System.out.println("MovimientosReporto: " + messageElement.getAsString());
					arregloMapsReportos = getMapaAtributos( messageElement, keysMovimientosReporto );
					arregloMapsReportos.add(decode( ( encabezadoXml + messageElementTotales.toString() ) , keysTotalMovimientosReporto ));
				}
				
				estadoCuentaResponseTO = new ReportosEstadoCuentaResponseTO(map);
				estadoCuentaResponseTO.setParametros(map);
				estadoCuentaResponseTO.setMovimientos(arregloMapsDetalle);
				estadoCuentaResponseTO.setOperacionesDirecto(arregloMapsDirecto);
				estadoCuentaResponseTO.setOperacionesReportos(arregloMapsReportos);
			}
			
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_WEB_SERVICES);				
			exceptionTO.setMessage("Lo sentimos, existió un error de comunicación con el departamento de Tesorería.");
			throw new InversionesGenericException(exceptionTO);
		} catch (InversionesGenericException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InversionesGenericException();
		}
		
		return estadoCuentaResponseTO;
	}
		
//	public List<Map<String,String>> RESPALDOdecodeEstadoCuenta(String respuesta, String[] keys)throws InversionesGenericException{
//		String[] keysAviso = {"CODIGO","DESCRIPCION"};
//		MessageElement messageElement=null;
//
//		respuesta=respuesta.replaceAll("<Salida>","");
//		respuesta=respuesta.replaceAll("</Salida>","");
//		int inicio = respuesta.indexOf("<?");
//		int fin = respuesta.indexOf("?>") + 2;
//		String encabezado = respuesta.substring(inicio, fin);
//		String parceo = respuesta.substring(fin);
//		if(parceo.length()>0){
//			respuesta = encabezado + "<taz>" + parceo + "</taz>";
//		}		
//		List<Map<String,String>> arregloMaps = new  ArrayList<Map<String,String>>();
//		try {
//			messageElement = XMLDecode.buildXMLMessageElement( respuesta );
//			respuesta=messageElement.item(0).getFirstChild().getNodeValue();
//			boolean valido=validaRespuesta(respuesta);
//			if(valido){
//				String[] datos = respuesta.split("\\|");
//				
//				if( datos.length > 2 ){
//										
//					respuesta = messageElement.toString();
//					inicio = respuesta.indexOf( "<Encabezado>" );
//					fin = respuesta.indexOf( "</Encabezado>" ) + 13;
//					encabezado = "<tas>" + respuesta.substring( inicio, fin ) + "</tas>" ;
//					messageElement = XMLDecode.buildXMLMessageElement( encabezado );
//					arregloMaps = getMapaAtributos( messageElement, keys );
//					Map<String, String> encabezadoMap = arregloMaps.get( 0 );
//
//					inicio = respuesta.indexOf( "<Resumen>" );
//					fin = respuesta.indexOf( "</Resumen>" ) + 10;
//					encabezado = "<tas>" + respuesta.substring( inicio, fin ) + "</tas>";
//					messageElement = XMLDecode.buildXMLMessageElement( encabezado ); 
//
//					String[] keysResumen = new String[ 7 ];
//					for( int i = keysResumen.length-1; i >= 0; i-- ){
//						int pos = i + 1;
//						keysResumen[ keysResumen.length - pos ] = keys[ keys.length - pos ];
//					}
//					arregloMaps = getMapaAtributos( messageElement, keysResumen );
//					encabezadoMap.putAll( arregloMaps.get( 0 ) );
//
//					inicio = respuesta.indexOf( "<Detalle>" );
//					fin = respuesta.indexOf( "</Detalle>" ) + 10;
//					encabezado = respuesta.substring( inicio, fin );
//					messageElement = XMLDecode.buildXMLMessageElement( encabezado );
//					String[] keysDetalle = new String[ 13 ];
//					keysDetalle[0] = "BASURA";
//					for( int i = 1; i < keysDetalle.length; i++ ){
//						keysDetalle[ i ] = keys[ i + 10 ];
//					}
//					arregloMaps = getMapaAtributos( messageElement, keysDetalle );
//
//					arregloMaps.add( 0, encabezadoMap );
//				}
//				else{
//					arregloMaps = getMapaAtributos( messageElement, keysAviso );
//				}
//			}
//			
//		} catch (XmlDecodeException e) {
//			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
//			exceptionTO.setLevel(LEVEL_WEB_SERVICES);				
//			exceptionTO.setMessage("Lo sentimos, existió un error de comunicación con el departamento de Tesorería.");
//			throw new InversionesGenericException(exceptionTO);
//		} catch (InversionesGenericException e) {
//			throw e;
//		} catch (Exception e) {
//			throw new InversionesGenericException();
//		}
//		
//		return arregloMaps;
//	}
	
	private List<Map<String, String>> getMapaAtributos( MessageElement messageElement, String[] keys ) throws InversionesGenericException {
		Iterator<MessageElement> it = messageElement.getChildren().iterator();
		String encabezado = "<?xml version='1.0' encoding='ISO-8859-1'?>";
		String contenido;
		Map<String, String> mapa = null;
		List<Map<String, String>> lista = new ArrayList<Map<String,String>>();
		while (it.hasNext()) {
			MessageElement me = null;
			me = it.next();
			try {
				contenido = me.getAsString().toString();
				contenido = encabezado + contenido;
				mapa = decode( contenido, keys );
				lista.add( mapa );
			} catch (Exception e) {
				throw new InversionesGenericException();
			}
		}
		return lista;
	}

	public boolean validaRespuesta(String respuesta)throws InversionesGenericException{
		boolean valido= true;
		String[] datos=respuesta.split("\\|");		
		Matcher errorMatch = Pattern.compile("^E[A-Z]*[0-9]{2}").matcher(datos[0]);
		Matcher avisoMatch = Pattern.compile("^A[A-Z]*[0-9]{2}").matcher(datos[0]);
		boolean error = errorMatch.matches();
		boolean aviso = avisoMatch.matches();
		if(error || aviso ){
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_WEB_SERVICES);
			if(error){
				exceptionTO.setMessage("Error: "+datos[1]);
				throw new InversionesGenericException(exceptionTO);
			}else{
				if(datos[0].equals(SIN_REGISTROS)){
					exceptionTO.setMessage(datos[1]);
					exceptionTO.setLevel(SIN_REGISTROS);
//					throw new InversionesGenericException(exceptionTO);
					valido = false;
				}else if(datos[0].equals(EXITO_ALTA_CLIENTE)||datos[0].equals(CLIENTE_ALTA_ANTERIIOR)){				
					valido = false;
				}else{
					exceptionTO.setMessage(datos[0] + ", " + "Aviso: "+datos[1]);
					exceptionTO.setLevel("AVISO");
					throw new InversionesGenericException(exceptionTO);
				}
			}			
		}
		return valido;
	}
	
}