package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ResultadoBusquedaSucursalTO implements Serializable {

	private static final long serialVersionUID = -377203876517704391L;
	private String noCentro;
	private String nombre;
	private String direccion;
	
	public String getNoCentro() {
		return noCentro;
	}
	public void setNoCentro(String noCentro) {
		this.noCentro = noCentro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
