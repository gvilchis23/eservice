package com.bancoazteca.elite.beans;

public class ConsultaPresolicitudDaoTO {
	private String optionDispositive;
	private String tokenCode;
	private String clave;
	
	private String usuario;
	private String fechaSolicitud;
	private String cuenta;
	private String regimen;
	private String tipoChequera;
	private String proteccion;
	private String montoPiso;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public String getTipoChequera() {
		return tipoChequera;
	}
	public void setTipoChequera(String tipoChequera) {
		this.tipoChequera = tipoChequera;
	}
	public String getProteccion() {
		return proteccion;
	}
	public void setProteccion(String proteccion) {
		this.proteccion = proteccion;
	}
	public String getMontoPiso() {
		return montoPiso;
	}
	public void setMontoPiso(String montoPiso) {
		this.montoPiso = montoPiso;
	}
	public String getOptionDispositive() {
		return optionDispositive;
	}
	public void setOptionDispositive(String optionDispositive) {
		this.optionDispositive = optionDispositive;
	}
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
}
