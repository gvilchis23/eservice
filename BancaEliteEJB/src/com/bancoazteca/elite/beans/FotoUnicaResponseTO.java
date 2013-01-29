package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class FotoUnicaResponseTO implements Serializable {

	private static final long serialVersionUID = -8210573356269997044L;
	
	private String fotoUsuario;
	private String status;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFotoUsuario() {
		return fotoUsuario;
	}
	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
}
