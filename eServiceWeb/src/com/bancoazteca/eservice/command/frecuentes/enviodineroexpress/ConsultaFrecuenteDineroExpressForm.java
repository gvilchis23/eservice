package com.bancoazteca.eservice.command.frecuentes.enviodineroexpress;

import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ConsultaFrecuenteDineroExpressForm extends FormBean{
	private String indice_alfabetico;
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();	
		return error;
	}

	public String getIndice_alfabetico() {
		return indice_alfabetico;
	}

	public void setIndice_alfabetico(String indice_alfabetico) {
		this.indice_alfabetico = indice_alfabetico;
	}

}
