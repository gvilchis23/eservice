package com.bancoazteca.eservice.command.catalogos;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EstadosDineroExpresForm extends FormBean{
	
	private String pais;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	
	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();	

		if (Validator.isEmptyData(pais)){
			error.add("pais", VALIDATOR_DINERO_EXPRESS_PAIS);
		}
	
	   return error;
	}
}
