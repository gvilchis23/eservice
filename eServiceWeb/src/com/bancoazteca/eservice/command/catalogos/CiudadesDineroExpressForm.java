package com.bancoazteca.eservice.command.catalogos;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class CiudadesDineroExpressForm extends FormBean{
	
	private String pais;
	private String estado;
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();	

		if (Validator.isEmptyData(pais)){
			error.add("pais", VALIDATOR_DINERO_EXPRESS_PAIS);
		}
		if (Validator.isEmptyData(estado)){
			error.add("estado", VALIDATOR_DINERO_EXPRESS_ESTADO);
		}
	
	   return error;
	}

}
