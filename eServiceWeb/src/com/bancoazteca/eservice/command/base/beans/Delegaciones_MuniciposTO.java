package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Delegaciones_MuniciposTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Collection<String> coleccion_municipios_delegaciones;

	public Collection<String> getColeccion_municipios_delegaciones() {
		return coleccion_municipios_delegaciones;
	}

	public void setColeccion_municipios_delegaciones(
			Collection<String> coleccionMunicipiosDelegaciones) {
		coleccion_municipios_delegaciones = coleccionMunicipiosDelegaciones;
	}

	
	

}
