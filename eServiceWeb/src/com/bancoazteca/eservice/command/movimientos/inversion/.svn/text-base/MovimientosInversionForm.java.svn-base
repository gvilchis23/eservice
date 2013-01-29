package com.bancoazteca.eservice.command.movimientos.inversion;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class MovimientosInversionForm extends FormBean {

	private String cuenta_inversion;

	public String getCuenta_inversion() {
		return cuenta_inversion;
	}

	public void setCuenta_inversion(String cuenta_inversion) {
		this.cuenta_inversion = cuenta_inversion;
	}
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(Validator.isEmptyData(cuenta_inversion)){
			error.add("cuenta_inversion", "validator.transferencias.spei.cuentas");
		}else if(!Validator.checkNumeric(cuenta_inversion)){
			error.add("cuenta_inversion", "validator.movimientos.cuenta.caracter");
		}
		return error;
	}
}