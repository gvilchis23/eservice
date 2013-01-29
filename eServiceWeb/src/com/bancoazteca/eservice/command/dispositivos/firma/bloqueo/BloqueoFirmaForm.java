package com.bancoazteca.eservice.command.dispositivos.firma.bloqueo;

import com.bancoazteca.elite.ejb.dispositivos.util.TipoOperacionTokenEnum;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class BloqueoFirmaForm extends FormBean{

	private String numero_serie;
	private Cipher clave_seguridad;

	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){			
			if(Validator.isEmptyData(numero_serie)){
				errores.add("numero_serie", BLOQUEO_FIRMA_NUMERO_SERIE);
			}
			else if(!Validator.checkNumeric(numero_serie)){
				errores.add("numero_serie", BLOQUEO_FIRMA_NUMERO_SERIE_DECIMAL);
			}
		}
		if(getComando().equalsIgnoreCase("EJECUCION")){
			if (TipoOperacionTokenEnum.DESBLOQUEAR.toString().equalsIgnoreCase(getTipo_operacion())){
				if ( clave_seguridad == null || Validator.isEmptyData(clave_seguridad.toString())) {
					errores.add("numero_token", BLOQUEO_TOKEN);
				}
			}
		}
		return  errores;
	}

	public String getNumero_serie() {
		return numero_serie;
	}

	public void setNumero_serie(String numero_serie) {
		this.numero_serie = numero_serie;
	}

	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}

	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}

}
