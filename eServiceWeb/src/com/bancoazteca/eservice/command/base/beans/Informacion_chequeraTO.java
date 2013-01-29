package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Informacion_chequeraTO {
	private Collection<Catalogo_estatus_chequesTO> coleccion_estatus_cheques;
	private String nombre_cliente;
	private String numero_cuenta;
	private String tipo_cuenta;
	private String numero_chequera;
	private String tipo_chequera;
	public Collection<Catalogo_estatus_chequesTO> getColeccion_estatus_cheques() {
		return coleccion_estatus_cheques;
	}
	public void setColeccion_estatus_cheques(
			Collection<Catalogo_estatus_chequesTO> coleccion_estatus_cheques) {
		this.coleccion_estatus_cheques = coleccion_estatus_cheques;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
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
	public String getNumero_chequera() {
		return numero_chequera;
	}
	public void setNumero_chequera(String numero_chequera) {
		this.numero_chequera = numero_chequera;
	}
	public String getTipo_chequera() {
		return tipo_chequera;
	}
	public void setTipo_chequera(String tipo_chequera) {
		this.tipo_chequera = tipo_chequera;
	}
}
