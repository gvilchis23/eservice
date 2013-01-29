package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Foto_UnicaTO implements Serializable {

	private static final long serialVersionUID = 2119683975069461061L;
	
	private String foto;
	private String estatus_wsfotounica;

	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getEstatus_wsfotounica() {
		return estatus_wsfotounica;
	}
	public void setEstatus_wsfotounica(String estatus_wsfotounica) {
		this.estatus_wsfotounica = estatus_wsfotounica;
	}
}
