package com.bancoazteca.eservice.command.donativos;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class DonativosForm extends FormBean {
	private String cuenta_cargo;
	private BigDecimal importe;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
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
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if(Validator.isEmptyData(cuenta_cargo)){
				error.add("cuenta_cargo", DONATIVOS_CUENTAS_ERROR, "cargo");
			}else{
				if(!Validator.checkNumeric(cuenta_cargo)){
					error.add("cuenta_cargo", DONATIVOS_CUENTAS_DECIMAL_ERROR, "referencia");
				}
			}						
			if(Validator.isEmptyData(importe.toString())){
				error.add("importe", DONATIVOS_IMPORTE_ERROR);
			}else{
				if(!Validator.checkDecimal(importe.toString())){
					error.add("importe", DONATIVOS_IMPORTE_DECIMAL_ERROR);
				}
			}
		}
		if(getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(clave_seguridad == null || clave_seguridad.toString().length() < 0){
					error.add(CommandBase.TAG_TOKEN, DONATIVOS_CLAVE_SEGURIDAD_ERROR);					
				}
				
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					error.add(CommandBase.TAG_HUELLA, DONATIVOS_HUELLA_SEGURIDAD_ERROR);
				}
			}else{
				error.add("opcion_seguridad",OPCION_SEGURIDAD);
			}	
		}
		return error;
	}
}
