package com.bancoazteca.ebanking.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class Formatter {

	private static final Logger log=Logger.getLogger(Formatter.class);
	
	public static String formatoFecha( Object fecha, String formato ){
		String salida = null;
		try{
			salida = new SimpleDateFormat( formato ).format( fecha );
		}catch( Exception e ){
			e.printStackTrace();
		}
		return salida;
	}

	public static String formatoFecha( String fecha, String formatoEntrada, String formatoSalida ){
		String salida = null;
		try{
			salida = new SimpleDateFormat( formatoSalida ).format( toDate( fecha, formatoEntrada ) );
		}catch( Exception e ){
			e.printStackTrace();
		}
		return salida;
	}

	public static String llenarPorIzq(String token, int longitud, String tipo)
	{
		if ( token.length() < longitud ) {
			StringBuffer buff = new StringBuffer();
			int i = token.length();
			while( true ){
				buff.append(tipo);
				++i;
				if( i >= longitud )
					return buff.toString() + token;
			}
		}
		else if( token.length() > longitud )
			return token.substring( 0, longitud );
		return token;
	}

	public static String llenarPorDer(String token, int longitud, String tipo)
	{
		if ( token.length() < longitud ) {
			StringBuffer buff = new StringBuffer();
			int i = token.length();
			while( true ){
				buff.append(tipo);
				++i;
				if( i >= longitud )
					return token + buff.toString();
			}
		}
		else if( token.length() > longitud )
			return token.substring( 0, longitud );
		return token;
	}
	
	public static String toText( String texto ){
		if( texto == null || texto.trim().length() == 0 )
			return "";
		return texto;
	}

	public static Date toDate( String fecha, String formato ) {
		Date data = null;
		try {
			data = new SimpleDateFormat( formato ).parse( fecha );
		} catch (Exception ex) {}
		return data;
	}

	public static String formatoXXCuenta(String cuenta) {
		int cuentaLength = cuenta.length();
		StringBuffer buffer = new StringBuffer( cuentaLength );
		for (int i = 0; i < cuentaLength - 4; ++i) {
			buffer.append("X");
			if ( ( i + 3 ) % 4 == 0 )
				buffer.append(" ");
		}
		buffer.append( cuenta.substring( cuentaLength - 4 ) );
		return buffer.toString();
	}
	
	/**
	 * Realiza el formato de un monto tipo alnova <code>000000000101</code> a monto real
	 * <code>1.01</code> separado en miles.
	 * 
	 * @param monto Monto en formato Alnova <code>000000000101</code>
	 * @return Monto en formto real 1.01 
	 */
	public static String formatMontosAlnova(String monto) {
		int i;
		try {
			i = Integer.parseInt(monto);
		} catch (NumberFormatException e) {
			i=0;
			log.error("Ocurrio error, monto no es numerico",e);
		}
		
		float k=(float)(i/100F);
		
		DecimalFormat d=new DecimalFormat("###,###,##0.00");
		String format=d.format(k);
		
		return format;
		
	}
	
	/*public static void main( String[] argas){
		System.out.println( Formatter.toDate( "2009-10-15" , "yyyy-MM-dd" ) );
		String cad = "Esta es una cadena on signo de dolar #IMPORTE#, #DIG#";
		System.out.println( "$1 000".replaceAll("\\$", "\\\\\\$") );
		cad = cad.replaceAll("#IMPORTE#", "$1 000".replaceAll("\\$", "\\\\\\$"));
		System.out.println( cad );
		System.out.println( "10\\20".replaceAll("\\\\", "\\\\\\\\") );
		cad = cad.replaceAll("#DIG#", "10\\01\\2010".replaceAll("\\\\", "\\\\\\\\"));
		System.out.println( cad );
		
		Timestamp hoy = new Timestamp(System.currentTimeMillis());
		Date hoy2 = new Date();
		SimpleDateFormat formato = new SimpleDateFormat( "mmss" );
		System.out.println( "Timestamp: " + formato.format( hoy ) );
		System.out.println( "Date: " + formato.format( hoy2 ) );
		System.out.println(""+ new Random().nextInt( 99 ) );
	}*/
}
