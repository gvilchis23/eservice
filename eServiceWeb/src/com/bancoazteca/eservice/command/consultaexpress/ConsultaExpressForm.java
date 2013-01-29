package com.bancoazteca.eservice.command.consultaexpress;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ConsultaExpressForm extends FormBean{

	private String action;
	private String cuenta;
	private String tarjeta;
	private String nip;
	private String numero_token;
	private String numero_token_confirmacion;
	private String identificador_sesion;
	private String opcion_seguridad; 
	private Cipher clave_seguridad;
	private String huella_seguridad;
	
	
	
	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();
		boolean flag=true;
		if (getComando().equalsIgnoreCase("VALIDACION")) {
			if(nip == null || Validator.isEmptyData(nip)){
				errors.add("nip", VALIDATOR_NIP_REQUERIDO);
				flag=false;
			}else{
				if(nip.length()!=4 || !Validator.checkNumeric(nip)){
					errors.add("nip", VALIDATOR_NIP_NUMERIC);
					flag=false;
				}
			}
		
		
			
		}else if (getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					errors.add("clave_seguridad",CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					errors.add("huella_seguridad",HUELLA_SEGURIDAD);
				}
			}else{
				errors.add("opcion_seguridad",OPCION_SEGURIDAD);
			}
		}
		return errors;
	}
	
	
	
	
	
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}



	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
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

	
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	
	public String getNumero_token() {
		return numero_token;
	}
	public void setNumero_token(String numero_token) {
		this.numero_token = numero_token;
	}
	public String getNumero_token_confirmacion() {
		return numero_token_confirmacion;
	}
	public void setNumero_token_confirmacion(String numero_token_confirmacion) {
		this.numero_token_confirmacion = numero_token_confirmacion;
	}
	public String getIdentificador_sesion() {
		return identificador_sesion;
	}
	public void setIdentificador_sesion(String identificador_sesion) {
		this.identificador_sesion = identificador_sesion;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}
