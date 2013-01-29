package com.bancoazteca.eservice.command.pagoservicios.pagotarjetaazteca;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class PagoTarjetaAztecaForm extends FormBean {

	private static final long serialVersionUID = -5878783232451L;
	private String cuenta_cargo;
	private String numero_tarjeta;
	private String importe;
	private String comision;
	private String opcion_seguridad; 
	private Cipher clave_seguridad;
	private String huella_seguridad;
	private String email;
	private String mensaje;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}

	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}

	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}

	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}

	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();
		if (getComando().equalsIgnoreCase("VALIDACION")) {
			if ( Validator.isEmptyData(cuenta_cargo)|| cuenta_cargo.equals("-1")) {
				errors.add("cuenta_x", VALIDATOR_PAYMENT_PAGOTARJETACREDITO_ACCOUNT_EMPTY);
			}
			if ( numero_tarjeta== null || numero_tarjeta.equals("") || numero_tarjeta.equals("-1")) {
				errors.add("tarjeta", VALIDATOR_PAYMENT_PAGOTARJETACREDITO_TARJETA_EMPTY);
			}
			if (Validator.isEmptyData(importe)) {
				errors.add("importe", VALIDATOR_PAYMENT_PAGOTARJETACREDITO_PAYMENT_EMPTY);
			} else if (!Validator.checkDecimal(importe)) {
				errors.add("importe", VALIDATOR_PAYMENT_PAGOTARJETACREDITO_PAYMENT_CHARACTER);
			} else if (Double.parseDouble(importe)<=0) {
				errors.add("importe", VALIDATOR_PAYMENT_PAGOTARJETACREDITO_PAYMENT_ZERO);
			}
		}else if (getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					errors.add("clave_seguridad",VALIDATOR_PAYMENT_PAGOTARJETACREDITO_SECURITY_CLAV_EMPTY);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					errors.add("huella_seguridad",VALIDATOR_PAYMENT_PAGOTARJETACREDITO_SECURITY_HUELLA_EMPTY);
				}
			}else{
				errors.add("opcion_seguridad",VALIDATOR_PAYMENT_PAGOTARJETACREDITO_SECURITY_OPTION_EMPTY);
			}
			
			if (getComando().equalsIgnoreCase("enviomail")) {
				 	if (Validator.isEmptyData(email)){
				 		errors.add("email", VALIDATOR_ENVIO_MAIL_CORREO_ELECTRONICO);		
				 	}
														
				 	if (Validator.isEmptyData(mensaje)) {
				 		errors.add("mensaje",VALIDATOR_ENVIO_MAIL_MENSAJE);
					}
			 }	
			
		}
		return errors;
	}
	
	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
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
	
	
}
