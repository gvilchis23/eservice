package com.bancoazteca.eservice.command.pagoservicios.tarjeta_otrosbancos;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class PagoTarjetaOtrosBancosForm extends FormBean{
	private String numero_tarjeta;
	private String cuenta_cargo;
	private String importe;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	public MessageErrors validate (){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if(Validator.isEmptyData(numero_tarjeta)){
				error.add("numero_tarjeta", TARJETA_CUENTA_ERROR);
			}
			
			if(Validator.isEmptyData(cuenta_cargo)){
				error.add("cuenta_cargo", CUENTAS_ERROR, "cargo");
			}else{	
				if(!Validator.checkNumeric(cuenta_cargo)){
					error.add("cuenta_cargo", CUENTAS_NUMERICAS_ERROR, "cargo");
				}
			}
			
			if(Validator.isEmptyData(importe)){
				error.add("importe", IMPORTE_ERROR);
			}else{
				if(!Validator.checkDecimal(importe)){
					error.add("importe", IMPORTE_DECIMAL_ERROR);
				}
			}						
		}
		if(getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					error.add("clave_seguridad",CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					error.add("huella_seguridad",HUELLA_SEGURIDAD);
				}
			}else{
				error.add("opcion_seguridad",OPCION_SEGURIDAD);
			}
		}
		return error;
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

	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
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


	public String getHuella_seguridad() {
		return huella_seguridad;
	}


	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}

}
