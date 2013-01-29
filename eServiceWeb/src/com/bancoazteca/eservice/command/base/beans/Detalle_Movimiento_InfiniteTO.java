package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Detalle_Movimiento_InfiniteTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha_aplicacion;
	private String fecha_operacion;
	private String establecimiento;
	private String importe;
	private String monto_moneda_original;
	public String getFecha_aplicacion() {
		return fecha_aplicacion;
	}
	public void setFecha_aplicacion(String fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}
	public String getFecha_operacion() {
		return fecha_operacion;
	}
	public void setFecha_operacion(String fecha_operacion) {
		this.fecha_operacion = fecha_operacion;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getMonto_moneda_original() {
		return monto_moneda_original;
	}
	public void setMonto_moneda_original(String monto_moneda_original) {
		this.monto_moneda_original = monto_moneda_original;
	}
	
}
