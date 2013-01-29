package com.bancoazteca.eservice.command.saldos;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class DetallesSaldoPorCuentasForm extends FormBean{
	
	private String numero_de_cuenta;
	
	public String getNumero_de_cuenta() {
		return numero_de_cuenta;
	}
	public void setNumero_de_cuenta(String numero_de_cuenta) {
		this.numero_de_cuenta = numero_de_cuenta;
	}
	
	public MessageErrors validate()
	{
		MessageErrors mensajes=new MessageErrors();
		if(Validator.isEmptyData(numero_de_cuenta)){
			mensajes.add("numero_de_cuenta", "Proporcione un parametro: como numero de cuenta, cuentas concatenadas por un arroba, o un asterisco.");
		}
		return mensajes;
	}
}
