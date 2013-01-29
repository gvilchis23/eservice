package com.bancoazteca.eservice.command.enviodineroexpress.util;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.Errors;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EnvioCorreoDineroExpressForm extends FormBean {

	private String cuenta_correo;
	private String mensaje_correo;
	
	public String getCuenta_correo() {
		return cuenta_correo;
	}
	public void setCuenta_correo(String cuentaCorreo) {
		cuenta_correo = cuentaCorreo;
	}
	public String getMensaje_correo() {
		return mensaje_correo;
	}
	public void setMensaje_correo(String mensajeCorreo) {
		mensaje_correo = mensajeCorreo;
	}
	
	
	@Override
	public MessageErrors validate() {
		MessageErrors messageErrors=new MessageErrors();
		
		if(Validator.isEmptyData(cuenta_correo)){
			messageErrors.add("cuenta_correo",Errors.VALIDATOR_DINERO_EXPRESS_CUENTA_CORREO);
		}
		if(Validator.isEmptyData(mensaje_correo)){
			messageErrors.add("mensaje_correo",Errors.VALIDATOR_DINERO_EXPRESS_MENSAJE_CORREO);
		}
		
		return messageErrors;
	}
	
}