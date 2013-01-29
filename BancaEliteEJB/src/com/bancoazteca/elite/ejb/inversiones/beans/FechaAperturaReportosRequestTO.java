package com.bancoazteca.elite.ejb.inversiones.beans;


public class FechaAperturaReportosRequestTO extends InversionesRequestTO {
	
	private static final long serialVersionUID = -361365882435279556L;
	
	private String idAlnova;
	private String numeroContrato;
	private String anioPeriodo;
	
	public String getIdAlnova() {
		return idAlnova;
	}
	public void setIdAlnova(String idAlnova) {
		this.idAlnova = idAlnova;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getAnioPeriodo() {
		return anioPeriodo;
	}
	public void setAnioPeriodo(String anioPeriodo) {
		this.anioPeriodo = anioPeriodo;
	}	
		
}
