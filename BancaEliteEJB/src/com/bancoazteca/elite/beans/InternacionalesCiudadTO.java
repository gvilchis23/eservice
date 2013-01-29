package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class InternacionalesCiudadTO implements Serializable{
	

	private static final long serialVersionUID = -2651383178554283758L;
	
	private String nombreCiudad;	
	private String claveCiudad;
	
	public String getNombreCiudad() {
		return nombreCiudad;
	}
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	public String getClaveCiudad() {
		return claveCiudad;
	}
	public void setClaveCiudad(String claveCiudad) {
		this.claveCiudad = claveCiudad;
	}
	
	

}
