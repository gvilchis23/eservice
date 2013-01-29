package com.bancoazteca.eservice.command.validahuella;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ValidaHuellaForm extends FormBean{

	private String huella_seguridad;

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
		
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("EJECUCION")){			
			if(Validator.isEmptyData(huella_seguridad)){
				errores.add(CommandBase.TAG_HUELLA, HUELLA_SEGURIDAD);
			}	
		}
		return  errores;
	}
}
