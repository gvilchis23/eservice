package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class OperacionFrecuenteTO  implements Serializable {
	
	private static final long serialVersionUID = 1426183819562353611L;
	
	private String usuario;
	private String cadena_datos;
	private String operaciones;
	private String alias;
	private String clave_operacion;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCadena_datos() {
		return cadena_datos;
	}
	public void setCadena_datos(String cadena_datos) {
		this.cadena_datos = cadena_datos;
	}
	public String getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(String operaciones) {
		this.operaciones = operaciones;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getClave_operacion() {
		return clave_operacion;
	}
	public void setClave_operacion(String clave_operacion) {
		this.clave_operacion = clave_operacion;
	}
}
