package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class DatosAdicionalesPagoServiciosTO implements Serializable{

	private static final long serialVersionUID = -8426380370613077294L;


	private String servletWrapper;
	private String digitoVerificador;	
	private String fechaAplicacion;
	private String numeroOperacion;
	
	public String getServletWrapper() {
		return servletWrapper;
	}
	public void setServletWrapper(String servletWrapper) {
		this.servletWrapper = servletWrapper;
	}
	public String getDigitoVerificador() {
		return digitoVerificador;
	}
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	
	
}
