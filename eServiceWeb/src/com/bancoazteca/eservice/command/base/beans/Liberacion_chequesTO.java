package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Liberacion_chequesTO implements Serializable{

	private static final long serialVersionUID = -6980743470879278497L;
	
	private String numero_cuenta;
	private String tipo_cuenta;
	private String numero_cheque;
	private String monto_cheque;
	private String descripcion_chequera;
	private String numero_chequera;
	private String fecha_operacion;
	
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getTipo_cuenta() {
		return tipo_cuenta;
	}
	public void setTipo_cuenta(String tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}
	public String getNumero_cheque() {
		return numero_cheque;
	}
	public void setNumero_cheque(String numero_cheque) {
		this.numero_cheque = numero_cheque;
	}
	public String getMonto_cheque() {
		return monto_cheque;
	}
	public void setMonto_cheque(String monto_cheque) {
		this.monto_cheque = monto_cheque;
	}
	public String getDescripcion_chequera() {
		return descripcion_chequera;
	}
	public void setDescripcion_chequera(String descripcion_chequera) {
		this.descripcion_chequera = descripcion_chequera;
	}
	public String getNumero_chequera() {
		return numero_chequera;
	}
	public void setNumero_chequera(String numero_chequera) {
		this.numero_chequera = numero_chequera;
	}
	public String getFecha_operacion() {
		return fecha_operacion;
	}
	public void setFecha_operacion(String fecha_operacion) {
		this.fecha_operacion = fecha_operacion;
	}
	
}
