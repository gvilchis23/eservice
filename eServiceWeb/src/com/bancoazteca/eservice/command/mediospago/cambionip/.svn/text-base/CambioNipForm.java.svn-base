package com.bancoazteca.eservice.command.mediospago.cambionip;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class CambioNipForm extends FormBean{
	private String numero_tarjeta;
	private Cipher nuevo_nip;
	private Cipher confirma_nuevo_nip;
	private Cipher actual_nip;
	
	private String opcion_seguridad; 
	private Cipher clave_seguridad;
	private String huella_seguridad;
	
	
	
	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();
		boolean flag=true;
		if (getComando().equalsIgnoreCase("VALIDACION")) {
			if(actual_nip == null || Validator.isEmptyData(actual_nip.string)){
				errors.add("actual_nip", VALIDATOR_NIP_REQUERIDO);
				flag=false;
			}else{
				if(actual_nip.string.length()!=4 || !Validator.checkNumeric(actual_nip.string)){
					errors.add("actual_nip", VALIDATOR_NIP_NUMERIC);
					flag=false;
				}
			}
			if(nuevo_nip == null || Validator.isEmptyData(nuevo_nip.string)){
				errors.add("nuevo_nip", VALIDATOR_NIP_REQUERIDO);
				flag=false;
			}else{
				if(nuevo_nip.string.length()!=4 || !Validator.checkNumeric(nuevo_nip.string)){
					errors.add("nuevo_nip", VALIDATOR_NIP_NUMERIC);
					flag=false;
				}
			}
			if(confirma_nuevo_nip == null || Validator.isEmptyData(confirma_nuevo_nip.string)){
				errors.add("confirma_nuevo_nip", VALIDATOR_NIP_REQUERIDO);
				flag=false;
			}else{
				if(confirma_nuevo_nip.string.length()!=4 || !Validator.checkNumeric(confirma_nuevo_nip.string)){
					errors.add("confirma_nuevo_nip", VALIDATOR_NIP_NUMERIC);
					flag=false;
				}
			}
			if(flag){
				if(!nuevo_nip.string.equals(confirma_nuevo_nip.string)){
					errors.add("confirma_nuevo_nip", VALIDATOR_CONFIRMACION_NIP);
				}else{
					if(nuevo_nip.string.equals(actual_nip.string)){
						errors.add("nuevo_nip", VALIDATOR_NUEVO_NIP_SIN_CAMBIOS);
					}
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

	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}

	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}

	public Cipher getNuevo_nip() {
		return nuevo_nip;
	}

	public void setNuevo_nip(Cipher nuevo_nip) {
		this.nuevo_nip = nuevo_nip;
	}

	public Cipher getConfirma_nuevo_nip() {
		return confirma_nuevo_nip;
	}

	public void setConfirma_nuevo_nip(Cipher confirma_nuevo_nip) {
		this.confirma_nuevo_nip = confirma_nuevo_nip;
	}

	public Cipher getActual_nip() {
		return actual_nip;
	}

	public void setActual_nip(Cipher actual_nip) {
		this.actual_nip = actual_nip;
	}


}
