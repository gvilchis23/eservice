package com.bancoazteca.eservice.command.movimientos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class MovimientosForm extends FormBean{
	
	private String numero_cuenta;
	private String numero_tarjeta;
	private String fecha_inicial;
	private String fecha_final;
	private String rango;
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		if(getIdservicio().equals("MOVIMIENTOS")){
			if(Validator.isEmptyData(numero_cuenta)){
				error.add("numero_cuenta", MOVIMIENTOS_NUMERO_CUENTA_ERROR);
			}else if(!Validator.checkNumeric(numero_cuenta)){
				error.add("numero_cuenta", MOVIMIENTOS_NUMERO_CUENTA_NUMERICO_ERROR);
			}else if(numero_cuenta.length() != 14){
				error.add("numero_cuenta", MOVIMIENTOS_NUMERO_CUENTA_TAMANIO_ERROR);
			}
		}
		if(getIdservicio().equals("MOVIMIENTOS_INFINITE")){
			if(Validator.isEmptyData(numero_tarjeta)){
				error.add("numero_tarjeta", MOVIMIENTOS_NUMERO_CUENTA_ERROR);
			}else if(!Validator.checkNumeric(numero_tarjeta)){
				error.add("numero_tarjeta", MOVIMIENTOS_NUMERO_CUENTA_NUMERICO_ERROR);
			}	
		}		
		return error;
	}
		
//	private String FORMATDATE = "dd-MM-yyyy";
//	
//	public MessageErrors validate(){
//		String commando = getComando();
//		MessageErrors error = new MessageErrors();
//		if( commando.equalsIgnoreCase( "EJECUCION" ) ){
//			if(Validator.isEmptyData(numero_cuenta)){
//				error.add("numero_cuenta", MOVIMIENTOS_NUMERO_CUENTA_ERROR);
//			}else if(!Validator.checkNumeric(numero_cuenta)){
//				error.add("numero_cuenta", MOVIMIENTOS_NUMERO_CUENTA_NUMERICO_ERROR);
//			}else if(numero_cuenta.length() != 14){
//				error.add("numero_cuenta", MOVIMIENTOS_NUMERO_CUENTA_TAMANIO_ERROR);
//			}
//			
//			
//			if(rango != null){
//				if(Validator.isEmptyData(rango)){
//					error.add("rango", VALIDATOR_CONSULTA_MOVIMIENTOS_RANGO_EMPTY);	
//				}								
//			}else{			
//				Date fchInicial = null;
//				Date fchFinal = null;
//				Date fchHoy = new Date();
//				
//				if(Validator.isEmptyData(fecha_inicial)){
//					error.add("fecha_inicial", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_INICIAL_EMPTY);
//				}else{
//					if(fecha_inicial.contains("/")){
//						String fechaInicial = fecha_inicial.replace("/", "-");
//						setFecha_inicial(fechaInicial);
//					}
//					
//					if(! Validator.checkFecha(fecha_inicial, FORMATDATE)){
//						error.add("fecha_inicial", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_FORMATO_ERROR);
//					}else{
//						try {
//							fchInicial = getDateFormat(fecha_inicial);
//							if(fchInicial.after(fchHoy)){
//								error.add("fecha_inicial", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_ACTUAL_COMPARACION_ERROR);
//							}
//						} catch (ParseException e) {							
//							error.add("fecha_inicial", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_FORMATO_ERROR);
//							e.printStackTrace();
//						}
//					}
//				}			
//								
//				if(Validator.isEmptyData(fecha_final)){
//					error.add("fecha_final", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_FINAL_EMPTY);
//				}else{
//					if(fecha_final.contains("/")){
//						String fechaFinal = fecha_final.replace("/", "-");
//						setFecha_final(fechaFinal);
//					}
//					
//					if(! Validator.checkFecha(fecha_final, FORMATDATE)){
//						error.add("fecha_final", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_FORMATO_ERROR);
//					}else{
//						try {
//							fchFinal = getDateFormat(fecha_final);							
//							if(fchFinal.after(fchHoy)){
//								error.add("fecha_final", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_ACTUAL_COMPARACION_ERROR);
//							}
//						} catch (ParseException e) {
//							e.printStackTrace();
//							error.add("fecha_final", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_FORMATO_ERROR);
//						}
//					}
//				}	
//				
//				if(fchInicial != null && fchFinal != null){
//					if(fchInicial.after(fchFinal)){
//						error.add("fechas", VALIDATOR_CONSULTA_MOVIMIENTOS_FECHAS_COMPARACION_ERROR);
//					}
//				}
//			}				
//		}
//		
//		if(getIdservicio().equals("MOVIMIENTOS_INFINITE")){
//			if(Validator.isEmptyData(numero_tarjeta)){
//				error.add("numero_tarjeta", MOVIMIENTOS_NUMERO_CUENTA_ERROR);
//			}else if(!Validator.checkNumeric(numero_tarjeta)){
//				error.add("numero_tarjeta", MOVIMIENTOS_NUMERO_CUENTA_NUMERICO_ERROR);
//			}	
//		}	
//		
//		
//	
//	
//		return error;
//	}

	public String getNumero_cuenta() {
		return numero_cuenta;
	}

	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}

	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}

	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}

	public String getFecha_inicial() {
		return fecha_inicial;
	}

	public void setFecha_inicial(String fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}

	public String getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	
//	private Date getDateFormat(String fecha) throws ParseException{		
//		SimpleDateFormat df=new SimpleDateFormat(FORMATDATE);
//		df.setLenient(false);
//		Date fch = df.parse(fecha);	
//		
//		return fch;
//	}


}
