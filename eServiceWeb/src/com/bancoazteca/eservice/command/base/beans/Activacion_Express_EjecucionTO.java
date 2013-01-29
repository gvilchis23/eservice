package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Activacion_Express_EjecucionTO implements Serializable{
	
	private static final long serialVersionUID = 5027320500224591055L;
	
	private String estatus_activacion;

	public String getEstatus_activacion() {
		return estatus_activacion;
	}

	public void setEstatus_activacion(String estatus_activacion) {
		this.estatus_activacion = estatus_activacion;
	}

	
}
