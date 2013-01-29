package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class LoginConciliacionRequestTO implements Serializable {

	private static final long serialVersionUID = 6049797375984408255L;

	private String idUsuario;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
}
