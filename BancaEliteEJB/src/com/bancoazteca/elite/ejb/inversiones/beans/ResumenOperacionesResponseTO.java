package com.bancoazteca.elite.ejb.inversiones.beans;

public class ResumenOperacionesResponseTO extends InversionesResponseTO {

	private String folio;
	private String fechaLiquidacion;
	private String fechaVencimiento;
	private Double monto;
	private String plazo;
	private String tasa;
	private Double interesGenerados;
	private Double retencion;
	private Double montoTotal;
	private String estatus;
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Double getInteresGenerados() {
		return interesGenerados;
	}
	public void setInteresGenerados(Double interesGenerados) {
		this.interesGenerados = interesGenerados;
	}
	public Double getRetencion() {
		return retencion;
	}
	public void setRetencion(Double retencion) {
		this.retencion = retencion;
	}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
}