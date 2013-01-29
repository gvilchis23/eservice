package com.bancoazteca.eservice.command.login;

import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class LoginForm extends FormBean{
	
	private String usuario;
	private Cipher password;
//	private Cipher idaplicacion;
	
	private String cerrar_sesion_anterior;

	public String getCerrar_sesion_anterior() {
		return cerrar_sesion_anterior;
	}

	public void setCerrar_sesion_anterior(String cerrar_sesion_anterior) {
		this.cerrar_sesion_anterior = cerrar_sesion_anterior;
	}

	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();		
		if(usuario == null || usuario.trim().length() == 0){
			error.add("usuario", LOGIN_USUARIO_ERROR);
		}
		if(password == null ){
			error.add("password", LOGIN_PASSWORD_ERROR);
		}
		if (getIdaplicacion()==null || getIdaplicacion().toString().trim().length()==0){
			error.add("idaplicacion", APPLICATION_SESSION_ERROR);
		}
		return error;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Cipher getPassword() {
		return password;
	}

	public void setPassword(Cipher password) {
		this.password = password;
	}

}
