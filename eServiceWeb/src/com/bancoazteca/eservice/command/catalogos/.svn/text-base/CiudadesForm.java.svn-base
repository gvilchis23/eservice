package com.bancoazteca.eservice.command.catalogos;

import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class CiudadesForm extends FormBean{

	private String pais;
	
	@Override
	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();

		if (pais==null || pais.toString().trim().length()==0){
			errors.add("pais", CATALOGOS_CIUDADES_PAIS_NULO);
		}
		

		return errors;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
}
