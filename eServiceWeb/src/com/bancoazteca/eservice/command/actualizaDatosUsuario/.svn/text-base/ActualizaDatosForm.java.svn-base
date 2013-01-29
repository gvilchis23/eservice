package com.bancoazteca.eservice.command.actualizaDatosUsuario;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ActualizaDatosForm extends FormBean{
	
	private String pregunta_secreta;
	private Cipher respuesta_pregunta_secreta;
	private Cipher confirmar_respuesta_pregunta_secreta;
	private String email;
	private String celular;
	private String compania_celular;
	private String recibir_correos_informativos;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	
	public String getPregunta_secreta() {
		return pregunta_secreta;
	}
	public void setPregunta_secreta(String pregunta_secreta) {
		this.pregunta_secreta = pregunta_secreta;
	}
	public Cipher getRespuesta_pregunta_secreta() {
		return respuesta_pregunta_secreta;
	}
	public void setRespuesta_pregunta_secreta(Cipher respuesta_pregunta_secreta) {
		this.respuesta_pregunta_secreta = respuesta_pregunta_secreta;
	}
	public Cipher getConfirmar_respuesta_pregunta_secreta() {
		return confirmar_respuesta_pregunta_secreta;
	}
	public void setConfirmar_respuesta_pregunta_secreta(
			Cipher confirmar_respuesta_pregunta_secreta) {
		this.confirmar_respuesta_pregunta_secreta = confirmar_respuesta_pregunta_secreta;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCompania_celular() {
		return compania_celular;
	}
	public void setCompania_celular(String compania_celular) {
		this.compania_celular = compania_celular;
	}
	public String getRecibir_correos_informativos() {
		return recibir_correos_informativos;
	}
	public void setRecibir_correos_informativos(String recibir_correos_informativos) {
		this.recibir_correos_informativos = recibir_correos_informativos;
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
	
	public MessageErrors validate(){
		String commando = getComando();
		MessageErrors error = new MessageErrors();
		
		if( "VALIDACION".equalsIgnoreCase( commando ) ){
			
			if( Validator.isEmptyData( pregunta_secreta ) ){
				error.add( "pregunta_secreta", ACTUALIZA_DATOS_PREGUNTA_SECRETA_EMPTY );
			}			
			
			if( ! (Validator.isEmptyData( respuesta_pregunta_secreta.toString() ) && 
					Validator.isEmptyData( confirmar_respuesta_pregunta_secreta.toString() ) )){
				if( !respuesta_pregunta_secreta.toString().equals(confirmar_respuesta_pregunta_secreta.toString())){
					error.add( "respuesta_pregunta_secreta", ACTUALIZA_DATOS_CONFIRMACION_RESPUESTA_PREGUNTA_SECRETA );
				}
			}else{
				error.add( "respuesta_pregunta_secreta", ACTUALIZA_DATOS_RESPUESTA_PREGUNTA_SECRETA_EMPTY );
			}			
			
			
			if(!Validator.isEmptyData(email)){
				if(Validator.checkEMail(email)){
					if(email.length()>50){
						error.add( "email", ACTUALIZA_DATOS_EMAIL_INVALIDO );
					}
				}else{
					error.add( "email", ACTUALIZA_DATOS_EMAIL_INVALIDO );
				}
			}else{
				error.add( "email", ACTUALIZA_DATOS_EMAIL_EMPTY );
			}
			

			if( !Validator.isEmptyData( celular ) ){
				if(!Validator.checkNumeric(celular)){
					error.add( "celular", ACTUALIZA_DATOS_CELULAR_INVALIDO );
				}
			}else {
				error.add( "celular", ACTUALIZA_DATOS_CELULAR_EMPTY );
			}
			
			if( Validator.isEmptyData( compania_celular ) ){
				error.add( "compania_celular", ACTUALIZA_DATOS_COMPANIA_EMPTY );
			}
			
			if(!Validator.isEmptyData(recibir_correos_informativos)){
				if(!(recibir_correos_informativos.equals("true")||recibir_correos_informativos.equals("false"))){
					error.add( "recibir_correos_informativos", ACTUALIZA_DATOS_RECIBIR_EMAIL_INVALIDO );
				}
			}else{
				error.add( "recibir_correos_informativos", ACTUALIZA_DATOS_RECIBIR_EMAIL_EMPTY );
			}
		}else if(getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					error.add(CommandBase.TAG_TOKEN, PAGO_SERVICIOS_PARAMETRIZABLE_OPCION_SEGURIDAD_ERROR, "clave");
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					error.add(CommandBase.TAG_HUELLA, PAGO_SERVICIOS_PARAMETRIZABLE_OPCION_SEGURIDAD_ERROR, "huella");
				}
			}else{
				error.add("opcion_seguridad",OPCION_SEGURIDAD);
			}	
		}

		return error;
	}

}
