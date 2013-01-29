package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class DetalleMovimientosTO implements Serializable{
	
	private static final long serialVersionUID = 2931295275306647566L;

	private Double interes;
	private Double saldo;
	private String unidades;
	private String movimiento;
	private String folio;
	private String fecha;
	private String valorUnidad;
	private String saldoUnidades;
	private Double importe;
	
	public Double getInteres() {
		return interes;
	}
	public void setInteres(Double interes) {
		this.interes = interes;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public String getUnidades() {
		return unidades;
	}
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	
}
