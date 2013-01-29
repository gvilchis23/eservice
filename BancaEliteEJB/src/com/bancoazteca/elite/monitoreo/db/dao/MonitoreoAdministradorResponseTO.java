package com.bancoazteca.elite.monitoreo.db.dao;

import java.io.Serializable;

public class MonitoreoAdministradorResponseTO implements Serializable{
	
	private static final long serialVersionUID = 8423717948588908042L;
	
	private String id;
	private String nombre;
	private String usuario;
	private String contrasenia;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
}
