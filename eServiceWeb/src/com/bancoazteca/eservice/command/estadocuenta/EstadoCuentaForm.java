package com.bancoazteca.eservice.command.estadocuenta;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EstadoCuentaForm extends FormBean {
	private String cuenta;
	private String periodo;
	private String anio_periodo;
	private String mes_periodo;
	
	
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getPeriodo() {
		return periodo;
	}
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equals("VALIDACION")){
			if(Validator.isEmptyData(cuenta)){
				error.add("cuenta", ESTADO_CUENTA_ERROR, "cuenta");
			}
			if(!Validator.isEmptyData(anio_periodo))
			if(!Validator.checkNumeric(anio_periodo)){
				error.add("anio_periodo", ESTADO_CUENTA_ANIO_VALIDATION, "anio_periodo");
			}
		}
		if(getComando().equals("EJECUCION")){
			if(Validator.isEmptyData(anio_periodo) && Validator.isEmptyData(mes_periodo) &&  Validator.isEmptyData(periodo)){
				error.add("periodo", ESTADO_CUENTA_PERIODO_ERROR,"periodo");
			}		
		}
		
		return error;
	}
	public String getAnio_periodo() {
		return anio_periodo;
	}
	public void setAnio_periodo(String anio_periodo) {
		this.anio_periodo = anio_periodo;
	}
	public String getMes_periodo() {
		return mes_periodo;
	}
	public void setMes_periodo(String mes_periodo) {
		this.mes_periodo = mes_periodo;
	}
	
	

}
