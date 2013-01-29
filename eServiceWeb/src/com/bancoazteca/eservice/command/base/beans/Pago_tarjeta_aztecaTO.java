package com.bancoazteca.eservice.command.base.beans;

public class Pago_tarjeta_aztecaTO {
	
	private String titular_cuenta_cargo;
	private String cuenta_cargo;
	private String numero_tarjeta;
	private String banco_emisor;
	private String tipo_tarjeta;
	private String titular_tarjeta;
	private String importe;
	private String comision;
	private String iva_comision;
	private String importe_total;
	
	public String getTitular_tarjeta() {
		return titular_tarjeta;
	}
	public void setTitular_tarjeta(String titular_tarjeta) {
		this.titular_tarjeta = titular_tarjeta;
	}
	public String getIva_comision() {
		return iva_comision;
	}
	public void setIva_comision(String iva_comision) {
		this.iva_comision = iva_comision;
	}
	public String getTitular_cuenta_cargo() {
		return titular_cuenta_cargo;
	}
	public void setTitular_cuenta_cargo(String titular_cuenta_cargo) {
		this.titular_cuenta_cargo = titular_cuenta_cargo;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}
	public String getBanco_emisor() {
		return banco_emisor;
	}
	public void setBanco_emisor(String banco_emisor) {
		this.banco_emisor = banco_emisor;
	}
	public String getTipo_tarjeta() {
		return tipo_tarjeta;
	}
	public void setTipo_tarjeta(String tipo_tarjeta) {
		this.tipo_tarjeta = tipo_tarjeta;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getImporte_total() {
		return importe_total;
	}
	public void setImporte_total(String importe_total) {
		this.importe_total = importe_total;
	}

}
