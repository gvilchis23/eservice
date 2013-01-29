package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Comisiones_Dinero_ExpressTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String monto_enviar;
	private String comision;
	private String descuento;
	private String subtotal;
	private String impuestos;
	private String total_pagar;
	
	public String getMonto_enviar() {
		return monto_enviar;
	}
	public void setMonto_enviar(String monto_enviar) {
		this.monto_enviar = monto_enviar;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}
	public String getTotal_pagar() {
		return total_pagar;
	}
	public void setTotal_pagar(String total_pagar) {
		this.total_pagar = total_pagar;
	}
	

}
