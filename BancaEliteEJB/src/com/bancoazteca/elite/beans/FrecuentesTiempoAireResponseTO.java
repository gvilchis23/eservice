package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class FrecuentesTiempoAireResponseTO implements Serializable{

	private static final long serialVersionUID = -3654664791022097246L;
	
	private Collection telefonosFrecuentes;

	public Collection getTelefonosFrecuentes() {
		return telefonosFrecuentes;
	}

	public void setTelefonosFrecuentes(Collection telefonosFrecuentes) {
		this.telefonosFrecuentes = telefonosFrecuentes;
	}
}
