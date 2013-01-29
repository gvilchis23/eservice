package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Movimientos_inversion_directoTO implements Serializable {

	private static final long serialVersionUID = -6216104116227407729L;
	
	private String emisora; 
	private String serie;
	private Inversion_directoTO mes_actual;
	private Inversion_directoTO mes_anterior;
	
	public String getEmisora() {
		return emisora;
	}
	public void setEmisora(String emisora) {
		this.emisora = emisora;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Inversion_directoTO getMes_actual() {
		return mes_actual;
	}
	public void setMes_actual(Inversion_directoTO mes_actual) {
		this.mes_actual = mes_actual;
	}
	public Inversion_directoTO getMes_anterior() {
		return mes_anterior;
	}
	public void setMes_anterior(Inversion_directoTO mes_anterior) {
		this.mes_anterior = mes_anterior;
	}
			
}
