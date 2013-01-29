package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Agentes_Dinero_ExpressTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Collection<String> coleccion_agentes;

	public Collection<String> getColeccion_agentes() {
		return coleccion_agentes;
	}

	public void setColeccion_agentes(Collection<String> coleccionAgentes) {
		coleccion_agentes = coleccionAgentes;
	}

	

}
