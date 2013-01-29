package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ServiciosDisponiblesTO implements Serializable{

	private static final long serialVersionUID = -1642325986699015092L;
	
	private String servicio;
	private String tipoServicio;
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
}
