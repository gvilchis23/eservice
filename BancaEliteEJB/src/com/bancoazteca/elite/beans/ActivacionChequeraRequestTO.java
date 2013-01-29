package com.bancoazteca.elite.beans;
import java.io.Serializable;

public class ActivacionChequeraRequestTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	private String user;
	private String numeroCuenta;
	private String descripcionCuenta;
	private String fechaSolicitud;
	private String fechaActivacion;
	private String chequeInicial;
	private String chequeFinal;
	private String descripcionChequera;
	private String optionDispositive;
	private String tokencode;
	private String clave;

	public void setUser(String user) {
		this.user = user;
	}
	public String getUser() {
		return user;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getDescripcionCuenta() {
		return descripcionCuenta;
	}
	public void setDescripcionCuenta(String descripcionCuenta) {
		this.descripcionCuenta = descripcionCuenta;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	public String getOptionDispositive() {
		return optionDispositive;
	}
	public void setOptionDispositive(String optionDispositive) {
		this.optionDispositive = optionDispositive;
	}
	public String getTokencode() {
		return tokencode;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getChequeInicial() {
		return chequeInicial;
	}
	public void setChequeInicial(String chequeInicial) {
		this.chequeInicial = chequeInicial;
	}
	public String getChequeFinal() {
		return chequeFinal;
	}
	public void setChequeFinal(String chequeFinal) {
		this.chequeFinal = chequeFinal;
	}
	public String getDescripcionChequera() {
		return descripcionChequera;
	}
	public void setDescripcionChequera(String descripcionChequera) {
		this.descripcionChequera = descripcionChequera;
	}
}
