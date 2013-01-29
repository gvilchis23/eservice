package com.bancoazteca.eservice.command.liberarnomia;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;
import com.bancoazteca.eservice.validator.Errors;

public class LiberarNominaForm extends FormBean{
	private String coleccion_recibos;
	private String opcion_seguridad;
	private String huella_seguridad;
	private Cipher clave_seguridad;
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
//		if(getComando().equalsIgnoreCase("SOLICITUD")){
//			if(Validator.isEmptyData(getColeccion_recibos())){
//				error.add("coleccion_recibos", CUENTAS_LIBERAR_NOMINA_COLECCION_RECIBOS_HUELLA_ERROR);
//			}
//		}
		
		if(getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					error.add(CommandBase.TAG_TOKEN, CUENTAS_LIBERAR_NOMINA_OPCION_SEGURIDAD_TOKEN_ERROR, "clave");
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					error.add(CommandBase.TAG_HUELLA, CUENTAS_LIBERAR_NOMINA_OPCION_SEGURIDAD_HUELLA_ERROR, "huella");
				}
			}else{
				error.add("opcion_seguridad",OPCION_SEGURIDAD);
			}	
		}else if(getComando().equalsIgnoreCase("EJECUCION_RECIBOS")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(clave_seguridad==null||Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					error.add("clave_seguridad",CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					error.add("huella_seguridad",HUELLA_SEGURIDAD);
				}
			}else{
				error.add("opcion_seguridad",OPCION_SEGURIDAD_ERROR);
			}
			if(Validator.isEmptyData(coleccion_recibos)){
				error.add("coleccion_recibos", CUENTAS_LIBERAR_NOMINA_COLECCION_RECIBOS_HUELLA_ERROR);
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

	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}

	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}


	public String getColeccion_recibos() {
		return coleccion_recibos;
	}

	public void setColeccion_recibos(String coleccion_recibos) {
		this.coleccion_recibos = coleccion_recibos;
	}

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
}
