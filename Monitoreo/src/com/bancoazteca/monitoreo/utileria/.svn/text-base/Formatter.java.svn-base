package com.bancoazteca.monitoreo.utileria;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;




/**
 * @author Banco Azteca S.A. [JFAV] Septiembre 12, 2008.
 * 
 * En esta clase se deber&aacute; concentrar todos los formateos de Banca Elite
 * para texto, n&uacute;meros y fechas. Además de contener las constantes
 * auxiliares para la aplicaci&oacite;n como pueden d&iacute;s, meses y
 * a&ntilde;os.
 */
public class Formatter {
	
//	private static final Logger $log = Logger.getLogger(Formatter.class);
	
	private static DecimalFormat decimalFormat = null;
	
	private static DecimalFormat decimalFormatSimple = null;
	
	public static final Collection<String> DIAS = getDias();

	public static final Collection<String> MESES = getMeses();

	public static final Collection<String> ANIOS = getAnios();

	public static String FECHA_ACTUAL = getFechaActual();
	
	final static String NBSP = "&nbsp;";
	
	static {
		decimalFormat = new DecimalFormat( "###,###,##0.00" );
		decimalFormatSimple = new DecimalFormat( "########0.00" );
	}
	

	private static Collection<String> getDias() {
		Collection<String> dias = new ArrayList<String>();
		for (int i = 0; i <= 31; i++) {
			dias.add(String.valueOf(i));
		}
		return dias;
	}

	private static final Collection<String> getMeses() {
		Collection<String> meses = new ArrayList<String>();
		String[] $meses = { "Enero", "Ferbero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
		for (String string : $meses) {
			meses.add(string);
		}
		return meses;
	}

	private static Collection<String> getAnios() {

		return null;
	}

	private static String formatDayMonthYear(Date fecha) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat sDF = new SimpleDateFormat(pattern);
		return sDF.format(fecha);
	}

	private static String getFechaActual() {
		return formatDayMonthYear(new Date());
	}
	
	public static String formatCuenta( String cuenta ) {
		if( (cuenta != null) && (cuenta.length() == 20) )
			return cuenta.substring( 4, 8 ).concat( cuenta.substring( 10, 20 ) );
		else if(cuenta != null && cuenta.length() == 14)
			return cuenta;
		else if (cuenta != null && cuenta.length() < 14){
			StringBuffer temp = new StringBuffer();
			for(int i = 0; i < 14-cuenta.length(); i++){
				temp.append("0");
			}
			temp.append(cuenta);
			return temp.toString();
		}
		return NBSP;
	}
	
	public static double conversionMontoAlnovaJava(String montoAlnova){
		double montoConvertido = 0.0;
		
		if( ( montoAlnova != null ) && ( montoAlnova.length() > 1 ) ){
			int longitud = montoAlnova.length();
			String decimales = montoAlnova.substring(longitud-2, longitud);
			String enteros = montoAlnova.substring(0, longitud-2);
			String montoParceado = enteros + "." + decimales;
			montoConvertido = Double.parseDouble(montoParceado);
		}		
		return montoConvertido;
	}
	
	public static String formatMonto( BigInteger monto ) {		
		StringBuffer buffer = new StringBuffer(decimalFormat.format(monto));
		if (buffer.length() >= 4) {
			buffer.deleteCharAt(buffer.length() -1 );
			buffer.deleteCharAt(buffer.length() -1 );
			buffer.deleteCharAt(buffer.length() -1 );
		}
		if( monto != null ) {
			
			if( monto.compareTo(BigInteger.ZERO) < 0 ) {				
				buffer.insert( 0, "<font color=\"red\">" );
				buffer.append( "</font>" );
			}
		}
		return buffer.toString();
	}
	
	public static String formatMontoPesos( double monto ) {
		StringBuffer buffer = new StringBuffer( "$ " + decimalFormat.format( monto ) );
		if( monto < 0 ) {
			buffer.insert( 0, "<font color=\"red\">" );
			buffer.append( "</font>" );
		}
		return buffer.toString();
	}
	
