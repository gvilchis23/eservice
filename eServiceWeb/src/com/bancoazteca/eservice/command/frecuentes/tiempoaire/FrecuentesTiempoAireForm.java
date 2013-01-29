package com.bancoazteca.eservice.command.frecuentes.tiempoaire;


import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class FrecuentesTiempoAireForm extends FormBean {
	
	private String compania;
	private String alias;
	private String referencia;
	private String numero_telefonico;
	private String numero_telefonico_actual;
	private String numero_telefonico_nuevo;
	private String index;
	private String tipo_operacion;

	
	
	public String getNumero_telefonico_actual() {
		return numero_telefonico_actual;
	}
	public void setNumero_telefonico_actual(String numero_telefonico_actual) {
		this.numero_telefonico_actual = numero_telefonico_actual;
	}
	public String getNumero_telefonico_nuevo() {
		return numero_telefonico_nuevo;
	}
	public void setNumero_telefonico_nuevo(String numero_telefonico_nuevo) {
		this.numero_telefonico_nuevo = numero_telefonico_nuevo;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String carrier) {
		this.compania = carrier;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getNumero_telefonico() {
		return numero_telefonico;
	}
	public void setNumero_telefonico(String numero_telefonico) {
		this.numero_telefonico = numero_telefonico;
	}
	public String getTipo_operacion() {
		return tipo_operacion;
	}
	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}
	
	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();
		String comando = getComando();
		
		
		if(getIdservicio().equalsIgnoreCase("mostrar_frecuentes_tiempo_aire")){
			if(comando.equalsIgnoreCase("EJECUCION")){
				if(Validator.isEmptyData(getCompania())){
					errors.add("compania",PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CARRIER_ERROR);
				}
			}
		}else if (getIdservicio().equalsIgnoreCase("alta_frecuentes_tiempo_aire")) {
			if(comando.equalsIgnoreCase("EJECUCION")){
				if(Validator.isEmptyData(getCompania())){
					errors.add("compania",PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CARRIER_ERROR);
				}
				if(Validator.isEmptyData(getAlias())){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_VACIO);
				}else if(!Validator.checkOnlyText(getAlias())){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_ERROR);
				}else if(getAlias().length() > 30){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_LONGITUD);
				}
				if(Validator.isEmptyData(getNumero_telefonico())){
					errors.add("numero_telefonico",ERROR_TELEFONO_FRECUENTE_VACIO);
				}else if(!Validator.checkNumeric(getNumero_telefonico())){
					errors.add("numero_telefonico",ERROR_TELEFONO_NO_NUMERICO);
				}else if(getNumero_telefonico().length() != 10){
					errors.add("numero_telefonico",ERROR_LONGITUD_NUMERO_TELEFONICO);
				}
			}
		}else if (getIdservicio().equalsIgnoreCase("eliminar_frecuente_tiempo_aire")) {
			if(comando.equalsIgnoreCase("VALIDACION")){
				if(Validator.isEmptyData(getCompania())){
					errors.add("compania",PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CARRIER_ERROR);
				}
				if(Validator.isEmptyData(getAlias())){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_VACIO);
				}else if(!Validator.checkOnlyText(getAlias())){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_ERROR);
				}else if(getAlias().length() > 30){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_LONGITUD);
				}
				if(Validator.isEmptyData(getNumero_telefonico())){
					errors.add("numero_telefonico",ERROR_TELEFONO_FRECUENTE_VACIO);
				}else if(!Validator.checkNumeric(getNumero_telefonico())){
					errors.add("numero_telefonico",ERROR_TELEFONO_NO_NUMERICO);
				}else if(getNumero_telefonico().length() != 10){
					errors.add("numero_telefonico",ERROR_LONGITUD_NUMERO_TELEFONICO);
				}
			}else if(comando.equalsIgnoreCase("VALIDACION")){
				if(Validator.isEmptyData(getCompania())){
					errors.add("compania",PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CARRIER_ERROR);
				}
			}
		}else if (getIdservicio().equalsIgnoreCase("modificar_frecuente_tiempo_aire")) {
			if(comando.equalsIgnoreCase("VALIDACION")){
				if(Validator.isEmptyData(getCompania())){
					errors.add("compania",PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CARRIER_ERROR);
				}
				if(Validator.isEmptyData(getAlias())){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_VACIO);
				}else if(!Validator.checkOnlyText(getAlias())){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_ERROR);
				}else if(getAlias().length() > 30){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_LONGITUD);
				}
				if(Validator.isEmptyData(getNumero_telefonico())){
					errors.add("numero_telefonico",ERROR_TELEFONO_FRECUENTE_VACIO);
				}else if(!Validator.checkNumeric(getNumero_telefonico())){
					errors.add("numero_telefonico",ERROR_TELEFONO_NO_NUMERICO);
				}else if(getNumero_telefonico().length() != 10){
					errors.add("numero_telefonico",ERROR_LONGITUD_NUMERO_TELEFONICO);
				}
			}else if(comando.equalsIgnoreCase("EJECUCION")){
				if(Validator.isEmptyData(getCompania())){
					errors.add("compania",PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CARRIER_ERROR);
				}
				if(Validator.isEmptyData(getAlias())){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_VACIO);
				}else if(!Validator.checkOnlyText(getAlias())){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_ERROR);
				}else if(getAlias().length() > 30){
					errors.add("alias",VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_LONGITUD);
				}
				if(Validator.isEmptyData(getNumero_telefonico())){
					errors.add("numero_telefonico",ERROR_TELEFONO_FRECUENTE_VACIO);
				}else if(!Validator.checkNumeric(getNumero_telefonico())){
					errors.add("numero_telefonico",ERROR_TELEFONO_NO_NUMERICO);
				}else if(getNumero_telefonico().length() != 10){
					errors.add("numero_telefonico",ERROR_LONGITUD_NUMERO_TELEFONICO);
				}
			}
		}	
		return errors;
	}
}
