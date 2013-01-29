package com.bancoazteca.eservice.command.base.beans;


public class Traspasos_Terceros_EjecucionTO {
	

	
	private String titular_cuentas;
	private String cuenta_cargo; 
	private String concepto;
	private String titular_cuenta_destino; 
	private String cuenta_destino; 
	private String referencia; 
	private String importe;
	private String comision; 
	private String iva_comision; 
	private String folio_aclaracion;
	
	public String getTitular_cuentas() {
		return titular_cuentas;
	}
	public void setTitular_cuentas(String titular_cuentas) {
		this.titular_cuentas = titular_cuentas;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getTitular_cuenta_destino() {
		return titular_cuenta_destino;
	}
	public void setTitular_cuenta_destino(String titular_cuenta_destino) {
		this.titular_cuenta_destino = titular_cuenta_destino;
	}
	public String getCuenta_destino() {
		return cuenta_destino;
	}
	public void setCuenta_destino(String cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
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
	public String getIva_comision() {
		return iva_comision;
	}
	public void setIva_comision(String iva_comision) {
		this.iva_comision = iva_comision;
	}
	public String getFolio_aclaracion() {
		return folio_aclaracion;
	}
	public void setFolio_aclaracion(String folio_aclaracion) {
		this.folio_aclaracion = folio_aclaracion;
	} 


	
}
