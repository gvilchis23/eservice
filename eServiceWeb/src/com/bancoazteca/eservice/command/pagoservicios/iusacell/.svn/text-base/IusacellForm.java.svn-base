package com.bancoazteca.eservice.command.pagoservicios.iusacell;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class IusacellForm extends FormBean{
	private String numero_referencia;
	private String cuenta_cargo;
	private String importe;
	private String concepto_pago;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;

	
	public MessageErrors validate (){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if(Validator.isEmptyData(numero_referencia)){
				error.add("numero_referencia", NUMERO_REFERENCIA_ERROR);
			}
			
			if(Validator.isEmptyData(cuenta_cargo)){
				error.add("cuenta_cargo", CUENTAS_ERROR, "cargo");
			}else{	
				if(!Validator.checkNumeric(cuenta_cargo)){
					error.add("cuenta_cargo",CUENTAS_NUMERICAS_ERROR, "cargo");
				}
			}
			
			if(Validator.isEmptyData(importe)){
				error.add("importe", IMPORTE_ERROR);
			}else{
				if(!Validator.checkDecimal(importe)){
					error.add("importe", IMPORTE_DECIMAL_ERROR);
				}
			}
			
			if(Validator.isEmptyData(concepto_pago)){
				error.add("concepto_pago", CONCEPTO_ERROR);
			}
		}
		
		if(getComando().equalsIgnoreCase("EJECUCION")){		
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					error.add("clave_seguridad", CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					error.add("clave_seguridad", CLAVE_SEGURIDAD);
				}
			}else{
				error.add("opcion_seguridad",OPCION_SEGURIDAD);
			}	
		}
		return error;
	}
	
	
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}
	public String getNumero_referencia() {
		return numero_referencia;
	}
	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getConcepto_pago() {
		return concepto_pago;
	}
	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
	}

	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}


	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}


	public String getHuella_seguridad() {
		return huella_seguridad;
	}


	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}


	
}
