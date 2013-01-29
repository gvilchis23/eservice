package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class MovimientosTO implements Serializable{
	
	private static final long serialVersionUID = 2018840384498295402L;
	
	private Double balance;
	private String concepto;
	private String fecha;
	private Double importe;
	private String numero;
	private String operador;
	private String sucursal;
	private String terminal;	
	private String interes;
	private Double saldo;
	private String movimiento;
	private String unidades;
	private String folio;	
	private String valorUnidad;
	private String saldoUnidades;
	

	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getInteres() {
		return interes;
	}
	public void setInteres(String interes) {
		this.interes = interes;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	public String getUnidades() {
		return unidades;
	}
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getValorUnidad() {
		return valorUnidad;
	}
	public void setValorUnidad(String valorUnidad) {
		this.valorUnidad = valorUnidad;
	}
	public String getSaldoUnidades() {
		return saldoUnidades;
	}
	public void setSaldoUnidades(String saldoUnidades) {
		this.saldoUnidades = saldoUnidades;
	}
	

	


}
