package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Detalle_OperacionTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String folio;
	private String fecha_liquidacion;
	private String fecha_vencimiento;
	private Double monto;
	private String plazo;
	private String tasa;
	private Double interes_generados;
	private Double retencion;
	private Double monto_total;
	private String estatus;
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFecha_liquidacion() {
		return fecha_liquidacion;
	}
	public void setFecha_liquidacion(String fecha_liquidacion) {
		this.fecha_liquidacion = fecha_liquidacion;
	}
	public String getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(String fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
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
	public Double getInteres_generados() {
		return interes_generados;
	}
	public void setInteres_generados(Double interes_generados) {
		this.interes_generados = interes_generados;
	}
	public Double getRetencion() {
		return retencion;
	}
	public void setRetencion(Double retencion) {
		this.retencion = retencion;
	}
	public Double getMonto_total() {
		return monto_total;
	}
	public void setMonto_total(Double monto_total) {
		this.monto_total = monto_total;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String status) {
		this.estatus = status;
	}
}
