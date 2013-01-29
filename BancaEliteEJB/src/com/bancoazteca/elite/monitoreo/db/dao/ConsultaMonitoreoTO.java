package com.bancoazteca.elite.monitoreo.db.dao;

import java.io.Serializable;

public class ConsultaMonitoreoTO implements Serializable{

	private static final long serialVersionUID = 4500113972307030119L;

	private String nombre;
	private String totalUsuarios;
	private String numeroConexiones;
	private String numeroOperaciones;
	private String aplicacion;
	private String transaccion;
	private String importeOperacion;
	private String valor;
	private String fecha;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumeroConexiones() {
		return numeroConexiones;
	}
	public void setNumeroConexiones(String numeroConexiones) {
		this.numeroConexiones = numeroConexiones;
	}
	public String getNumeroOperaciones() {
		return numeroOperaciones;
	}
	public void setNumeroOperaciones(String numeroOperaciones) {
		this.numeroOperaciones = numeroOperaciones;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	public String getImporteOperacion() {
		return importeOperacion;
	}
	public void setImporteOperacion(String importeOperacion) {
		this.importeOperacion = importeOperacion;
	}
	public String getTotalUsuarios() {
		return totalUsuarios;
	}
	public void setTotalUsuarios(String totalUsuarios) {
		this.totalUsuarios = totalUsuarios;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	 
}
