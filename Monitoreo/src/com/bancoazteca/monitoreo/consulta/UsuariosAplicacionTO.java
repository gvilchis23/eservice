package com.bancoazteca.monitoreo.consulta;

import java.io.Serializable;

public class UsuariosAplicacionTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String aplicacion;
	private String nombre;
	private String numero_conexiones;
	
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumero_conexiones() {
		return numero_conexiones;
	}
	public void setNumero_conexiones(String numeroConexiones) {
		numero_conexiones = numeroConexiones;
	}
}
