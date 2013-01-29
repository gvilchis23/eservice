package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Ciudades_Dinero_ExpressTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Collection<String> coleccion_ciudades;

	public Collection<String> getColeccion_ciudades() {
		return coleccion_ciudades;
	}

	public void setColeccion_ciudades(Collection<String> coleccion_ciudades) {
		this.coleccion_ciudades = coleccion_ciudades;
	}

}
