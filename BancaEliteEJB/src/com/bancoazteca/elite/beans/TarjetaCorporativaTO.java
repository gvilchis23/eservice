package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class TarjetaCorporativaTO implements Serializable {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6031836225527884804L;
	
	private String tipo;
	private String titularidad;
	private String nombreTitular;
	private String limiteCredito;
	private String creditoUsado;
	private String retenido;
	private String creditoDisponible;
	private String pagoMinimo;
	private String fechaLimiteDePago;
	private String creditoUtilizadoAlCorte;
	private String fechaDeCorte;
	private String numDePeriodos;
	private String estatus;
	private String saldoAlCorte;
	private String pagoParaNoGenerarIntereses;
	private String fechaInicialDeCorte;
	private String fechaFinalDeCorte;
	private String despuesCorte;
	private String tarjetaInvalida;
	private String tarjetaNueva;
	private String fechaProximoCorte;
	private String fechaProximoPago;
	private String fechaActivacion;
	private String tieneTarjetasAdicionales;
	private String tarjetaAdicional;
	private String credUtilizadoTarjAdicionales;
	private String tieneCargosAdicionales;
	private String secuencia;
	private String titularContrato;

	
	
	public TarjetaCorporativaTO(){
		limiteCredito = "0";
		creditoUsado = "0";
		retenido = "0";
		creditoDisponible = "0";
		pagoMinimo = "0";
		creditoUtilizadoAlCorte = "0";
		saldoAlCorte = "0";
		pagoParaNoGenerarIntereses = "0";
		credUtilizadoTarjAdicionales = "0";
	}



	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}



	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	/**
	 * @return the titularidad
	 */
	public String getTitularidad() {
		return titularidad;
	}



	/**
	 * @param titularidad the titularidad to set
	 */
	public void setTitularidad(String titularidad) {
		this.titularidad = titularidad;
	}



	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}



	/**
	 * @param nombreTitular the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}



	/**
	 * @return the limiteCredito
	 */
	public String getLimiteCredito() {
		return limiteCredito;
	}



	/**
	 * @param limiteCredito the limiteCredito to set
	 */
	public void setLimiteCredito(String limiteCredito) {
		this.limiteCredito = limiteCredito;
	}



	/**
	 * @return the creditoUsado
	 */
	public String getCreditoUsado() {
		return creditoUsado;
	}



	/**
	 * @param creditoUsado the creditoUsado to set
	 */
	public void setCreditoUsado(String creditoUsado) {
		this.creditoUsado = creditoUsado;
	}



	/**
	 * @return the retenido
	 */
	public String getRetenido() {
		return retenido;
	}



	/**
	 * @param retenido the retenido to set
	 */
	public void setRetenido(String retenido) {
		this.retenido = retenido;
	}



	/**
	 * @return the creditoDisponible
	 */
	public String getCreditoDisponible() {
		return creditoDisponible;
	}



	/**
	 * @param creditoDisponible the creditoDisponible to set
	 */
	public void setCreditoDisponible(String creditoDisponible) {
		this.creditoDisponible = creditoDisponible;
	}



	/**
	 * @return the pagoMinimo
	 */
	public String getPagoMinimo() {
		return pagoMinimo;
	}



	/**
	 * @param pagoMinimo the pagoMinimo to set
	 */
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}



	/**
	 * @return the fechaLimiteDePago
	 */
	public String getFechaLimiteDePago() {
		return fechaLimiteDePago;
	}



	/**
	 * @param fechaLimiteDePago the fechaLimiteDePago to set
	 */
	public void setFechaLimiteDePago(String fechaLimiteDePago) {
		this.fechaLimiteDePago = fechaLimiteDePago;
	}



	/**
	 * @return the creditoUtilizadoAlCorte
	 */
	public String getCreditoUtilizadoAlCorte() {
		return creditoUtilizadoAlCorte;
	}



	/**
	 * @param creditoUtilizadoAlCorte the creditoUtilizadoAlCorte to set
	 */
	public void setCreditoUtilizadoAlCorte(String creditoUtilizadoAlCorte) {
		this.creditoUtilizadoAlCorte = creditoUtilizadoAlCorte;
	}



	/**
	 * @return the fechaDeCorte
	 */
	public String getFechaDeCorte() {
		return fechaDeCorte;
	}



	/**
	 * @param fechaDeCorte the fechaDeCorte to set
	 */
	public void setFechaDeCorte(String fechaDeCorte) {
		this.fechaDeCorte = fechaDeCorte;
	}



	/**
	 * @return the numDePeriodos
	 */
	public String getNumDePeriodos() {
		return numDePeriodos;
	}



	/**
	 * @param numDePeriodos the numDePeriodos to set
	 */
	public void setNumDePeriodos(String numDePeriodos) {
		this.numDePeriodos = numDePeriodos;
	}



	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}



	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}



	/**
	 * @return the saldoAlCorte
	 */
	public String getSaldoAlCorte() {
		return saldoAlCorte;
	}



	/**
	 * @param saldoAlCorte the saldoAlCorte to set
	 */
	public void setSaldoAlCorte(String saldoAlCorte) {
		this.saldoAlCorte = saldoAlCorte;
	}



	/**
	 * @return the pagoParaNoGenerarIntereses
	 */
	public String getPagoParaNoGenerarIntereses() {
		return pagoParaNoGenerarIntereses;
	}



	/**
	 * @param pagoParaNoGenerarIntereses the pagoParaNoGenerarIntereses to set
	 */
	public void setPagoParaNoGenerarIntereses(String pagoParaNoGenerarIntereses) {
		this.pagoParaNoGenerarIntereses = pagoParaNoGenerarIntereses;
	}



	/**
	 * @return the fechaInicialDeCorte
	 */
	public String getFechaInicialDeCorte() {
		return fechaInicialDeCorte;
	}



	/**
	 * @param fechaInicialDeCorte the fechaInicialDeCorte to set
	 */
	public void setFechaInicialDeCorte(String fechaInicialDeCorte) {
		this.fechaInicialDeCorte = fechaInicialDeCorte;
	}



	/**
	 * @return the fechaFinalDeCorte
	 */
	public String getFechaFinalDeCorte() {
		return fechaFinalDeCorte;
	}



	/**
	 * @param fechaFinalDeCorte the fechaFinalDeCorte to set
	 */
	public void setFechaFinalDeCorte(String fechaFinalDeCorte) {
		this.fechaFinalDeCorte = fechaFinalDeCorte;
	}



	/**
	 * @return the despuesCorte
	 */
	public String getDespuesCorte() {
		return despuesCorte;
	}



	/**
	 * @param despuesCorte the despuesCorte to set
	 */
	public void setDespuesCorte(String despuesCorte) {
		this.despuesCorte = despuesCorte;
	}



	/**
	 * @return the tarjetaInvalida
	 */
	public String getTarjetaInvalida() {
		return tarjetaInvalida;
	}



	/**
	 * @param tarjetaInvalida the tarjetaInvalida to set
	 */
	public void setTarjetaInvalida(String tarjetaInvalida) {
		this.tarjetaInvalida = tarjetaInvalida;
	}



	/**
	 * @return the tarjetaNueva
	 */
	public String getTarjetaNueva() {
		return tarjetaNueva;
	}



	/**
	 * @param tarjetaNueva the tarjetaNueva to set
	 */
	public void setTarjetaNueva(String tarjetaNueva) {
		this.tarjetaNueva = tarjetaNueva;
	}



	/**
	 * @return the fechaProximoCorte
	 */
	public String getFechaProximoCorte() {
		return fechaProximoCorte;
	}



	/**
	 * @param fechaProximoCorte the fechaProximoCorte to set
	 */
	public void setFechaProximoCorte(String fechaProximoCorte) {
		this.fechaProximoCorte = fechaProximoCorte;
	}



	/**
	 * @return the fechaProximoPago
	 */
	public String getFechaProximoPago() {
		return fechaProximoPago;
	}



	/**
	 * @param fechaProximoPago the fechaProximoPago to set
	 */
	public void setFechaProximoPago(String fechaProximoPago) {
		this.fechaProximoPago = fechaProximoPago;
	}



	/**
	 * @return the fechaActivacion
	 */
	public String getFechaActivacion() {
		return fechaActivacion;
	}



	/**
	 * @param fechaActivacion the fechaActivacion to set
	 */
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}



	/**
	 * @return the tieneTarjetasAdicionales
	 */
	public String getTieneTarjetasAdicionales() {
		return tieneTarjetasAdicionales;
	}



	/**
	 * @param tieneTarjetasAdicionales the tieneTarjetasAdicionales to set
	 */
	public void setTieneTarjetasAdicionales(String tieneTarjetasAdicionales) {
		this.tieneTarjetasAdicionales = tieneTarjetasAdicionales;
	}



	/**
	 * @return the tarjetaAdicional
	 */
	public String getTarjetaAdicional() {
		return tarjetaAdicional;
	}



	/**
	 * @param tarjetaAdicional the tarjetaAdicional to set
	 */
	public void setTarjetaAdicional(String tarjetaAdicional) {
		this.tarjetaAdicional = tarjetaAdicional;
	}



	/**
	 * @return the credUtilizadoTarjAdicionales
	 */
	public String getCredUtilizadoTarjAdicionales() {
		return credUtilizadoTarjAdicionales;
	}



	/**
	 * @param credUtilizadoTarjAdicionales the credUtilizadoTarjAdicionales to set
	 */
	public void setCredUtilizadoTarjAdicionales(String credUtilizadoTarjAdicionales) {
		this.credUtilizadoTarjAdicionales = credUtilizadoTarjAdicionales;
	}



	/**
	 * @return the tieneCargosAdicionales
	 */
	public String getTieneCargosAdicionales() {
		return tieneCargosAdicionales;
	}



	/**
	 * @param tieneCargosAdicionales the tieneCargosAdicionales to set
	 */
	public void setTieneCargosAdicionales(String tieneCargosAdicionales) {
		this.tieneCargosAdicionales = tieneCargosAdicionales;
	}



	/**
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}



	/**
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}



	/**
	 * @return the titularContrato
	 */
	public String getTitularContrato() {
		return titularContrato;
	}



	/**
	 * @param titularContrato the titularContrato to set
	 */
	public void setTitularContrato(String titularContrato) {
		this.titularContrato = titularContrato;
	}

}
