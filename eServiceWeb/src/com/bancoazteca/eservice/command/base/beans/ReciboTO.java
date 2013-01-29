package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class ReciboTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String total;
	private String numero_operacion;
	private String servicio;
	private String fecha_pago;
	private String fecha_aplicacion;
	private String referencia;
	private String indice_recibo;
	
	
	
	public String getIndice_recibo() {
		return indice_recibo;
	}
	public void setIndice_recibo(String indiceRecibo) {
		indice_recibo = indiceRecibo;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getNumero_operacion() {
		return numero_operacion;
	}
	public void setNumero_operacion(String numero_operacion) {
		this.numero_operacion = numero_operacion;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public String getFecha_aplicacion() {
		return fecha_aplicacion;
	}
	public void setFecha_aplicacion(String fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}