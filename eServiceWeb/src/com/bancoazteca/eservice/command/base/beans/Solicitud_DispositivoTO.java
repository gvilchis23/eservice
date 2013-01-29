package com.bancoazteca.eservice.command.base.beans;


public class Solicitud_DispositivoTO {
	
	private String costo_token;
	private String costo_lector;
	private boolean mostrar_token;
	
	public String getCosto_token() {
		return costo_token;
	}
	public void setCosto_token(String costo_token) {
		this.costo_token = costo_token;
	}
	public String getCosto_lector() {
		return costo_lector;
	}
	public void setCosto_lector(String costo_lector) {
		this.costo_lector = costo_lector;
	}
	public boolean isMostrar_token() {
		return mostrar_token;
	}
	public void setMostrar_token(boolean mostrar_token) {
		this.mostrar_token = mostrar_token;
	}

}
