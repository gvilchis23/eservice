package com.bancoazteca.eservice.command.usuarios.fotounica;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class FotoUnicaForm extends FormBean{
	
	private String cuenta_usuario;
	private String sucursal_cuenta_usuario;
	
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("EJECUCION")){			
			
			if(Validator.isEmptyData(getCuenta_usuario())){
				errores.add("cuenta_usuario", VALIDATOR_FOTO_UNICA_CUENTA_EMPTY);
			}else if(!Validator.checkNumeric(getCuenta_usuario())){
				errores.add("cuenta_usuario", VALIDATOR_FOTO_UNICA_CUENTA_ERROR);
			}else if(getCuenta_usuario().length()!=10){
				errores.add("cuenta_usuario", VALIDATOR_FOTO_UNICA_CUENTA_LONGITUD);
			}
	
			if(Validator.isEmptyData(getSucursal_cuenta_usuario())){
				errores.add("sucursal_cuenta_usuario", VALIDATOR_FOTO_UNICA_SUCURSAL_EMPTY);
			}else if(!Validator.checkNumeric(getSucursal_cuenta_usuario())){
				errores.add("sucursal_cuenta_usuario", VALIDATOR_FOTO_UNICA_SUCURSAL_ERROR);
			}else if(getSucursal_cuenta_usuario().length()!=4){
				errores.add("sucursal_cuenta_usuario", VALIDATOR_FOTO_UNICA_SUCURSAL_LONGITUD);
			}
		}
		return  errores;
	}
	
	public String getCuenta_usuario() {
		return cuenta_usuario;
	}
	public void setCuenta_usuario(String cuenta_usuario) {
		this.cuenta_usuario = cuenta_usuario;
	}
	public String getSucursal_cuenta_usuario() {
		return sucursal_cuenta_usuario;
	}
	public void setSucursal_cuenta_usuario(String sucursal_cuenta_usuario) {
		this.sucursal_cuenta_usuario = sucursal_cuenta_usuario;
	}
}
