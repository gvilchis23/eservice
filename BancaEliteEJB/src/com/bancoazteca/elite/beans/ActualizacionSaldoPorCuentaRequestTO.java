package com.bancoazteca.elite.beans;

import java.io.Serializable;

import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;

public class ActualizacionSaldoPorCuentaRequestTO extends InversionesRequestTO implements Serializable {

	private static final long serialVersionUID = 4478334596463711024L;
	
	private String numeroCuenta;

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
}
