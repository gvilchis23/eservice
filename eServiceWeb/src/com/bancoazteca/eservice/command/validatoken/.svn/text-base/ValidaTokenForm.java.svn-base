package com.bancoazteca.eservice.command.validatoken;

import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ValidaTokenForm extends FormBean{

	private String user;
	private Cipher token_code;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Cipher getToken_code() {
		return token_code;
	}

	public void setToken_code(Cipher token_code) {
		this.token_code = token_code;
	}
	
	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();		
		if(user == null || user.trim().length() == 0){
			error.add("user", VALIDA_TOKEN_USER_ERROR);
		}
		if(token_code == null ){
			error.add("token", VALIDA_TOKEN_TOKEN_ERROR);
		}
		return error;
	}
}
