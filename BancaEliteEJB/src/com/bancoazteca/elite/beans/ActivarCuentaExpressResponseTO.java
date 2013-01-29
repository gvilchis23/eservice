package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ActivarCuentaExpressResponseTO implements Serializable{

	private static final long serialVersionUID = 3863346686048596655L;
	

	private String longitudHuella;
	private String llavePublica;
	private String statusRespuesta;
	
	public String getLongitudHuella() {
		return longitudHuella;
	}
	public void setLongitudHuella(String longitudHuella) {
		this.longitudHuella = longitudHuella;
	}
	public String getLlavePublica() {
		return llavePublica;
	}
	public void setLlavePublica(String llavePublica) {
		this.llavePublica = llavePublica;
	}
	public String getStatusRespuesta() {
		return statusRespuesta;
	}
	public void setStatusRespuesta(String statusRespuesta) {
		this.statusRespuesta = statusRespuesta;
	}
	
	

}
