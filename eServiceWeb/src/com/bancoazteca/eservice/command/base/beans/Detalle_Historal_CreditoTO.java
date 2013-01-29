package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Detalle_Historal_CreditoTO implements Serializable{

	private static final long serialVersionUID = -6257906435907165411L;

	private String fecha_inicio_calculo;
	private String seguro_danios;
	private String dias_calculo;
	private String iva_intereses;
	private String num_pago;
	private String amortizacion_capital;
	private String tasa;
	private String saldo_insoluto;
	private String pago_a_realizar;
	private String seguro_vida;
	private String intereses;
	private String fecha_fin_calculo;
	
	public String getFecha_inicio_calculo() {
		return fecha_inicio_calculo;
	}
	public void setFecha_inicio_calculo(String fecha_inicio_calculo) {
		this.fecha_inicio_calculo = fecha_inicio_calculo;
	}
	public String getSeguro_danios() {
		return seguro_danios;
	}
	public void setSeguro_danios(String seguro_danios) {
		this.seguro_danios = seguro_danios;
	}
	public String getDias_calculo() {
		return dias_calculo;
	}
	public void setDias_calculo(String dias_calculo) {
		this.dias_calculo = dias_calculo;
	}
	public String getIva_intereses() {
		return iva_intereses;
	}
	public void setIva_intereses(String iva_intereses) {
		this.iva_intereses = iva_intereses;
	}
	public String getNum_pago() {
		return num_pago;
	}
	public void setNum_pago(String num_pago) {
		this.num_pago = num_pago;
	}
	public String getAmortizacion_capital() {
		return amortizacion_capital;
	}
	public void setAmortizacion_capital(String amortizacion_capital) {
		this.amortizacion_capital = amortizacion_capital;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getSaldo_insoluto() {
		return saldo_insoluto;
	}
	public void setSaldo_insoluto(String saldo_insoluto) {
		this.saldo_insoluto = saldo_insoluto;
	}
	public String getPago_a_realizar() {
		return pago_a_realizar;
	}
	public void setPago_a_realizar(String pago_a_realizar) {
		this.pago_a_realizar = pago_a_realizar;
	}
	public String getSeguro_vida() {
		return seguro_vida;
	}
	public void setSeguro_vida(String seguro_vida) {
		this.seguro_vida = seguro_vida;
	}
	public String getIntereses() {
		return intereses;
	}
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}
	public String getFecha_fin_calculo() {
		return fecha_fin_calculo;
	}
	public void setFecha_fin_calculo(String fecha_fin_calculo) {
		this.fecha_fin_calculo = fecha_fin_calculo;
	}
	
	
	
	
}
