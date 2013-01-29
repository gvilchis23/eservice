package com.bancoazteca.eservice.command.admonSeguridad.changePassword;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;
import com.bancoazteca.eservice.beans.Cipher;

public class ChangePasswordForm extends FormBean{

	private static final long serialVersionUID = 6443747532846141116L;
	
	private Cipher contrasenia_actual;
	private Cipher nueva_contrasenia;
	private Cipher confirmacion_nueva_contrasenia;
	
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	
		
	public MessageErrors validate(){
		String commando = getComando();
		MessageErrors error = new MessageErrors();
		
		if( "VALIDACION".equalsIgnoreCase( commando ) ){
			if( Validator.isEmptyData( contrasenia_actual.toString() ) ){
				error.add( "contrasenia_actual", CHANGE_PASSWORD_OLD_EMPTY );
			}
			if( Validator.isEmptyData( nueva_contrasenia.toString() ) ){
				error.add( "nueva_contrasenia", CHANGE_PASSWORD_NEW_EMPTY );
			}
			if( Validator.isEmptyData( confirmacion_nueva_contrasenia.toString() ) ){
				error.add( "confirmacion_nueva_contrasenia", CHANGE_PASSWORD_NEW_CONFIRMATION_EMPTY );
			}
			if(! (confirmacion_nueva_contrasenia.toString().equals(nueva_contrasenia.toString())) ){
				error.add( "nueva_contrasenia", CHANGE_PASSWORD_NEW_CONFIRMATION_ERROR);
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



	public Cipher getContrasenia_actual() {
		return contrasenia_actual;
	}



	public void setContrasenia_actual(Cipher contrasenia_actual) {
		this.contrasenia_actual = contrasenia_actual;
	}



	public Cipher getNueva_contrasenia() {
		return nueva_contrasenia;
	}



	public void setNueva_contrasenia(Cipher nueva_contrasenia) {
		this.nueva_contrasenia = nueva_contrasenia;
	}



	public Cipher getConfirmacion_nueva_contrasenia() {
		return confirmacion_nueva_contrasenia;
	}



	public void setConfirmacion_nueva_contrasenia(
			Cipher confirmacion_nueva_contrasenia) {
		this.confirmacion_nueva_contrasenia = confirmacion_nueva_contrasenia;
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
