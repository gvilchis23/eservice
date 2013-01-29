package com.bancoazteca.eservice.command.traspasos.propias;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class TraspasosPropiasForm extends FormBean{
	private String cuenta_cargo;
	private String cuenta_destino;
	private String concepto;
	private String importe;
	private String valor_submit;
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("EJECUCION")){			
			if(Validator.isEmptyData(cuenta_cargo)){
				error.add("cuenta_cargo",CUENTAS_ERROR, "cargo");
			}else{	
				if(!Validator.checkNumeric(cuenta_cargo)){
					error.add("cuenta_cargo", CUENTAS_NUMERICAS_ERROR, "cargo");
				}
			}
			if(Validator.isEmptyData(cuenta_destino)){
				error.add("cuenta_destino", CUENTAS_ERROR, "destino");
			}else{	
				if(!Validator.checkNumeric(cuenta_destino)){
					error.add("cuenta_destino",CUENTAS_NUMERICAS_ERROR, "destino");
				}			
			}
			
			if(Validator.isEmptyData(concepto)){
				error.add("concepto", CONCEPTO_ERROR);
			}
			
			if(Validator.isEmptyData(importe)){
				error.add("importe", IMPORTE_ERROR);
			}else{
				if(!Validator.checkDecimal(importe)){
					error.add("importe", IMPORTE_DECIMAL_ERROR);
				}
			}
		}
		return error;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getCuenta_destino() {
		return cuenta_destino;
	}
	public void setCuenta_destino(String cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getValor_submit() {
		return valor_submit;
	}
	public void setValor_submit(String valor_submit) {
		this.valor_submit = valor_submit;
	}
	
}
