package com.bancoazteca.eservice.command.otroscreditos;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class OtrosCreditosForm  extends FormBean{
	
	private String method_credito;
	private String numero_credito;

	
	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();
		
		if(Validator.isEmptyData(method_credito)){
			error.add("method_credito","validator.movimientos_otros_creditos.metodo.requerido");
		}
		if(Validator.isEmptyData(numero_credito)){
			error.add("numero_credito","validator.movimientos_otros_creditos.numero_credito.requerido");
		}
		
		
		
		return error;
	}
		
	
	
	
	
	public String getMethod_credito() {
		return method_credito;
	}

	public void setMethod_credito(String method_credito) {
		this.method_credito = method_credito;
	}

	public String getNumero_credito() {
		return numero_credito;
	}

	public void setNumero_credito(String numero_credito) {
		this.numero_credito = numero_credito;
	}

}
