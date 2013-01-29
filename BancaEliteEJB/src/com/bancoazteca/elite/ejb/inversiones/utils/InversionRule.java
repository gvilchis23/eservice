package com.bancoazteca.elite.ejb.inversiones.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import com.bancoazteca.elite.util.Validator;



public class InversionRule {
	/**
	 * Constante monto minimo <code>primitive  <b>int</b></code> MIN_AMOUNT = 100000
	 */
	public static final int MIN_AMOUNT = 100000;
	/**
	 * Constante monto maximo <code>primitive  <b>int</b></code> MAX_AMOUNT = 1000000000
	 */
	public static final int MAX_AMOUNT = 1000000000;
	/**
	 * Constante de formato <code>java.lang.String</code> de fecha DD/MM/YYYY.
	 */
	public static final String FORMAT_DATE_DD_MM_YYYY_SLASH = "dd/MM/yyyy";
	/**
	 * Constante de formato <code>java.lang.String</code> de fecha YYYY/MM/DD.
	 */
	public static final String FORMAT_DATE_YYYY_MM_DD = "yyyy/MM/dd";
	/**
	 * Constante de formato <code>java.lang.String</code> de fecha DD-MM-YYYY.
	 */
	public static final String FORMAT_DATE_DD_MM_YYYY_DASH = "dd-MM-yyyy";
	/**
	 * Constante de formato <code>java.lang.String</code> de fecha YYYY-MM-DD.
	 */
	public static final String FORMAT_DATE_YYYY_MM_DD_DASH = "yyyy-MM-dd";
	/**
	 * Constante de formato <code>java.lang.String</code> de fecha DD/MM/YYYY_HH:mm.
	 */
	public static final String FORMAT_DATE_DD_MM_YYYY_HH_mm_SLASH = "dd/MM/yyyy_HH:mm";
	/**
	 * Constante de formato <code>java.lang.String</code> de fecha DD/MM/YYYY_HH:mm.
	 */
	public static final String FORMAT_DATE_DD_MM_YYYY_HH_mm_DASH = "dd-MM-yyyy_HH:mm";
	
	public static final String FORMAT_DATE_YYYYMMDD= "yyyyMMdd";
	
	
	public static boolean validaFecha(final String fecha,final String formato)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		Date testDate = null;
		if(Validator.isEmptyData(fecha)){
			return false;
		}
		try{
			testDate = sdf.parse(fecha);
		}catch (ParseException e){
			return false;
		}
		
