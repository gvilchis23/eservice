package com.bancoazteca.eservice.command.frecuentes.tarjeta_azteca;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EliminarFrecuentesPagoTarjetaAztecaForm extends FormBean{

	private String numero_tarjeta;
	private String alias;
	private String banco;
	private String tipo_tarjeta;
	
	

	public MessageErrors validate (){
		MessageErrors error = new MessageErrors();
		if(getIdservicio().equalsIgnoreCase("eliminar_frecuentes_pago_tarjeta_azteca")){
			if(Validator.isEmptyData(numero_tarjeta)){
				error.add("numero_tarjeta_actual", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_ERROR, "actual");								
			}else{
				if(!Validator.checkNumeric(numero_tarjeta)) 
					error.add("numero_tarjeta_actual", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_NUMERICA_ERROR, "actual");
			}
					
			if(!Validator.isEmptyData(numero_tarjeta)){
				if(!Validator.checkNumeric(numero_tarjeta)) 
					error.add("numero_tarjeta_nuevo", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_NUMERICA_ERROR, "nuevo");
			}	
		}
		return error;
	}

	

	

	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}

	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}

	

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}





	public String getBanco() {
		return banco;
	}





	public void setBanco(String banco) {
		this.banco = banco;
	}





	public String getTipo_tarjeta() {
		return tipo_tarjeta;
	}





	public void setTipo_tarjeta(String tipo_tarjeta) {
		this.tipo_tarjeta = tipo_tarjeta;
	}

	
	
	
}
