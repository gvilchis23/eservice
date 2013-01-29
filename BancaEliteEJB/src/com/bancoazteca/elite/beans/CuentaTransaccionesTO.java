package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class CuentaTransaccionesTO implements Serializable{

	private static final long serialVersionUID = -436635419310365356L;
	
	private String index;
	private String numero;
	private String cuenta;
	private String descripcion;
	private String cuentaFormateada;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCuentaFormateada() {
		return cuentaFormateada;
	}
	public void setCuentaFormateada(String cuentaFormateada) {
		this.cuentaFormateada = cuentaFormateada;
	}

}
