package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class LiberacionChequesRequestTO implements Serializable {

	private static final long serialVersionUID = 2256826065418288009L;

	private String user;
	private String fechaSolicitud;
	private String cuenta;
	private String activar;
	private String numeroCheque;
	private String montoCheque;
	private String opcionActivacion;
	private String clave;

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getActivar() {
		return activar;
	}

	public void setActivar(String activar) {
		this.activar = activar;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getMontoCheque() {
		return montoCheque;
	}

	public void setMontoCheque(String montoCheque) {
		this.montoCheque = montoCheque;
	}

	public String getOpcionActivacion() {
		return opcionActivacion;
	}

	public void setOpcionActivacion(String opcionActivacion) {
		this.opcionActivacion = opcionActivacion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
