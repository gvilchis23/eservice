package com.bancoazteca.eservice.command.pagoservicios.movistar;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class PagoServiciosMovistarForm extends FormBean {
	private String numero_referencia;
	private String cuenta_cargo;
	private String importe;
	private String concepto_pago;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}
	public String getNumero_referencia() {
		return numero_referencia;
	}
	@SuppressWarnings("unused")
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	@SuppressWarnings("unused")
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	@SuppressWarnings("unused")
	public void setImporte(String importe) {
		this.importe = importe;
	}
	@SuppressWarnings("unused")
	public String getImporte() {
		return importe;
	}
	@SuppressWarnings("unused")
	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
	}
	@SuppressWarnings("unused")
	public String getConcepto_pago() {
		return concepto_pago;
	}
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if(Validator.isEmptyData(cuenta_cargo)){
				error.add("cuenta_cargo", PAGO_SERVICIOS_MOVISTAR_CUENTAS_ERROR, "cargo");
			}else{
				if(!Validator.checkNumeric(cuenta_cargo)){
					error.add("cuenta_cargo", PAGO_SERVICIOS_MOVISTAR_CUENTAS_DECIMAL_ERROR, "referencia");
				}
			}
			if(Validator.isEmptyData(numero_referencia)){
				error.add("numero_referencia", PAGO_SERVICIOS_MOVISTAR_NUMERO_REFERENCIA_ERROR, "referencia");
			}else{	
				if(!Validator.checkNumeric(numero_referencia)){
					error.add("numero_referencia", PAGO_SERVICIOS_MOVISTAR_NUMERO_REFERENCIA_DECIMAL_ERROR, "referencia");
				}			
			}
			if(Validator.isEmptyData(concepto_pago)){
				error.add("concepto", PAGO_SERVICIOS_MOVISTAR_CONCEPTO_PAGO_ERROR);
			}
			if(Validator.isEmptyData(importe)){
				error.add("importe", PAGO_SERVICIOS_MOVISTAR_IMPORTE_ERROR);
			}else{
				if(!Validator.checkDecimal(importe)){
					error.add("importe", PAGO_SERVICIOS_MOVISTAR_IMPORTE_DECIMAL_ERROR);
				}
			}
		}
		if(getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					error.add(CommandBase.TAG_TOKEN, PAGO_SERVICIOS_MOVISTAR_OPCION_SEGURIDAD_ERROR);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					error.add(CommandBase.TAG_HUELLA, PAGO_SERVICIOS_MOVISTAR_OPCION_SEGURIDAD_ERROR);
				}
			}else{
				error.add("opcion_seguridad",OPCION_SEGURIDAD);
			}	
		}
		return error;
	}
	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}
	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}
	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
	
}
