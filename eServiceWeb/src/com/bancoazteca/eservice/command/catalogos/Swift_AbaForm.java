package com.bancoazteca.eservice.command.catalogos;

import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class Swift_AbaForm extends FormBean {
	private String pais;
	private String ciudad;
	
	@Override
	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();		
		if (pais==null || pais.toString().trim().length()==0){
			errors.add("pais", SWIFT_ABA_PAIS_NULO);
		}
		if (ciudad==null || ciudad.toString().trim().length()==0){
			errors.add("ciudad", SWIFT_ABA_CIUDADES_NULO);
		}
		

		return errors;
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	

}