	public static String formatMontoPesos( String importe ) {
		if((importe!=null)&&(importe.length()>0)){
			Double monto=Double.parseDouble(importe);
			Locale LOCALE_MX = new Locale("es","mx");
			NumberFormat numberFormat=DecimalFormat.getInstance(LOCALE_MX);
			numberFormat.setMinimumFractionDigits(2);
			
			importe = new String( "$ " + numberFormat.format( monto ) );
			if( monto < 0 ) {
				importe = new String( "$ " + numberFormat.format( 0.00 ) );
			}
		}
		return importe;
	}
	
	public static String formatMonto( double monto ) {
		StringBuffer buffer = new StringBuffer( decimalFormat.format( monto ) );
		if( monto < 0 ) {
			buffer.insert( 0, "<font color=\"red\">" );
			buffer.append( "</font>" );
		}
		return buffer.toString();
	}

	public static String formatMonto( BigDecimal monto ) {
		StringBuffer buffer = new StringBuffer("0.00");
		if(monto!=null){
			buffer = new StringBuffer( decimalFormatSimple.format( monto ) );
		}		

		return buffer.toString();
	}
	
	public static String formatMontoTruncado( String monto ) {
		StringBuffer buffer = new StringBuffer("0.00");
		if(!Validator.isEmptyData(monto)){
			if(Validator.checkNumericBalance(monto)){
				Double importe = new Double(monto);
				buffer = new StringBuffer( decimalFormatSimple.format( importe ) );
			}
		}
		return buffer.toString();
	}

	public static String formatSplittedCuenta( String cuenta ){
		String cuentaFormateada = formatCuenta(cuenta);
		if(cuentaFormateada.equals("&nbsp;")){
			return formatAccountAnotherBank( cuenta );
		}else{
			return split4CharsTokens( cuentaFormateada );
		}
		
	}
	
	public static String formatSplittedCuentaClabe( String cuentaClabe ){
		return split4CharsTokens( cuentaClabe );
	}
	
	public static String formatSplittedTarjeta( String tarjeta ) {
		return split4CharsTokens( tarjeta );
	}
	
	public static String formatXXCuenta(String cuenta){
		int cuentaLength = cuenta.length();
		StringBuffer buffer = new StringBuffer(cuentaLength);
		for(int i = 0; i < cuentaLength - 4; i++){
			buffer.append("X");
			if( ( (i+3) % 4) == 0 ){
				buffer.append(" ");
			}
		}
		buffer.append(cuenta.substring(cuentaLength-4));
		return buffer.toString();			
	}
	
	public static String formatXXTarjeta(String tarjeta){
		tarjeta = removeSpaces(tarjeta);		
		int tarjetaLength = tarjeta.length();
		StringBuffer buffer = new StringBuffer(tarjetaLength);
		for(int i = 0; i < tarjetaLength - 4; i++){
			buffer.append("X");
			if( ( (i+1) % 4) == 0 ){
				buffer.append(" ");
			}
		}
		buffer.append(tarjeta.substring(tarjetaLength-4));
		return buffer.toString();			
	}
	
	public static String split4CharsTokens(String param){
		try {
			if( param != null ) {
				if( param.length() == 16 ) {
					return param.substring(0,4)+" "+param.substring(4,8)+" "+
						param.substring(8,12)+" "+param.substring(12,16);
				} else if ( param.length() == 14 ){
					return param.substring(0,4)+" "+param.substring(4,8)+" "+
						param.substring(8,12)+" "+param.substring(12,14);	
				} else if ( param.length() == 17 && param.indexOf(" ")!=-1){
					return param.substring(0,4)+" "+param.substring(4,6)+
						param.substring(7,9)+" "+param.substring(9,13)+" "+
						param.substring(13,17);
				} else if ( param.length() == 18 ){
					return param.substring(0,4)+" "+param.substring(4,8)+ " " +
						param.substring(8,12)+" "+param.substring(12,16)+" "+
						param.substring(16,18);
				} else{
					return param;
				}
			} else {
				return "";
			}
		} catch(Exception e){
//			$log.info("Formatter falla en split4CharsTokens ->"+
//				param+" "+e.getMessage());
			return param;
		}
	}
	