		if (!sdf.format(testDate).equals(fecha)) {
			return false;
		}
		return true;
	}
	
	
	public static boolean validaMonto(final String monto,final int montominimo){
		
		if(Validator.isEmptyData(monto)){
			return false;
		}
		Double montoNum;
		try{
			montoNum=Double.parseDouble(monto);
		}catch(NumberFormatException ex){
			return false;
		}
		if(montoNum<montominimo){
			return false;
		}
		return true;
	}
	
	public static boolean validaPlazo(final String plazo,final Integer... plazos){
		
		if(Validator.isEmptyData(plazo)){
			return false;
		}
		int plazoNum;
		try{
			plazoNum=Integer.parseInt(plazo);
		}catch(NumberFormatException ex){
			return false;
		}
		for (Integer integer : plazos) {
			if(plazoNum==integer){
				return true;
			}
		}
		return false;
	}
	
	public static boolean validaCuentaAlfanumerica(String cuenta,int longitud){
		
		if(Validator.isEmptyData(cuenta)){
			return false;
		}
		if(longitud!=cuenta.length()){
			return false;
		}
		return true;
	}
	
	public static boolean validaCorreoElectronico(String correo){
		boolean flag = false;		
		if (correo != null && correo.trim().length()!=0){
			try {
				RE re = new RE("^[a-zA-Z](-|[a-zA-Z0-9\\._])*[a-zA-Z0-9]@[a-zA-Z0-9](-|[a-zA-Z0-9\\._])*[a-zA-Z0-9][\\.][a-zA-Z](-|[a-zA-Z0-9\\._])*[a-zA-Z]$");
				flag = re.match(correo.trim());
			} catch (RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static boolean validaSoloCaracter(String dato,int longitud)
	{	
		if(Validator.isEmptyData(dato)){
			return false;	
		}
		if(!Validator.checkOnlyText(dato)){
			return false;
		}
		if(dato.length()>longitud){
			return false;
		}
		return true;
	}
	
	public static boolean validaSoloAlfanumericos(String dato,int longitud)
	{
		if(Validator.isEmptyData(dato)){
			return false;
		}
		if(!Validator.checkAlphanumeric(dato)){
			return false;
		}
		if(dato.length()>longitud){
			return false;
		}
		return true;
	}
	
	public static boolean validaSoloNumericos(String dato,int longitud)
	{
		if(Validator.isEmptyData(dato)){
			return false;
		}
		if(!Validator.checkNumeric(dato)){
			return false;
		}
		if(dato.length()>longitud){
			return false;
		}
		return true;
	}
	
	public static String formateaMontoRetencion(String monto){
		
		String montoSeparado[] = monto.split("[.]");
		if(montoSeparado.length>1){
			monto = agragaCerosIzq(montoSeparado[0],14)+agragaCerosDer(montoSeparado[1],2).substring(0,2);
		}else
			monto = agragaCerosIzq(montoSeparado[0],14)+"00";
		return monto;
	}
	
	public static String formateaPorcentaje(String porcentaje){
		String porcentajeSeparado[] = porcentaje.split("[.]");
		if(porcentajeSeparado.length>1){
			porcentaje = agragaCerosIzq(porcentajeSeparado[0],3)+agragaCerosDer(porcentajeSeparado[1],2).substring(0,2);
		}else
			porcentaje = agragaCerosIzq(porcentajeSeparado[0],3)+"00";
		return porcentaje;
	}
	
	public static String agragaCerosIzq(String dato, int ceros){
		
		while(dato.length()<ceros){
			dato = "0"+dato;
		} 
		return dato;
		
	}
   public static String agragaCerosDer(String dato, int ceros){
		
		while(dato.length()<ceros){
			dato+= "0";
		} 
		return dato;
		
	}
   public static void main(String[] args) throws ParseException {
	   System.out.println(getReferencia(null));
   }
   /**
    * Método que obtiene un objeto <code>java.lang.String </code> que contiene el 
    * numero de cliente obtiene un objeto <code>java.lang.String</code> con el 
    * numero de referencia unica asignada para la cuenta.
    * @param 
    * 	Un objeto <code>java.lang.String </code> con el valor del numero del cliente
    * @return 
    * 	Un objeto <code>java.lang.String </code> con el formato NUMERO_CLIENTE_DD/MM/YYYY_HH:mm<br>
    * 	null si el objeto numero de cliente viene null 
    * @throws ParseException 
    * 	en caso de que la operacion no tenga exito. 
    */
   public static String getReferencia(String numeroCliente) {
	   try {
		   StringBuffer buffer=new StringBuffer(numeroCliente);
		   buffer.append(formatFecha(new Date(), FORMAT_DATE_DD_MM_YYYY_HH_mm_DASH));
		   return buffer.toString();
	   }catch(Exception exception){
		   exception.printStackTrace();
	   }
	   return null;
   }

	/**
	 * Método que convierte un objeto <code>java.util.Date</code> a un
	 * <code>java.lang.String</code> en el formato deseado formato.
	 * 
	 * @param date
	 *            El <code>java.util.Date</code> a convertir.
	 * @param format
	 *            El formato en que se desea convertir a
	 *            <code>java.lang.String</code> la fecha.
	 * @return Un objeto <code>java.lang.String</code> en el formato
	 *         especificado.
	 * @throws ParseException
	 *             En caso de que la operación no tenga éxito.
	 */
	public static String formatFecha(Date date, final String format)
			throws ParseException {
		SimpleDateFormat _format = new SimpleDateFormat(format, new Locale("es", "MX"));
		return _format.format(date);
	}
}
