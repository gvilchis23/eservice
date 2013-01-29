package com.bancoazteca.eservice.command.pagoservicios.tiempoaire;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;


public class Tiempo_AireForm extends FormBean{
	
	//private static final String PAGO_SERVICIOS_COMPRA_TIEMPO_AIRE_CUENTA_CARGO_ERROR = null;
	private String carrier;
	private String idCuenta;
	private String numero_telefonico;
	private String monto;
	private String cuenta_cargo;
	private String referencia; 
	private String numeroCuenta;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	public void reset(){
		carrier=null;
		idCuenta=null;
		numero_telefonico=null;
		monto=null;
		cuenta_cargo=null;
		referencia = null;
		clave_seguridad = null;
		opcion_seguridad = null;
	}
	
	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}

	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}

	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}

	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getNumero_telefonico() {
		return numero_telefonico;
	}

	public void setNumero_telefonico(String numero_telefonico) {
		this.numero_telefonico = numero_telefonico;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCuenta_cargo() {
		return cuenta_cargo;
	}

	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	
	public MessageErrors validate() {
		MessageErrors error = new MessageErrors();	
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			
			if(Validator.isEmptyData(cuenta_cargo)){
				error.add("cuenta_cargo", PAGO_SERVICIOS_COMPRA_TIEMPO_AIRE_CUENTA_CARGO);
			}else{	
				if(!Validator.checkNumeric(cuenta_cargo)){
					error.add("cuenta_cargo", PAGO_SERVICIOS_COMPRA_TIEMPO_AIRE_CUENTA_CARGO_NUMERICA_ERROR);
				}
			}
			
			if(Validator.isEmptyData(monto)){
				error.add("importe", PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_MONTO_ERROR);
			}			
			else{
				if(!Validator.checkDecimal(monto)){
					error.add("importe", PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_MONTO_DECIMAL_ERROR);
				}
			}			
			
			if(Validator.isEmptyData(numero_telefonico)){
				error.add("numero_telefonico", PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_NUMERO_TELEFONICO_ERROR);
			}
			else{
				if(!Validator.checkNumeric(numero_telefonico)){
					error.add(numero_telefonico, PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_NUMERO_TELEFONICO_DECIMAL_ERROR);
				}
			}
			
			if(Validator.isEmptyData(carrier)){
				error.add("carrier", PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CARRIER_ERROR);
			}
		}
		if(getComando().equalsIgnoreCase("EJECUTAR")){			
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(clave_seguridad == null || Validator.isEmptyData(clave_seguridad.toString())){
					error.add(CommandBase.TAG_TOKEN, CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad)){
					error.add(CommandBase.TAG_HUELLA, HUELLA_SEGURIDAD);
				}
			}else{
				error.add("opcion_seguridad", OPCION_SEGURIDAD);
			}	
		}
		
		return error;
	}

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
	
}