	public static String removeSpaces( String item ) {
		if (item == null)
			return "";
		char[] chs = item.toCharArray();
		StringBuffer cta = new StringBuffer();
		for ( int i=0;i<chs.length;i++ ){
			if ( !Character.isSpaceChar( chs[i] ) ){
				cta.append(chs[i]);
			}
		}
		return cta.toString();
	}
	
	public static String removeSpacesLeftRight( String item ) {
		if (item == null){
			return "";
		}
		item = item.replaceAll("^( )*", "");
		item = item.replaceAll("( )*$", "");
		
		return item;
	}
	
	/*
	public static boolean checkEMail(String data){
		boolean flag = false;		
		if (data != null && data.trim().length()!=0){
			try {
				RE re = new RE("^[a-zA-Z](-|[a-zA-Z0-9\\._])*[a-zA-Z0-9]@[a-zA-Z0-9](-|[a-zA-Z0-9\\._])*[a-zA-Z0-9][\\.][a-zA-Z](-|[a-zA-Z0-9\\._])*[a-zA-Z]$");
				flag = re.match(data.trim());
			} catch (RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	*/
	
	public static String getReportName(String cuenta, String periodo){
		StringBuffer fileName = new StringBuffer();
		String separador = "_";
		String extension = ".csv";
		
		fileName.append("EdoCta");
		fileName.append(separador);
		fileName.append(cuenta.substring(14, 17));
		fileName.append(separador);
		String[] array = periodo.split(" ");
		fileName.append(formatSimpleDate(array[0]));
		fileName.append(separador);
		fileName.append(formatSimpleDate(array[2]));
		fileName.append(extension);
		
		return fileName.toString();
	}
	
	private static String formatSimpleDate(String fecha){
		String[] cads = fecha.split("-");
		String mes = cads[1];
		String numero = "";
		
		if (mes.equalsIgnoreCase("enero"))					
			numero = "01";
		if (mes.equalsIgnoreCase("febrero"))					
			numero = "02";
		if (mes.equalsIgnoreCase("marzo"))					
			numero = "03";
		if (mes.equalsIgnoreCase("abril"))					
			numero = "04";
		if (mes.equalsIgnoreCase("mayo"))					
			numero = "05";
		if (mes.equalsIgnoreCase("junio"))					
			numero = "06";
		if (mes.equalsIgnoreCase("julio"))					
			numero = "07";
		if (mes.equalsIgnoreCase("agosto"))					
			numero = "08";
		if (mes.equalsIgnoreCase("septiembre"))					
			numero = "09";
		if (mes.equalsIgnoreCase("octubre"))					
			numero = "10";
		if (mes.equalsIgnoreCase("noviembre"))					
			numero = "11";
		if (mes.equalsIgnoreCase("diciembre"))					
			numero = "12";	
		return cads[0] + "-" + numero + "-" + cads[2];
	}
	

	private static String formatAccountAnotherBank(String param){
		try {
			if( param != null ) {
				int tamañoParam = param.length();
				int index = 0;
				String bloque = "";
				String formatParam = "";
				while (index < (tamañoParam-1)) {
					if((index+4) < (tamañoParam-1)){
						bloque = param.substring(index, (index+4)) + " ";
					}else{
						bloque = param.substring(index, tamañoParam);
					}
					formatParam = formatParam + bloque;
					index = index + 4;
				}
				return formatParam;
			} else {
				return "";
			}
		} catch(Exception e){
//			$log.info("Formatter falla en split4CharsTokens ->"+
//				param+" "+e.getMessage());
			return param;
		}
	}
	
