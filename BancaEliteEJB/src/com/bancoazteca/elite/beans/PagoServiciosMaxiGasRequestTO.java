package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class PagoServiciosMaxiGasRequestTO implements Serializable{

	
	private static final long serialVersionUID = 17639297737098525L;

	private String user;
	private String cuentaReferencia;
	private String digitoVerificador;
	private String cuentaCargo;
	private String tipoServicio;
	private String importe;
	private String comision;
	private String iva;
	private String tokenCode;
	private String digitalFinger;
	private String total;
	private String fechaAplicacion;
	private String userId;
	private String optionDispositive;
	private String clave;
	
	
	
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
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
	public String getDigitalFinger() {
		return digitalFinger;
	}
	public void setDigitalFinger(String digitalFinger) {
		this.digitalFinger = digitalFinger;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOptionDispositive() {
		return optionDispositive;
	}
	public void setOptionDispositive(String optionDispositive) {
		this.optionDispositive = optionDispositive;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCuentaReferencia() {
		return cuentaReferencia;
	}
	public void setCuentaReferencia(String cuentaReferencia) {
		this.cuentaReferencia = cuentaReferencia;
	}
	public String getDigitoVerificador() {
		return digitoVerificador;
	}
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}	
}