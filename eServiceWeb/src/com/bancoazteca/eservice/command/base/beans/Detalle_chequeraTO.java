package com.bancoazteca.eservice.command.base.beans;

public class Detalle_chequeraTO {
	
	private String descripcion;
	private String regimen_firmas;
	private String importe_piso;
	private String proteccion;
	private String estado;
	private String cheque_final;
	private String cheque_inicial;
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRegimen_firmas() {
		return regimen_firmas;
	}
	public void setRegimen_firmas(String regimen_firmas) {
		this.regimen_firmas = regimen_firmas;
	}
	public String getImporte_piso() {
		return importe_piso;
	}
	public void setImporte_piso(String importe_piso) {
		this.importe_piso = importe_piso;
	}
	public String getProteccion() {
		return proteccion;
	}
	public void setProteccion(String proteccion) {
		this.proteccion = proteccion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCheque_final() {
		return cheque_final;
	}
	public void setCheque_final(String cheque_final) {
		this.cheque_final = cheque_final;
	}
	public String getCheque_inicial() {
		return cheque_inicial;
	}
	public void setCheque_inicial(String cheque_inicial) {
		this.cheque_inicial = cheque_inicial;
	}
}
