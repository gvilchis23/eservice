package com.bancoazteca.eservice.command.pagoservicios.telmex;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class PagoServiciosTelmexForm extends FormBean {
	
	private String numero_telefonico;
	private String digito_verificador;
	private String cuenta_cargo;
	private String importe;
	private String concepto_pago;

	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
		
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){			
			if(Validator.isEmptyData(numero_telefonico)){
				errores.add("numero_telefonico", PAGO_SERVICIOS_TELMEX_TELEFONO_ERROR, "telefono");
			}else if(!Validator.checkNumeric(numero_telefonico)){
				errores.add("numero_telefonico", PAGO_SERVICIOS_TELMEX_TELEFONO_NUMERICAS_ERROR, "telefono");
			}
			
			if(Validator.isEmptyData(digito_verificador)){
				errores.add("digito_verificador", PAGO_SERVICIOS_TELMEX_VERIFICADOR_ERROR, "verificador");
			}else if(!Validator.checkNumeric(numero_telefonico)){
				errores.add("digito_verificador", PAGO_SERVICIOS_TELMEX_VERIFICADOR_NUMERICAS_ERROR, "destino");
			}else if(digito_verificador.length()!=1){
				errores.add("digito_verificador", PAGO_SERVICIOS_TELMEX_VERIFICADOR_TAMANIO_ERROR, "destino");
			}
	
			if(Validator.isEmptyData(cuenta_cargo)){
				errores.add("cuenta_cargo", PAGO_SERVICIOS_TELMEX_CUENTAS_ERROR, "cargo");
			}else if(!Validator.checkNumeric(cuenta_cargo)){
					errores.add("cuenta_cargo", PAGO_SERVICIOS_TELMEX_CUENTAS_NUMERICAS_ERROR, "cargo");
			}else if(cuenta_cargo.length() != 14){
				errores.add("cuenta_cargo", PAGO_SERVICIOS_TELMEX_CUENTA_TAMANIO_ERROR);
			}
			
			if(Validator.isEmptyData(importe)){
				errores.add("importe", PAGO_SERVICIOS_TELMEX_IMPORTE_ERROR);
			}else if(!Validator.checkDecimal(importe)){
				errores.add("importe", PAGO_SERVICIOS_TELMEX_IMPORTE_DECIMAL_ERROR);
			}
			
		}else if(getComando().equalsIgnoreCase("EJECUCION")){			
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					errores.add(CommandBase.TAG_TOKEN, PAGO_SERVICIOS_TELMEX_CLAVESEGURIDAD_ERROR);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					errores.add(CommandBase.TAG_HUELLA, PAGO_SERVICIOS_TELMEX_CLAVESEGURIDAD_ERROR);
				}
			}else{
				errores.add("opcion_seguridad", PAGO_SERVICIOS_TELMEX_SEGURIDAD_ERROR);
			}	
		}
		return  errores;
	}
	
	public String getNumero_telefonico() {
		return numero_telefonico;
	}
	public void setNumero_telefonico(String numero_telefonico) {
		this.numero_telefonico = numero_telefonico;
	}
	public String getDigito_verificador() {
		return digito_verificador;
	}
	public void setDigito_verificador(String digito_verificador) {
		this.digito_verificador = digito_verificador;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getConcepto_pago() {
		return concepto_pago;
	}
	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
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

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
}