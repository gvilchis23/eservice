package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class PagoTarjetaCreditoRequestTO implements Serializable {

	private static final long serialVersionUID = -558926468700L;
	private String user;
	private String cuenta;
	private String tarjeta;
	private String importe;
	private String comision;
	

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
