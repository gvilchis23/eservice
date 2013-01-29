package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class PagoServiciosTelmexRequestTO implements Serializable{

	
	private static final long serialVersionUID = -4001064227602279538L;
	
	
	private String user;
	private String numeroTelefonico;
	private String digitoVerificador;
	private String cuentaCargo;
	private String tipoServicio;
	private String importe;
	private String tokenCode;
	private String digitalFinger;
	private String total;
	private String fechaAplicacion;
	private String userId;
	private String optionDispositive;
	private String clave;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
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
	
}
