package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class FrecuentesTiempoAireRequestTO implements Serializable{

	private static final long serialVersionUID = -6503457054304296780L;
	
	private String user;
	private String alias;
	private String compania;
	private String telefono;
	private String index;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
}
