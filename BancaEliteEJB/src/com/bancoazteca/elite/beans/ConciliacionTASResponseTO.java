package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ConciliacionTASResponseTO implements Serializable{
	private static final long serialVersionUID = 3129643903481450425L;
	private String folio_orden="";
	private String fecha_operacion="";
	private String monto="0";
	private String estatus="";
	private String folio_retencion="";
	private String monto_retencion="0";
	private String folio_cargo="";
	private String monto_cargo="0";
	private String codigo="";
	private String descripcion="";
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFolio_orden() {
		return folio_orden;
	}
	public void setFolio_orden(String folio_orden) {
		this.folio_orden = folio_orden;
	}
	public String getFecha_operacion() {
		return fecha_operacion;
	}
	public void setFecha_operacion(String fecha_operacion) {
		this.fecha_operacion = fecha_operacion;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getFolio_retencion() {
		return folio_retencion;
	}
	public void setFolio_retencion(String folio_retencion) {
		this.folio_retencion = folio_retencion;
	}
	public String getMonto_retencion() {
		return monto_retencion;
	}
	public void setMonto_retencion(String monto_retencion) {
		this.monto_retencion = monto_retencion;
	}
	public String getFolio_cargo() {
		return folio_cargo;
	}
	public void setFolio_cargo(String folio_cargo) {
		this.folio_cargo = folio_cargo;
	}
	public String getMonto_cargo() {
		return monto_cargo;
	}
	public void setMonto_cargo(String monto_cargo) {
		this.monto_cargo = monto_cargo;
	}
}