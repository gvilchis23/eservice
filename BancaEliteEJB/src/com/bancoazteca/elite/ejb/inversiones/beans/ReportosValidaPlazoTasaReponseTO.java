package com.bancoazteca.elite.ejb.inversiones.beans;

public class ReportosValidaPlazoTasaReponseTO extends InversionesResponseTO {
	
	private boolean ok;
	private String mensaje;
	private String codigoMensaje;
	
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	
	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
}