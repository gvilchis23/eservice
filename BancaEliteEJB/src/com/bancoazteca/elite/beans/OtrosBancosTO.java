package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class OtrosBancosTO implements Serializable{

	private static final long serialVersionUID = 700298475083401072L;
	
	private String index;
	private String descripcion;
	private String codigo;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
