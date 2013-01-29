package com.bancoazteca.elite.beans;

import java.io.Serializable;

import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;

public class ActualizacionSaldoPorCuentaResponseTO extends InversionesResponseTO implements Serializable{

	private static final long serialVersionUID = 1775989357565258084L;
	
	private double saldoRetenido;
	private double saldoDisponible;
	private double saldoTotal;
	private String numeroCuenta;
	
	public double getSaldoRetenido() {
		return saldoRetenido;
	}
	public void setSaldoRetenido(double saldoRetenido) {
		this.saldoRetenido = saldoRetenido;
	}
	public double getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public double getSaldoTotal() {
		return saldoTotal;
	}
	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
}
