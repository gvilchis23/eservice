package com.bancoazteca.monitoreo.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bancoazteca.monitoreo.utileria.Validator;
import com.bancoazteca.monitoreo.MonitoreoForm;

public class LoginForm extends MonitoreoForm  {

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String contrasenia;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if (!Validator.checkAlphanumeric(getUsuario())){
			if (getUsuario().length()<0)
				errors.add("usuarionulo", new ActionMessage("error.monitoreo.usuarioincorrecto"));
			else
				errors.add("caracteresincorrectos", new ActionMessage("error.monitoreo.usuarioincorrecto"));
		}
		if (!Validator.checkAlphanumeric(getContrasenia())){
			if (getContrasenia().length()<0)
				errors.add("contraseniaincorrecta", new ActionMessage("error.monitoreo.contraseniaincorrecta"));
			else
				errors.add("caracteresincorrectos", new ActionMessage("error.monitoreo.contraseniaincorrecta"));
		}
		return errors;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		usuario = "";
		contrasenia = "";
	}
}
