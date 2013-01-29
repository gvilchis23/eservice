package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class FotoUnicaRequestTO implements Serializable {

	private static final long serialVersionUID = 1639082437695814048L;
	
	private String sucursal;
	private String cuenta;
	private String pstrIP;
	private String pstrServerPort;
	private String user;
	
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getPstrIP() {
		return pstrIP;
	}
	public void setPstrIP(String pstrIP) {
		this.pstrIP = pstrIP;
	}
	public String getPstrServerPort() {
		return pstrServerPort;
	}
	public void setPstrServerPort(String pstrServerPort) {
		this.pstrServerPort = pstrServerPort;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
