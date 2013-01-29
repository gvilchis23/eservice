/**
 * 
 */
package com.bancoazteca.elite.ejb.transferencias;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author Paul Edgar Diaz Islas
 *
 */
public class AlnovaUtils {

	private HashMap<String, String> atributos;
	public static final int VALIDACION_LDAP = 1;
	public static final int VALIDACION_HUELLA = 2;
	public static final int VALIDACION_CONFIRMACION = 3;
	public static final int VALIDACION_TOKEN_RSA = 5;
	public static final int VALIDACION_TOKEN_HUELLA = 6;
	
	
	public AlnovaUtils(){
		atributos=new HashMap<String, String>();
	}
	
	public void addAtributo(String nombre,String valor){
		atributos.put(nombre, valor);
	}
	
	public String convertirAtributos(){
		Set<Entry<String, String>> set=atributos.entrySet();
		Iterator<Entry<String, String>> it=set.iterator();
		StringBuffer buffer=new StringBuffer();
		
		while (it.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) it.next();
			String nombre=entry.getKey();
			String valor=entry.getValue();
			buffer.append(nombre);
			buffer.append('/');
			if(valor==null){
				buffer.append("");
			}else{
				buffer.append(valor);
			}
			buffer.append('~');
		}
		
		int logitud=buffer.length();
		buffer.deleteCharAt(logitud-1);
		String cadenaAlnova=buffer.toString();
		
		return cadenaAlnova;
	}
	
	public static String formatMontoAlnova( BigDecimal monto, int digitos){
		String montoAlnova = formatMontoAlnova(monto);
		StringBuffer temp = null;
		if (montoAlnova.length() < digitos){
			temp = new StringBuffer();
			for(int i = 0; i < digitos-montoAlnova.length(); i++){
				temp.append("0");
			}
			temp.append(montoAlnova);
		}
		return temp.toString();
	}
	
	public static String formatMontoAlnova( BigDecimal monto ) {
		String temp = monto.toString();
		int idx = monto.scale();
		if( idx < 2 ) while( idx++ < 2 ) temp = temp.concat( "0" );
		idx = temp.indexOf( '.' );
		if( idx != -1 ) temp = temp.substring( 0, idx ).concat( temp.substring( idx + 1, temp.length() ) );
		return temp;
	}
	
//	public static int getTipoValidacion(ClienteVO clienteVO, BigDecimal montoOperacion){ 
//		int tipoValidacion = -1;
//		int nivelSeguridad = clienteVO.getSecurityData().getSecLevelVO().getSecurityLevel().intValue();								
//		
//		if(esValidacionHuella(clienteVO,montoOperacion)){
//			tipoValidacion = VALIDACION_HUELLA;
//		}
//		else{
//			switch(nivelSeguridad){
//				
//				case SecurityLevelVO.MOVIMEINTOS_NIVEL_ALTO: 
//						tipoValidacion = VALIDACION_HUELLA;
//						break;
//				case SecurityLevelVO.MOVIMEINTOS_NIVEL_BAJO: 
//						tipoValidacion = VALIDACION_TOKEN_RSA;
//						break;
//				case SecurityLevelVO.MOVIMEINTOS_NIVEL_CONTRASENA1:
//				case SecurityLevelVO.MOVIMEINTOS_NIVEL_CONTRASENA2:
//				case SecurityLevelVO.MOVIMEINTOS_NIVEL_CONTRASENA3:
//						tipoValidacion = VALIDACION_CONFIRMACION;
//						break;
//				case SecurityLevelVO.MOVIMEINTOS_NIVEL_MEDIO:
//						tipoValidacion = VALIDACION_TOKEN_HUELLA;
//						break;
//				default:
//						tipoValidacion = 0;//desconocido
//						break;
//						
//				
//				
//			}
//		}		
//		return tipoValidacion;
//	}
//	
//	public static boolean esValidacionHuella(ClienteVO clienteVO,BigDecimal montoOperacion){
//		boolean res = false;
//		int nivelSeguridad = clienteVO.getSecurityData().getSecLevelVO().getSecurityLevel().intValue();
//		/*Verdadero si es nivel huella*/
//		res = nivelSeguridad == SecurityLevelVO.MOVIMEINTOS_NIVEL_ALTO ;
//		/*Si no es nivel huella se verifica si el monto solicitado mas el monto acumulado del dia rebasa el limite*/
//		if(!res && montoOperacion != null){
//
//			res = !validaMonto(clienteVO.getSecurityData().getMontosVO().getSumDiarioCalculado(),
//							  NivelSeguridadData.getMontoMaximaPorDiaSinHuella(),
//							  montoOperacion);
//		}
//		return res;
//	}
	
}
