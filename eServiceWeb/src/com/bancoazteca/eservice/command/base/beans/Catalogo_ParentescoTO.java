package com.bancoazteca.eservice.command.base.beans;

public class Catalogo_ParentescoTO {
	private String indice;
	private String descripcion;
	
	public Catalogo_ParentescoTO() {
	}
	public Catalogo_ParentescoTO(String indice,String descripcion) {
		this.indice=indice;
		this.descripcion=descripcion;
	}
	public String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
