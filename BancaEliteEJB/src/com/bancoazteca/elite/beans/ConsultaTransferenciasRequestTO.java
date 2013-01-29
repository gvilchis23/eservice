package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ConsultaTransferenciasRequestTO implements Serializable {

	private static final long serialVersionUID = -1111547871L;
	private String user;
	private String cuenta;
	private String origen;
	private String index;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
}
