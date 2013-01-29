package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Estado_cuenta_mercado_dinero_solicitudTO implements Serializable{

	private static final long serialVersionUID = 1851973461388026618L;

	private Collection<String> coleccion_anios;
	private Collection<String> coleccion_meses;
	private String numero_contrato;
	private Cuenta_CargoTO cuenta_eje;
	
	public Collection<String> getColeccion_anios() {
		return coleccion_anios;
	}
	public void setColeccion_anios(Collection<String> coleccion_anios) {
		this.coleccion_anios = coleccion_anios;
	}
	public Collection<String> getColeccion_meses() {
		return coleccion_meses;
	}
	public void setColeccion_meses(Collection<String> coleccion_meses) {
		this.coleccion_meses = coleccion_meses;
	}
	public String getNumero_contrato() {
		return numero_contrato;
	}
	public void setNumero_contrato(String numero_contrato) {
		this.numero_contrato = numero_contrato;
	}
	public Cuenta_CargoTO getCuenta_eje() {
		return cuenta_eje;
	}
	public void setCuenta_eje(Cuenta_CargoTO cuenta_eje) {
		this.cuenta_eje = cuenta_eje;
	}
	
}
