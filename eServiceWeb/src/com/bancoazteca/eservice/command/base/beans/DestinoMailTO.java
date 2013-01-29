package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class DestinoMailTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5594257952471679497L;
	private String email;
	private String nombreDestinatario;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
}
