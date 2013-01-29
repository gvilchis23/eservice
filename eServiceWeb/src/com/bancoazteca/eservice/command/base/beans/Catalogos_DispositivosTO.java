package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;


public class Catalogos_DispositivosTO {
	private Collection<String> coleccion_estados;
	private Collection<String> coleccion_municipios;
	
	public Collection<String> getColeccion_estados() {
		return coleccion_estados;
	}
	public void setColeccion_estados(Collection<String> coleccion_estados) {
		this.coleccion_estados = coleccion_estados;
	}
	public Collection<String> getColeccion_municipios() {
		return coleccion_municipios;
	}
	public void setColeccion_municipios(Collection<String> coleccion_municipios) {
		this.coleccion_municipios = coleccion_municipios;
	}
}
