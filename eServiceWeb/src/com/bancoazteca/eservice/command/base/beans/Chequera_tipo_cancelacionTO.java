package com.bancoazteca.eservice.command.base.beans;

public class Chequera_tipo_cancelacionTO {
	private String value;
	private String descripcion;
	public Chequera_tipo_cancelacionTO() {
	}
	public Chequera_tipo_cancelacionTO(String descripcion, String value) {
		this.descripcion=descripcion;
		this.value=value;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
