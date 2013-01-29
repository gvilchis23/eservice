package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class DetallePagoCreditoTO implements Serializable{

	private static final long serialVersionUID = -6257906435907165411L;

	private String fechaInicioCalculo;
	private Double seguroDanios;
	private String diasCalculo;
	private Double ivaIntereses;
	private String numPago;
	private Double amortizacionCapital;
	private Double tasa;
	private Double saldoInsoluto;
	private Double pagoARealizar;
	private Double seguroVida;
	private Double intereses;
	private String fechaFinCalculo;
	
	public String getFechaInicioCalculo() {
		return fechaInicioCalculo;
	}
	public void setFechaInicioCalculo(String fechaInicioCalculo) {
		this.fechaInicioCalculo = fechaInicioCalculo;
	}
	public Double getSeguroDanios() {
		return seguroDanios;
	}
	public void setSeguroDanios(Double seguroDanios) {
		this.seguroDanios = seguroDanios;
	}
	public String getDiasCalculo() {
		return diasCalculo;
	}
	public void setDiasCalculo(String diasCalculo) {
		this.diasCalculo = diasCalculo;
	}
	public Double getIvaIntereses() {
		return ivaIntereses;
	}
	public void setIvaIntereses(Double ivaIntereses) {
		this.ivaIntereses = ivaIntereses;
	}
	public String getNumPago() {
		return numPago;
	}
	public void setNumPago(String numPago) {
		this.numPago = numPago;
	}
	public Double getAmortizacionCapital() {
		return amortizacionCapital;
	}
	public void setAmortizacionCapital(Double amortizacionCapital) {
		this.amortizacionCapital = amortizacionCapital;
	}
	public Double getTasa() {
		return tasa;
	}
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	public Double getSaldoInsoluto() {
		return saldoInsoluto;
	}
	public void setSaldoInsoluto(Double saldoInsoluto) {
		this.saldoInsoluto = saldoInsoluto;
	}
	public Double getPagoARealizar() {
		return pagoARealizar;
	}
	public void setPagoARealizar(Double pagoARealizar) {
		this.pagoARealizar = pagoARealizar;
	}
	public Double getSeguroVida() {
		return seguroVida;
	}
	public void setSeguroVida(Double seguroVida) {
		this.seguroVida = seguroVida;
	}
	public Double getIntereses() {
		return intereses;
	}
	public void setIntereses(Double intereses) {
		this.intereses = intereses;
	}
	public String getFechaFinCalculo() {
		return fechaFinCalculo;
	}
	public void setFechaFinCalculo(String fechaFinCalculo) {
		this.fechaFinCalculo = fechaFinCalculo;
	}
	
	
}
