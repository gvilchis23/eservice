package com.bancoazteca.eservice.command.base.beans;

public class ChequeraTO {
	
	private String descripcion;
	private String regimen_firmas;
	private String importe_piso;
	private String proteccion_chequera;
	private String estado;
	private String cheque_final;
	private String cheque_inicial;
	private String chequera_id;
	
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
	public String getChequera_id() {
		return chequera_id;
	}
	public void setChequera_id(String chequera_id) {
		this.chequera_id = chequera_id;
	}
	public String getProteccion_chequera() {
		return proteccion_chequera;
	}
	public void setProteccion_chequera(String proteccion_chequera) {
		this.proteccion_chequera = proteccion_chequera;
	}
}
