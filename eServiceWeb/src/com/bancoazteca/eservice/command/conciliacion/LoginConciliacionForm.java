package com.bancoazteca.eservice.command.conciliacion;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class LoginConciliacionForm extends FormBean {
	private String id_usuario;

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(Validator.isEmptyData(id_usuario)){
			error.add("id_usuario", VALIDA_IDUSUARIO, "id_usuario");
		}
		return error;
	}
}
