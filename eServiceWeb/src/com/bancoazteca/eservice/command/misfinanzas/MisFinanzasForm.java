package com.bancoazteca.eservice.command.misfinanzas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;


public class MisFinanzasForm extends FormBean {
	private static final Logger log = Logger.getLogger(MisFinanzasForm.class);
	private String numero_cuenta;
	private String fecha_inicial;
	private String fecha_final;
	private String rango;
		
	private String FORMATDATE = "dd-MM-yyyy";
	
	public MessageErrors validate(){
		String commando = getComando();
		MessageErrors error = new MessageErrors();
		
		if( commando.equalsIgnoreCase( "EJECUCION" ) ){
			if(Validator.isEmptyData(numero_cuenta)){
				error.add("numero_cuenta", VALIDATOR_CONSULTA_MISFINANZAS_CUENTA_EMPTY);	
			}
			if(rango != null){
				if(Validator.isEmptyData(rango)){
					error.add("rango", VALIDATOR_CONSULTA_MISFINANZAS_RANGO_EMPTY);	
				}								
			}else{			
				Date fchInicial = null;
				Date fchFinal = null;
				Date fchHoy = new Date();
				
				if(Validator.isEmptyData(fecha_inicial)){
					error.add("fecha_inicial", VALIDATOR_CONSULTA_MISFINANZAS_FECHA_INICIAL_EMPTY);
				}else{
					if(fecha_inicial.contains("/")){
						String fechaInicial = fecha_inicial.replace("/", "-");
						setFecha_inicial(fechaInicial);
					}
					
					if(! Validator.checkFecha(fecha_inicial, FORMATDATE)){
						error.add("fecha_inicial", VALIDATOR_CONSULTA_MISFINANZAS_FECHA_FORMATO_ERROR);
					}else{
						try {
							fchInicial = getDateFormat(fecha_inicial);
							if(fchInicial.after(fchHoy)){
								error.add("fecha_inicial", VALIDATOR_CONSULTA_MISFINANZAS_FECHA_ACTUAL_COMPARACION_ERROR);
							}
						} catch (ParseException e) {							
							error.add("fecha_inicial", VALIDATOR_CONSULTA_MISFINANZAS_FECHA_FORMATO_ERROR);
							e.printStackTrace();
						}
					}
				}			
								
				if(Validator.isEmptyData(fecha_final)){
					error.add("fecha_final", VALIDATOR_CONSULTA_MISFINANZAS_FECHA_FINAL_EMPTY);
				}else{
					if(fecha_final.contains("/")){
						String fechaFinal = fecha_final.replace("/", "-");
						setFecha_final(fechaFinal);
					}
					
					if(! Validator.checkFecha(fecha_final, FORMATDATE)){
						error.add("fecha_final", VALIDATOR_CONSULTA_MISFINANZAS_FECHA_FORMATO_ERROR);
					}else{
						try {
							fchFinal = getDateFormat(fecha_final);							
							if(fchFinal.after(fchHoy)){
								error.add("fecha_final", VALIDATOR_CONSULTA_MISFINANZAS_FECHA_ACTUAL_COMPARACION_ERROR);
							}
						} catch (ParseException e) {
							e.printStackTrace();
							error.add("fecha_final", VALIDATOR_CONSULTA_MISFINANZAS_FECHA_FORMATO_ERROR);
						}
					}
				}	
				
				if(fchInicial != null && fchFinal != null){
					if(fchInicial.after(fchFinal)){
						error.add("fechas", VALIDATOR_CONSULTA_MISFINANZAS_FECHAS_COMPARACION_ERROR);
					}
				}
			}				
		}
		return error;
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

	public String toString(){
		StringBuffer valuesForm = new StringBuffer("Fecha inicial: --> "+fecha_inicial);
		valuesForm.append("\n Fecha final --> "+ fecha_final);
		valuesForm.append("\n Rango -->"+ rango);
		valuesForm.append("\n numero cuenta --> "+ numero_cuenta);
		
		return valuesForm.toString();
	}
	
	private Date getDateFormat(String fecha) throws ParseException{		
		SimpleDateFormat df=new SimpleDateFormat(FORMATDATE);
		df.setLenient(false);
		Date fch = df.parse(fecha);	
		
		return fch;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}


	public String getNumero_cuenta() {
		return numero_cuenta;
	}


	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	
}
