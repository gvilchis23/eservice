package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class AlertsCardsTO implements Serializable{
	

	private static final long serialVersionUID = -7748104398426833779L;
	
	private String telefono;
	private String compania;
	private String tipoTarjeta;
	private String status;
	private String numeroTarjeta;
	private String fecha;
	private String abono;
	private String cargo;
	private String montoAbono;
	private String montoCargo;
	
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getAbono() {
		return abono;
	}
	public void setAbono(String abono) {
		this.abono = abono;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getMontoAbono() {
		return montoAbono;
	}
	public void setMontoAbono(String montoAbono) {
		this.montoAbono = montoAbono;
	}
	public String getMontoCargo() {
		return montoCargo;
	}
	public void setMontoCargo(String montoCargo) {
		this.montoCargo = montoCargo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}

	
	
}
