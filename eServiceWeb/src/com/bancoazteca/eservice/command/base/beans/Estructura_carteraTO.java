package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Estructura_carteraTO implements Serializable {

	private static final long serialVersionUID = 8354247698476438650L;

	private Estructura_mercado_dineroTO mes_actual;
	private Estructura_mercado_dineroTO mes_anterior;
	
	public Estructura_mercado_dineroTO getMes_actual() {
		return mes_actual;
	}
	public void setMes_actual(Estructura_mercado_dineroTO mes_actual) {
		this.mes_actual = mes_actual;
	}
	public Estructura_mercado_dineroTO getMes_anterior() {
		return mes_anterior;
	}
	public void setMes_anterior(Estructura_mercado_dineroTO mes_anterior) {
		this.mes_anterior = mes_anterior;
	}
		
}
