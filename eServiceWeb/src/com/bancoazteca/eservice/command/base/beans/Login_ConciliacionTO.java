package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Login_ConciliacionTO implements Serializable {

	private static final long serialVersionUID = 8855204208397068461L;

	private String tiene_acceso;

	public String getTiene_acceso() {
		return tiene_acceso;
	}

	public void setTiene_acceso(String tiene_acceso) {
		this.tiene_acceso = tiene_acceso;
	}
}
