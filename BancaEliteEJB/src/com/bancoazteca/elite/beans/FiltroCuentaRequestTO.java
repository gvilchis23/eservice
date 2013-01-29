package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class FiltroCuentaRequestTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String xml;
	private String idUsuario;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
