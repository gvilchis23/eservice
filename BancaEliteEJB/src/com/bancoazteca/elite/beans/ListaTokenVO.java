package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ListaTokenVO implements Serializable {

	private static final long serialVersionUID = -7860860652496657263L;

	private String statusToken;
	private String respuesta;
	private String fechaActi;
	private String numSerie;
	private String codRespuesta;
	private String esProcesoExitoso;
	
	public String getStatusToken() {
		return statusToken;
	}
	public void setStatusToken(String statusToken) {
		this.statusToken = statusToken;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getFechaActi() {
		return fechaActi;
	}
	public void setFechaActi(String fechaActi) {
		this.fechaActi = fechaActi;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public String getCodRespuesta() {
		return codRespuesta;
	}
	public void setCodRespuesta(String codRespuesta) {
		this.codRespuesta = codRespuesta;
	}
	public String getEsProcesoExitoso() {
		return esProcesoExitoso;
	}
	public void setEsProcesoExitoso(String esProcesoExitoso) {
		this.esProcesoExitoso = esProcesoExitoso;
	}
}
