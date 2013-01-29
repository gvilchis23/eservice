package com.bancoazteca.eservice.command.frecuentes.tarjeta_azteca;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class AltaFrecuentesPagoTarjetaAztecaForm extends FormBean{
	private String alias;
	private String numero_tarjeta;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	private String email;
	private String mensaje;
	
	public MessageErrors validate (){
		MessageErrors error = new MessageErrors();	
	   if (getComando().equalsIgnoreCase("validacion")) {
		   
		if(Validator.isEmptyData(alias)){
			error.add("alias", PAGO_SERVICIOS_FRECUENTES_ALIAS_BENEFICIARIO_ERROR, "");
		}
		if(Validator.isEmptyData(numero_tarjeta)){
			error.add("numero_tarjeta", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_ERROR, "");
		}else{	
			if(!Validator.checkNumeric(numero_tarjeta)){
				error.add("numero_tarjeta", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_NUMERICA_ERROR, "");
			}
		} 
		 }
		return error;
	}
	

	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	} 

}
