package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Regimen_operativo_fiscalTO implements Serializable {

	private static final long serialVersionUID = -1543047295939372771L;
	
	private String cartera_mes_anterior;
	private String mas_entradas;
	private String menos_salidas;
	private String cartera_mes_actual;
	private String variacion_mesual;
	private String isr_retenido;
	private String iva_acreditable;
	
	public String getCartera_mes_anterior() {
		return cartera_mes_anterior;
	}
	public void setCartera_mes_anterior(String cartera_mes_anterior) {
		this.cartera_mes_anterior = cartera_mes_anterior;
	}
	public String getMas_entradas() {
		return mas_entradas;
	}
	public void setMas_entradas(String mas_entradas) {
		this.mas_entradas = mas_entradas;
	}
	public String getMenos_salidas() {
		return menos_salidas;
	}
	public void setMenos_salidas(String menos_salidas) {
		this.menos_salidas = menos_salidas;
	}
	public String getCartera_mes_actual() {
		return cartera_mes_actual;
	}
	public void setCartera_mes_actual(String cartera_mes_actual) {
		this.cartera_mes_actual = cartera_mes_actual;
	}
	public String getVariacion_mesual() {
		return variacion_mesual;
	}
	public void setVariacion_mesual(String variacion_mesual) {
		this.variacion_mesual = variacion_mesual;
	}
	public String getIsr_retenido() {
		return isr_retenido;
	}
	public void setIsr_retenido(String isr_retenido) {
		this.isr_retenido = isr_retenido;
	}
	public String getIva_acreditable() {
		return iva_acreditable;
	}
	public void setIva_acreditable(String iva_acreditable) {
		this.iva_acreditable = iva_acreditable;
	}
	
}
