package com.bancoazteca.eservice.command.base.beans;

import com.bancoazteca.eservice.beans.Cipher;

public class StatusTO {
	
	private Cipher idsesion;
	private String idservicio;
	private String tipo_operacion; 
	private String codigo_operacion = "0";
	private String descripcion_codigo;
	private String error_sistema;
	
	
	public String getIdservicio() {
		return idservicio;
	}
	public void setIdservicio(String idservicio) {
		this.idservicio = idservicio;
	}
	public Cipher getIdsesion() {
		return idsesion;
	}
	public void setIdsesion(Cipher idsesion) {
		this.idsesion = idsesion;
	}
	public String getCodigo_operacion() {
		return codigo_operacion;
	}
	public void setCodigo_operacion(String codigo_operacion) {
		this.codigo_operacion = codigo_operacion;
	}
	public String getDescripcion_codigo() {
		return descripcion_codigo;
	}
	public void setDescripcion_codigo(String descripcion_codigo) {
		this.descripcion_codigo = descripcion_codigo;
	}
	public String getError_sistema() {
		return error_sistema;
	}
	public void setError_sistema(String error_sistema) {
		this.error_sistema = error_sistema;
	}
	public String getTipo_operacion() {
		return tipo_operacion;
	}
	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}
	
}
