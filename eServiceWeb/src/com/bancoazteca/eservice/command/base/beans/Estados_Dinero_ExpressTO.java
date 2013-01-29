package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Estados_Dinero_ExpressTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Collection<String> coleccion_estados;

	public Collection<String> getColeccion_estados() {
		return coleccion_estados;
	}

	public void setColeccion_estados(Collection<String> coleccion_estados) {
		this.coleccion_estados = coleccion_estados;
	}
	
	

}
