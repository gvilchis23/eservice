package com.bancoazteca.eservice.command.frecuentes.tarjeta_otrosbancos;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class AltaFrecuentesPagoTarjetaOtrosBancosForm extends FormBean{
	private String alias_beneficiario;
	private String numero_tarjeta;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	public MessageErrors validate (){
		MessageErrors error = new MessageErrors();
		
		if(Validator.isEmptyData(alias_beneficiario)){
			error.add("alias_beneficiario", PAGO_SERVICIOS_FRECUENTES_ALIAS_BENEFICIARIO_ERROR, "nuevo");
		}if(Validator.isEmptyData(CLAVE_SEGURIDAD)||Validator.checkNumeric(CLAVE_SEGURIDAD)){
			error.add("token", PAGO_SERVICIOS_FRECUENTES_TOKEN_ERROR, "nuevo");
		}
		if(Validator.isEmptyData(numero_tarjeta)){
			error.add("numero_tarjeta", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_ERROR, "");
		}else{	
			if(!Validator.checkNumeric(numero_tarjeta)){
				error.add("numero_tarjeta", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_NUMERICA_ERROR, "");
			}
		}
		return error;
	}
	
	public String getAlias_beneficiario() {
		return alias_beneficiario;
	}
	public void setAlias_beneficiario(String alias_beneficiario) {
		this.alias_beneficiario = alias_beneficiario;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}

	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}

	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}

	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}

	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	} 
	
	

}
