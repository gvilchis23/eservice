package com.bancoazteca.eservice.command.login;

import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ApplicationLoginForm extends FormBean{
	
	
	private String aplicacion;
	private Cipher certificado;	
	
	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();		
		if(aplicacion == null || aplicacion.trim().length() == 0){
			error.add("aplicacion", LOGIN_APLICACION_ERROR);
		}
	
		if (certificado==null || certificado.toString().trim().length()==0){
			error.add("certificado", LOGIN_APLICACION_CERTIFICADO_ERROR);
		}
		return error;
	}
	
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public Cipher getCertificado() {
		return certificado;
	}
	public void setCertificado(Cipher certificado) {
		this.certificado = certificado;
	}
	
	
}
