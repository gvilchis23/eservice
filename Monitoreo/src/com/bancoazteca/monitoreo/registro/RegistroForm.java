package com.bancoazteca.monitoreo.registro;

import com.bancoazteca.monitoreo.MonitoreoForm;

public class RegistroForm extends MonitoreoForm {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String usuario;
	private String contrasenia;
	
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
