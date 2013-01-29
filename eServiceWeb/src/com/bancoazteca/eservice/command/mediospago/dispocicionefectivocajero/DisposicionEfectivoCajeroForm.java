package com.bancoazteca.eservice.command.mediospago.dispocicionefectivocajero;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;


public class DisposicionEfectivoCajeroForm extends FormBean {

	private String numero_tarjeta;
	private double monto;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numeroTarjeta) {
		numero_tarjeta = numeroTarjeta;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(Cipher claveSeguridad) {
		clave_seguridad = claveSeguridad;
	}
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public void setOpcion_seguridad(String opcionSeguridad) {
		opcion_seguridad = opcionSeguridad;
	}
	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huellaSeguridad) {
		huella_seguridad = huellaSeguridad;
	}
	
	@Override
	public MessageErrors validate() {
		MessageErrors errors=new MessageErrors();
		
		if(getComando().equalsIgnoreCase("solicitud")){
			if(Validator.isEmptyData(numero_tarjeta)){
				errors.add("numero_tarjeta",MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_NUM_TARJETA_EMPTY);
			}
		}
		
		if(getComando().equalsIgnoreCase("validacion")){
			if(monto<=0d){
				errors.add("monto",MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_MONTO_EMPTY);
			}
		}
		
		if(getComando().equalsIgnoreCase("ejecucion")){
			
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					errors.add("token",MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_TOKEN_EMPTY);
				}
				if(!Validator.checkNumeric(clave_seguridad.toString()) || clave_seguridad.toString().length()!=6){
					errors.add("token",MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_TOKEN_FORMATO);
				}
				
			}else{
				if(Validator.isEmptyData(huella_seguridad)){
					errors.add("huella",MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_HUELLA_EMPTY);
				}
			}
		}

		return errors;
	}
}
