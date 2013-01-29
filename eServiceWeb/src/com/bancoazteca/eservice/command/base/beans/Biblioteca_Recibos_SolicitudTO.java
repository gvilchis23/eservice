package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Biblioteca_Recibos_SolicitudTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<String>coleccion_servicios;

	public Collection<String> getColeccion_servicios() {
		return coleccion_servicios;
	}

	public void setColeccion_servicios(Collection<String> coleccionServicios) {
		coleccion_servicios = coleccionServicios;
	}
	
	
}
