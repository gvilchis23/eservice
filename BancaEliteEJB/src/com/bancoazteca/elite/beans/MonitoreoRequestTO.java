package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class MonitoreoRequestTO implements Serializable{

	private static final long serialVersionUID = 3844992024585780582L;

	private String usuario;
	private String nombre;
	private String aplicacion;
	private String transaccion;
	private int idTransaccion;
	private double importe;
	private String estatusTransaccion;
	private String descripcionEstatus;
	private int idRelacionUsuarioAplicacion;
	private String idSesion;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public String getEstatusTransaccion() {
		return estatusTransaccion;
	}
	public void setEstatusTransaccion(String estatusTransaccion) {
		this.estatusTransaccion = estatusTransaccion;
	}
	public String getDescripcionEstatus() {
		return descripcionEstatus;
	}
	public void setDescripcionEstatus(String descripcionEstatus) {
		this.descripcionEstatus = descripcionEstatus;
	}
	public int getIdRelacionUsuarioAplicacion() {
		return idRelacionUsuarioAplicacion;
	}
	public void setIdRelacionUsuarioAplicacion(int idRelacionUsuarioAplicacion) {
		this.idRelacionUsuarioAplicacion = idRelacionUsuarioAplicacion;
	}
	public String getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}
	public int getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
}