	public static String formatDate(String date){

		try{
		
			if(date == null){
				return date;
			}else if(date.length() != 10 ){
				if(date.length()!=9){
					return date;
				}
			}
			String anio, dia;
			int mes;
			if(date.charAt(2) == '-'){
				dia = date.substring(0,2);
				mes = Integer.parseInt(date.substring(3,5));
				anio = date.substring(6,10);
			}else{
				anio = date.substring(0, 4);
				if(date.length()==10){
					mes = Integer.parseInt(date.substring(5, 7));
					dia = date.substring(8, 10);
				}else{
					mes = Integer.parseInt(date.substring(5, 6));
					dia = date.substring(8, 9);
				}
				
			}

			switch(mes){
			case 1:{
				return dia+" Ene "+anio;
			}case 2:{
				return dia+" Feb "+anio;
			}case 3:{
				return dia+" Mar "+anio;
			}case 4:{
				return dia+" Abr "+anio;
			}case 5:{
				return dia+" May "+anio;
			}case 6:{
				return dia+" Jun "+anio;
			}case 7:{
				return dia+" Jul "+anio;
			}case 8:{
				return dia+" Ago "+anio;
			}case 9:{
				return dia+" Sep "+anio;
			}case 10:{
				return dia+" Oct "+anio;
			}case 11:{
				return dia+" Nov "+anio;
			}case 12:{
				return dia+" Dic "+anio;
			}default:{
				return date;
			}
			}
		}catch(Exception e){
			return date;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String formatoFechaHora(String fechaUltimoMovimiento){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");		
					
		String format = sdf.format(new Date(fechaUltimoMovimiento));
		
		String hora = fechaUltimoMovimiento.substring(11, 16);
        String meridiano = (Integer.parseInt(hora.substring(0,2))<= 12 ? "AM" : "PM");                       
          
        fechaUltimoMovimiento = formatDate(format.substring(0, 10))+ " " + hora + " " + meridiano;			
		
		return fechaUltimoMovimiento;
		
	}
	
	public static String[] periodoMesActual(){
		
		String fechas[]= new String[2];
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/");
		String fechaHoy= formato.format(fechaActual);
		String pimerDiaMes = formato2.format(fechaActual)+"01";
		fechas[0]=pimerDiaMes;
		fechas[1]=fechaHoy;
		
		return fechas;
	}
	
	public static String formatoFechaReportos(String fecha){
		String fechaFormateada = "";
		String[] token = fecha.split("/");
		int anio = 100 + Integer.parseInt(token[0]);
		int mes = Integer.parseInt(token[1])-1;
		int dia = Integer.parseInt(token[2]);
		Date date = new Date(anio, mes, dia);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		fecha = simpleDateFormat.format(date);
		fechaFormateada = formatDate(fecha);
		return fechaFormateada; 
	}
	
	public static String getCodigoErrorAlnova(String respuestaAlnova){
		int inicio = respuestaAlnova.indexOf("(");
		int fin = respuestaAlnova.indexOf(")")+1;
		String codigo = respuestaAlnova.substring(inicio, fin);
		return codigo;
	}
	
	public static String getMontoTruncado(String monto, int numeroDecimales){
		String montoSeparado[] = monto.split("[.]");
		if(montoSeparado.length>1){
			if(montoSeparado[1].length() > numeroDecimales){
				monto = montoSeparado[0]+"."+montoSeparado[1].substring(0,numeroDecimales);
			}			
		}else{
			String decimales = "";
			for(int i=0; i<numeroDecimales; i++){
				decimales += "0";
			}
			monto = monto+"."+decimales;
		}
		return monto;
	}
	
	public static String formatMonto(String importe) {
		if ((importe != null) && (importe.length() > 0)) {
						
			String montoSeparado[] = importe.split("[.]");
			
			int entero = Integer.parseInt(montoSeparado[0]);
			Locale LOCALE_MX = new Locale("es", "mx");
			NumberFormat numberFormat = DecimalFormat.getInstance(LOCALE_MX);

			importe = new String("$ " + numberFormat.format(entero));
			
			if (montoSeparado.length > 1) {
				importe += "." + montoSeparado[1];
			} else{
				importe += ".00";
			}
			
		}
		return importe;
	}
	/**
	 * @author
	 * Formato para fecha ej: de 15  NOVIEMBRE 2009 A 15 Nov 2009
	 */public static String formatoFecha(String fecha){		
		 	fecha = fecha.replaceAll("\t", " ");
		 	fecha = fecha.replaceAll(" ( )* ", " ");
			String fechaArreglo[]= fecha.split(" ");
			String mes=fechaArreglo[1].substring(0,3);
			fecha=fechaArreglo[0]+" "+mes.substring(0,1)+mes.substring(1,3).toLowerCase()+" "+fechaArreglo[2];
			return fecha;
			
		}
	 
	 	public static String formatFecha(String format,Date date){
	 		SimpleDateFormat sdf= new  SimpleDateFormat(format);
	 		return sdf.format(date);
	 	}
	public static void main(String[] args) {
		
		Formatter formatter = new Formatter();
		
//		String periodo = "23-Marzo-2009 al 22-Abril-2009";
//		String cuenta = "XX XXXX XXXX 0326    GUARDADITO VIST";				
//		System.out.println(getReportName(cuenta,periodo));
//	
//		
//		
//		String solicitud = "       Diana Berenice Camacho       ";
//		String result = formatter.removeSpacesLeftRight(solicitud);
//		System.out.println("Original: <" + solicitud + "> fin");
//		System.out.println("resultado: <" + result + "> fin");
//		
//		System.out.println("monto string 159850000.65987845136: "  + formatter.formatMontoPesos("159850000.67835468743657"));
//		
//
//		System.out.println("codigo Alnova: " + formatter.getCodigoErrorAlnova("Error messages:(PEE0001) PERSONA INEXISTENTE"));
//		
//		String monto = "0.0";
//		System.out.println("monto: " + monto);
//		System.out.println("monto truncado: " + formatter.getMontoTruncado(monto, 2));
//		
//		
//		String fecha = "06-11-2009";
//		System.out.println("fecha formateada: " + formatter.formatDate(fecha));
//		
//		DateFormat d=DateFormat.getDateInstance(DateFormat.FULL);
//		System.out.println("fecha reportos: " + d.format(new Date()));
//		
//		
//		String varw= "15  NOVIEMBRE 2009";
//		System.out.println("fecha otros: " + formatoFecha(varw));

//		BigDecimal saldo_al_corte = new BigDecimal(45990.52999999999883584678173065185546875);
//		BigDecimal credito_disponible =new BigDecimal(2282.670000000000072759576141834259033203125);
//		BigDecimal pago_para_no_generar_intereses =new BigDecimal(43990.52999999999883584678173065185546875);
//		BigDecimal retenido =null;
//		BigDecimal credito_usado =new BigDecimal("");
//		
		System.out.println("Formato de fecha"+ Formatter.formatFecha("dd 'de' MMMM 'de' yyyy",new Date()));
//		System.out.println("formatMontoTruncado credito_disponible =" + formatter.formatMonto(credito_disponible));
//		System.out.println("formatMontoTruncado pago_para_no_generar_intereses =" + formatter.formatMonto(pago_para_no_generar_intereses));
//		System.out.println("formatMontoTruncado retenido =" + formatter.formatMonto(retenido));
//		System.out.println("formatMontoTruncado credito_usado =" + formatter.formatMonto(credito_usado));
		
//		System.out.println("******************");
//		
//		saldo_al_corte ="Pato";
//		credito_disponible ="";
//		pago_para_no_generar_intereses =null;
//		retenido ="·~€";
//		
//		System.out.println("formatMontoPesos saldo_al_corte =" + formatter.formatMontoTruncado(saldo_al_corte));
//		System.out.println("formatMontoPesos credito_disponible =" + formatter.formatMontoTruncado(credito_disponible));
//		System.out.println("formatMontoPesos pago_para_no_generar_intereses =" + formatter.formatMontoTruncado(pago_para_no_generar_intereses));
//		System.out.println("formatMontoTruncado retenido =" + formatter.formatMontoTruncado(retenido));
//		
//		
		String monto = null;
		//45878.0
		formatter.conversionMontoAlnovaJava(monto);
		
	}
	
	
   
	
}
