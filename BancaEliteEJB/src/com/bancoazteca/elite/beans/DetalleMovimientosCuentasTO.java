package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class DetalleMovimientosCuentasTO implements Serializable{

	private static final long serialVersionUID = -5800689901528898518L;
	
	private Double comisiones;
	private Double cargos;
	private Double totales;
	private Double abonos;
	private String termino;
	private String inicio;
	
	public Double getComisiones() {
		return comisiones;
	}
	public void setComisiones(Double comisiones) {
		this.comisiones = comisiones;
	}
	public Double getCargos() {
		return cargos;
	}
	public void setCargos(Double cargos) {
		this.cargos = cargos;
	}
	public Double getTotales() {
		return totales;
	}
	public void setTotales(Double totales) {
		this.totales = totales;
	}
	public Double getAbonos() {
		return abonos;
	}
	public void setAbonos(Double abonos) {
		this.abonos = abonos;
	}
	public String getTermino() {
		return termino;
	}
	public void setTermino(String termino) {
		this.termino = termino;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	
}
