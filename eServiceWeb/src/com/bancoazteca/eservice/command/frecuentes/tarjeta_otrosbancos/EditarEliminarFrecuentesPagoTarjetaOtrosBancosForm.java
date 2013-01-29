package com.bancoazteca.eservice.command.frecuentes.tarjeta_otrosbancos;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EditarEliminarFrecuentesPagoTarjetaOtrosBancosForm extends FormBean{
	private String numero_tarjeta_actual;
	private String numero_tarjeta_nuevo;
	private String alias_beneficiario_nuevo;
	private String numero_tarjeta;
	
	public MessageErrors validate (){
		MessageErrors error = new MessageErrors();
		if(getIdservicio().equalsIgnoreCase("EDITAR_FRECUENTE")){
			if(Validator.isEmptyData(numero_tarjeta_actual)){
				error.add("numero_tarjeta_actual", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_ERROR, "actual");								
			}else{
				if(!Validator.checkNumeric(numero_tarjeta_actual)) 
					error.add("numero_tarjeta_actual", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_NUMERICA_ERROR, "actual");
			}
			
			if(!Validator.isEmptyData(numero_tarjeta_nuevo) && !Validator.isEmptyData(alias_beneficiario_nuevo)){
				error.add("numero_tarjeta_nuevo, alias_beneficiario_nuevo", PAGO_SERVICIOS_FRECUENTES_NO_POSIBLE_ERROR);
			}
			
			if(Validator.isEmptyData(numero_tarjeta_nuevo) && Validator.isEmptyData(alias_beneficiario_nuevo)){
				error.add("numero_tarjeta_nuevo", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_ERROR, "nuevo");
				error.add("alias_beneficiario_nuevo", PAGO_SERVICIOS_FRECUENTES_ALIAS_BENEFICIARIO_ERROR, "nuevo");
			}
			
			if(!Validator.isEmptyData(numero_tarjeta_nuevo)){
				if(!Validator.checkNumeric(numero_tarjeta_nuevo)) 
					error.add("numero_tarjeta_nuevo", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_NUMERICA_ERROR, "nuevo");
			}
			
		}else if(getIdservicio().equalsIgnoreCase("ELIMINAR_FRECUENTE")){
			if(Validator.isEmptyData(numero_tarjeta)){
				error.add("numero_tarjeta", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_ERROR, "");
			}else{	
				if(!Validator.checkNumeric(numero_tarjeta)){
					error.add("numero_tarjeta", PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_NUMERICA_ERROR, "");
				}
			}
		}
			
		return error;
	}

	public String getNumero_tarjeta_actual() {
		return numero_tarjeta_actual;
	}

	public void setNumero_tarjeta_actual(String numero_tarjeta_actual) {
		this.numero_tarjeta_actual = numero_tarjeta_actual;
	}

	public String getNumero_tarjeta_nuevo() {
		return numero_tarjeta_nuevo;
	}

	public void setNumero_tarjeta_nuevo(String numero_tarjeta_nuevo) {
		this.numero_tarjeta_nuevo = numero_tarjeta_nuevo;
	}

	public String getAlias_beneficiario_nuevo() {
		return alias_beneficiario_nuevo;
	}

	public void setAlias_beneficiario_nuevo(String alias_beneficiario_nuevo) {
		this.alias_beneficiario_nuevo = alias_beneficiario_nuevo;
	}

	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}

	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}
	
	
}
