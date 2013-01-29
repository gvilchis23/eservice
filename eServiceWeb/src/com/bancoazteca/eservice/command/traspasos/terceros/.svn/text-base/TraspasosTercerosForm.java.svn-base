package com.bancoazteca.eservice.command.traspasos.terceros;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class TraspasosTercerosForm extends FormBean{
	private Cipher clave_seguridad;
	private String cuenta_cargo;
	private String cuenta_destino;
	private String concepto;
	private String referencia;
	private String importe;
	
	private String opcion_seguridad; 
	private String huella_seguridad; 
	
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){			
			if(Validator.isEmptyData(cuenta_destino)){
				errores.add("cuenta_destino", TRASPASOS_TERCEROS_CUENTAS_ERROR, "destino");
			}else{	
				if(!Validator.checkNumeric(cuenta_destino)){
					errores.add("cuenta_destino", TRASPASOS_TERCEROS_CUENTAS_NUMERICAS_ERROR, "destino");
				}
			}
	
			if(Validator.isEmptyData(cuenta_cargo)){
				errores.add("cuenta_cargo", TRASPASOS_TERCEROS_CUENTAS_ERROR, "cargo");
			}else if(!Validator.checkNumeric(cuenta_cargo)){
					errores.add("cuenta_cargo", TRASPASOS_TERCEROS_CUENTAS_NUMERICAS_ERROR, "cargo");
			}else if(cuenta_cargo.length() != 14){
				errores.add("cuenta_cargo", TRASPASOS_TERCEROS_CUENTA_TAMANIO_ERROR);
			}
			
			if(Validator.isEmptyData(concepto)){
				errores.add("concepto", TRASPASOS_TERCEROS_CONCEPTO_ERROR);
			}
			
			if(Validator.isEmptyData(importe)){
				errores.add("importe", TRASPASOS_TERCEROS_IMPORTE_ERROR);
			}else{
				if(!Validator.checkDecimal(importe)){
					errores.add("importe", TRASPASOS_TERCEROS_IMPORTE_DECIMAL_ERROR);
				}
			}			
		}else if(getComando().equalsIgnoreCase("EJECUCION")){
			
			if(opcion_seguridad.equalsIgnoreCase("TOKEN")){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					errores.add("clave_seguridad",TRASPASOS_TERCEROS_CLAVESEGURIDAD_ERROR);
				}
			}else if(opcion_seguridad.equalsIgnoreCase("HUELLA")){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					errores.add("huella_seguridad",HUELLA_SEGURIDAD);
				}
			}else{
					errores.add("opcion_seguridad", TRASPASOS_TERCEROS_SEGURIDAD_ERROR);
			}
		}
		return  errores;
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

	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getCuenta_destino() {
		return cuenta_destino;
	}
	public void setCuenta_destino(String cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
}