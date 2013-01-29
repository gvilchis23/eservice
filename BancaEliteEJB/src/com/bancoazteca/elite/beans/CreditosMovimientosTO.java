package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreditosMovimientosTO implements Serializable{
	

	private static final long serialVersionUID = -2008067464284288464L;
	
	private String propietario;
	private String comisiones;
	private String moneda;
	private BigDecimal cargos;
	private String numOper;
	private String fechaDeOperacion;
	private String fechaDeAplicacion;
	private String inicio;
	private String cantidad;
	private String negocio;
	private String totales;
	private String abonoOCargoStr;
	private String numParticipe;
	private String termino;
	private BigDecimal abonos;
	private BigDecimal tipoCambio;
	
	private BigDecimal monedaOriginal;

	
	public BigDecimal getMonedaOriginal() {
		return monedaOriginal;
	}
	public void setMonedaOriginal(BigDecimal monedaOriginal) {
		this.monedaOriginal = monedaOriginal;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public String getComisiones() {
		return comisiones;
	}
	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getNumOper() {
		return numOper;
	}
	public void setNumOper(String numOper) {
		this.numOper = numOper;
	}
	public String getFechaDeOperacion() {
		return fechaDeOperacion;
	}
	public void setFechaDeOperacion(String fechaDeOperacion) {
		this.fechaDeOperacion = fechaDeOperacion;
	}
	public String getFechaDeAplicacion() {
		return fechaDeAplicacion;
	}
	public void setFechaDeAplicacion(String fechaDeAplicacion) {
		this.fechaDeAplicacion = fechaDeAplicacion;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getNegocio() {
		return negocio;
	}
	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}
	public String getTotales() {
		return totales;
	}
	public void setTotales(String totales) {
		this.totales = totales;
	}
	public String getAbonoOCargoStr() {
		return abonoOCargoStr;
	}
	public void setAbonoOCargoStr(String abonoOCargoStr) {
		this.abonoOCargoStr = abonoOCargoStr;
	}
	public String getNumParticipe() {
		return numParticipe;
	}
	public void setNumParticipe(String numParticipe) {
		this.numParticipe = numParticipe;
	}
	public String getTermino() {
		return termino;
	}
	public void setTermino(String termino) {
		this.termino = termino;
	}	
	public BigDecimal getAbonos() {
		return abonos;
	}
	public void setAbonos(BigDecimal abonos) {
		this.abonos = abonos;
	}	
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public BigDecimal getCargos() {
		return cargos;
	}
	public void setCargos(BigDecimal cargos) {
		this.cargos = cargos;
	}
	
	

}
