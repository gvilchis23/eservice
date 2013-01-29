package com.bancoazteca.eservice.command.base.beans;

public class Tarjeta_Otros_Bancos_EjecucionTO {
	private String cuenta_cargo;
	private String importe;
	private String comision;
	private String iva;
	private String total_pago;
	private String fecha_aplicacion;
	private String numero_tarjeta;
	private String banco;
	private String tipo_tarjeta;
	private String folio_aclaraciones;
	private String titular_cuenta_cargo;
	
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
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getTotal_pago() {
		return total_pago;
	}
	public void setTotal_pago(String total_pago) {
		this.total_pago = total_pago;
	}
	public String getFecha_aplicacion() {
		return fecha_aplicacion;
	}
	public void setFecha_aplicacion(String fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTipo_tarjeta() {
		return tipo_tarjeta;
	}
	public void setTipo_tarjeta(String tipo_tarjeta) {
		this.tipo_tarjeta = tipo_tarjeta;
	}
	public String getFolio_aclaraciones() {
		return folio_aclaraciones;
	}
	public void setFolio_aclaraciones(String folio_aclaraciones) {
		this.folio_aclaraciones = folio_aclaraciones;
	}

}
