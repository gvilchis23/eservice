package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class MovimientoInversionTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numero;
	private String fecha;
	private String balance;
	private String concepto;
	private String importe;
	private String tipoMovimiento;
	private String tasaBrutaAnual;
	
	public String getTasaBrutaAnual() {
		return tasaBrutaAnual;
	}
	public void setTasaBrutaAnual(String tasaBrutaAnual) {
		this.tasaBrutaAnual = tasaBrutaAnual;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
}