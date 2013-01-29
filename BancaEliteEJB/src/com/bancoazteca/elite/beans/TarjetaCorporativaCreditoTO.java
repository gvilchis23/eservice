package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class TarjetaCorporativaCreditoTO implements Serializable{

	private static final long serialVersionUID = -5936273647005877670L;

	private String movimientos;
	private String fechaInicialDeCorte;
	private String fechaProximoPago;
	private String titularContrato;
	private String fechaLimiteDePago;
	private String tarjetaNueva;
	private BigDecimal creditoUsado;
	private BigDecimal pagoMinimo;
	private String periodos;
	private String tipo;
	private String despuesCorte;
	private BigDecimal retenido;
	private String fechaDeCorte_Nueva;
	private String numDePeriodos;
	private String nombreTitular;
	private String fechaDeCorte;
	private String tarjetasAdicionales;
	private BigDecimal creditoDisponible;
	private BigDecimal credUtilizadoTarjAdicionales;
	private String titularidad;
	private String fechaActivacion;
	private String secuencia;
	private String fechaProximoCorte;
	private String fechaFinalDeCorte;
	private String estatus;
	private String tieneTarjetasAdicionales;
	private BigDecimal creditoUtilizadoAlCorte;
	private String contrato;
	private String tarjetaInvalida;
	private BigDecimal pagoParaNoGenerarIntereses;
	private String tarjetaAdicional;
	private String movimientosVO;
	private String numeroTarjeta;
	private BigDecimal saldoAlCorte;
	private BigDecimal limiteCredito;
	private String fechaLimiteDePago_Nueva;
	
	public String getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(String movimientos) {
		this.movimientos = movimientos;
	}
	public String getFechaInicialDeCorte() {
		return fechaInicialDeCorte;
	}
	public void setFechaInicialDeCorte(String fechaInicialDeCorte) {
		this.fechaInicialDeCorte = fechaInicialDeCorte;
	}
	public String getFechaProximoPago() {
		return fechaProximoPago;
	}
	public void setFechaProximoPago(String fechaProximoPago) {
		this.fechaProximoPago = fechaProximoPago;
	}
	public String getTitularContrato() {
		return titularContrato;
	}
	public void setTitularContrato(String titularContrato) {
		this.titularContrato = titularContrato;
	}
	public String getFechaLimiteDePago() {
		return fechaLimiteDePago;
	}
	public void setFechaLimiteDePago(String fechaLimiteDePago) {
		this.fechaLimiteDePago = fechaLimiteDePago;
	}
	public String getTarjetaNueva() {
		return tarjetaNueva;
	}
	public void setTarjetaNueva(String tarjetaNueva) {
		this.tarjetaNueva = tarjetaNueva;
	}
	public BigDecimal getCreditoUsado() {
		return creditoUsado;
	}
	public void setCreditoUsado(BigDecimal creditoUsado) {
		this.creditoUsado = creditoUsado;
	}
	public BigDecimal getPagoMinimo() {
		return pagoMinimo;
	}
	public void setPagoMinimo(BigDecimal pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}
	public String getPeriodos() {
		return periodos;
	}
	public void setPeriodos(String periodos) {
		this.periodos = periodos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDespuesCorte() {
		return despuesCorte;
	}
	public void setDespuesCorte(String despuesCorte) {
		this.despuesCorte = despuesCorte;
	}
	public BigDecimal getRetenido() {
		return retenido;
	}
	public void setRetenido(BigDecimal retenido) {
		this.retenido = retenido;
	}
	public String getFechaDeCorte_Nueva() {
		return fechaDeCorte_Nueva;
	}
	public void setFechaDeCorte_Nueva(String fechaDeCorte_Nueva) {
		this.fechaDeCorte_Nueva = fechaDeCorte_Nueva;
	}
	public String getNumDePeriodos() {
		return numDePeriodos;
	}
	public void setNumDePeriodos(String numDePeriodos) {
		this.numDePeriodos = numDePeriodos;
	}
	public String getNombreTitular() {
		return nombreTitular;
	}
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	public String getFechaDeCorte() {
		return fechaDeCorte;
	}
	public void setFechaDeCorte(String fechaDeCorte) {
		this.fechaDeCorte = fechaDeCorte;
	}
	public String getTarjetasAdicionales() {
		return tarjetasAdicionales;
	}
	public void setTarjetasAdicionales(String tarjetasAdicionales) {
		this.tarjetasAdicionales = tarjetasAdicionales;
	}
	public BigDecimal getCreditoDisponible() {
		return creditoDisponible;
	}
	public void setCreditoDisponible(BigDecimal creditoDisponible) {
		this.creditoDisponible = creditoDisponible;
	}
	public BigDecimal getCredUtilizadoTarjAdicionales() {
		return credUtilizadoTarjAdicionales;
	}
	public void setCredUtilizadoTarjAdicionales(
			BigDecimal credUtilizadoTarjAdicionales) {
		this.credUtilizadoTarjAdicionales = credUtilizadoTarjAdicionales;
	}
	public String getTitularidad() {
		return titularidad;
	}
	public void setTitularidad(String titularidad) {
		this.titularidad = titularidad;
	}
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getFechaProximoCorte() {
		return fechaProximoCorte;
	}
	public void setFechaProximoCorte(String fechaProximoCorte) {
		this.fechaProximoCorte = fechaProximoCorte;
	}
	public String getFechaFinalDeCorte() {
		return fechaFinalDeCorte;
	}
	public void setFechaFinalDeCorte(String fechaFinalDeCorte) {
		this.fechaFinalDeCorte = fechaFinalDeCorte;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getTieneTarjetasAdicionales() {
		return tieneTarjetasAdicionales;
	}
	public void setTieneTarjetasAdicionales(String tieneTarjetasAdicionales) {
		this.tieneTarjetasAdicionales = tieneTarjetasAdicionales;
	}
	public BigDecimal getCreditoUtilizadoAlCorte() {
		return creditoUtilizadoAlCorte;
	}
	public void setCreditoUtilizadoAlCorte(BigDecimal creditoUtilizadoAlCorte) {
		this.creditoUtilizadoAlCorte = creditoUtilizadoAlCorte;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getTarjetaInvalida() {
		return tarjetaInvalida;
	}
	public void setTarjetaInvalida(String tarjetaInvalida) {
		this.tarjetaInvalida = tarjetaInvalida;
	}
	public BigDecimal getPagoParaNoGenerarIntereses() {
		return pagoParaNoGenerarIntereses;
	}
	public void setPagoParaNoGenerarIntereses(BigDecimal pagoParaNoGenerarIntereses) {
		this.pagoParaNoGenerarIntereses = pagoParaNoGenerarIntereses;
	}
	public String getTarjetaAdicional() {
		return tarjetaAdicional;
	}
	public void setTarjetaAdicional(String tarjetaAdicional) {
		this.tarjetaAdicional = tarjetaAdicional;
	}
	public String getMovimientosVO() {
		return movimientosVO;
	}
	public void setMovimientosVO(String movimientosVO) {
		this.movimientosVO = movimientosVO;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public BigDecimal getSaldoAlCorte() {
		return saldoAlCorte;
	}
	public void setSaldoAlCorte(BigDecimal saldoAlCorte) {
		this.saldoAlCorte = saldoAlCorte;
	}
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public String getFechaLimiteDePago_Nueva() {
		return fechaLimiteDePago_Nueva;
	}
	public void setFechaLimiteDePago_Nueva(String fechaLimiteDePago_Nueva) {
		this.fechaLimiteDePago_Nueva = fechaLimiteDePago_Nueva;
	}
	
}
