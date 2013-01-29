package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Banco Azteca S.A. [JFAV] Octubre 8, 2008. 
 */
public class SecurityAmountsTO implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 4848127558717203117L;
	
	//Fields
	private String fechaUltimoMov;
	private BigDecimal sumMaxDiarioCalculado;
	private BigDecimal maxTercerosSugerido;
	private String maxTercerosAsString;
	private BigDecimal internacionales;
	private BigDecimal interbancario;
	private BigDecimal maxPagosSugerido;
	private BigDecimal sumDiarioCalculado;
	private String maxInternacionalesAsString;
	private BigDecimal maxInterbancario;
	private BigDecimal pagos;
	private BigDecimal maxPagos;
	private BigDecimal maxDiario;
	private BigDecimal maxDiarioSugerido;
	private BigDecimal maxInterbancarioSugerido;
	private String maxInterbancarioAsString;
	private String maxPagosAsString;
	private String vacio;	
	private BigDecimal terceros;
	private BigDecimal maxTerceros;
	private BigDecimal diario;
	private BigDecimal maxInternacionalesSugerido;
	private BigDecimal maxInternacionales;
	
	/**
	 * @return the fechaUltimoMov
	 */
	public String getFechaUltimoMov() {
		return fechaUltimoMov;
	}
	/**
	 * @param fechaUltimoMov the fechaUltimoMov to set
	 */
	public void setFechaUltimoMov(String fechaUltimoMov) {
		this.fechaUltimoMov = fechaUltimoMov;
	}
	/**
	 * @return the sumMaxDiarioCalculado
	 */
	public BigDecimal getSumMaxDiarioCalculado() {
		return sumMaxDiarioCalculado;
	}
	/**
	 * @param sumMaxDiarioCalculado the sumMaxDiarioCalculado to set
	 */
	public void setSumMaxDiarioCalculado(BigDecimal sumMaxDiarioCalculado) {
		this.sumMaxDiarioCalculado = sumMaxDiarioCalculado;
	}
	/**
	 * @return the maxTercerosSugerido
	 */
	public BigDecimal getMaxTercerosSugerido() {
		return maxTercerosSugerido;
	}
	/**
	 * @param maxTercerosSugerido the maxTercerosSugerido to set
	 */
	public void setMaxTercerosSugerido(BigDecimal maxTercerosSugerido) {
		this.maxTercerosSugerido = maxTercerosSugerido;
	}
	/**
	 * @return the maxTercerosAsString
	 */
	public String getMaxTercerosAsString() {
		return maxTercerosAsString;
	}
	/**
	 * @param maxTercerosAsString the maxTercerosAsString to set
	 */
	public void setMaxTercerosAsString(String maxTercerosAsString) {
		this.maxTercerosAsString = maxTercerosAsString;
	}
	/**
	 * @return the internacionales
	 */
	public BigDecimal getInternacionales() {
		return internacionales;
	}
	/**
	 * @param internacionales the internacionales to set
	 */
	public void setInternacionales(BigDecimal internacionales) {
		this.internacionales = internacionales;
	}
	/**
	 * @return the interbancario
	 */
	public BigDecimal getInterbancario() {
		return interbancario;
	}
	/**
	 * @param interbancario the interbancario to set
	 */
	public void setInterbancario(BigDecimal interbancario) {
		this.interbancario = interbancario;
	}
	/**
	 * @return the maxPagosSugerido
	 */
	public BigDecimal getMaxPagosSugerido() {
		return maxPagosSugerido;
	}
	/**
	 * @param maxPagosSugerido the maxPagosSugerido to set
	 */
	public void setMaxPagosSugerido(BigDecimal maxPagosSugerido) {
		this.maxPagosSugerido = maxPagosSugerido;
	}
	/**
	 * @return the sumDiarioCalculado
	 */
	public BigDecimal getSumDiarioCalculado() {
		return sumDiarioCalculado;
	}
	/**
	 * @param sumDiarioCalculado the sumDiarioCalculado to set
	 */
	public void setSumDiarioCalculado(BigDecimal sumDiarioCalculado) {
		this.sumDiarioCalculado = sumDiarioCalculado;
	}
	/**
	 * @return the maxInternacionalesAsString
	 */
	public String getMaxInternacionalesAsString() {
		return maxInternacionalesAsString;
	}
	/**
	 * @param maxInternacionalesAsString the maxInternacionalesAsString to set
	 */
	public void setMaxInternacionalesAsString(String maxInternacionalesAsString) {
		this.maxInternacionalesAsString = maxInternacionalesAsString;
	}
	/**
	 * @return the maxInterbancario
	 */
	public BigDecimal getMaxInterbancario() {
		return maxInterbancario;
	}
	/**
	 * @param maxInterbancario the maxInterbancario to set
	 */
	public void setMaxInterbancario(BigDecimal maxInterbancario) {
		this.maxInterbancario = maxInterbancario;
	}
	/**
	 * @return the pagos
	 */
	public BigDecimal getPagos() {
		return pagos;
	}
	/**
	 * @param pagos the pagos to set
	 */
	public void setPagos(BigDecimal pagos) {
		this.pagos = pagos;
	}
	/**
	 * @return the maxPagos
	 */
	public BigDecimal getMaxPagos() {
		return maxPagos;
	}
	/**
	 * @param maxPagos the maxPagos to set
	 */
	public void setMaxPagos(BigDecimal maxPagos) {
		this.maxPagos = maxPagos;
	}
	/**
	 * @return the maxDiario
	 */
	public BigDecimal getMaxDiario() {
		return maxDiario;
	}
	/**
	 * @param maxDiario the maxDiario to set
	 */
	public void setMaxDiario(BigDecimal maxDiario) {
		this.maxDiario = maxDiario;
	}
	/**
	 * @return the maxDiarioSugerido
	 */
	public BigDecimal getMaxDiarioSugerido() {
		return maxDiarioSugerido;
	}
	/**
	 * @param maxDiarioSugerido the maxDiarioSugerido to set
	 */
	public void setMaxDiarioSugerido(BigDecimal maxDiarioSugerido) {
		this.maxDiarioSugerido = maxDiarioSugerido;
	}
	/**
	 * @return the maxInterbancarioSugerido
	 */
	public BigDecimal getMaxInterbancarioSugerido() {
		return maxInterbancarioSugerido;
	}
	/**
	 * @param maxInterbancarioSugerido the maxInterbancarioSugerido to set
	 */
	public void setMaxInterbancarioSugerido(BigDecimal maxInterbancarioSugerido) {
		this.maxInterbancarioSugerido = maxInterbancarioSugerido;
	}
	/**
	 * @return the maxInterbancarioAsString
	 */
	public String getMaxInterbancarioAsString() {
		return maxInterbancarioAsString;
	}
	/**
	 * @param maxInterbancarioAsString the maxInterbancarioAsString to set
	 */
	public void setMaxInterbancarioAsString(String maxInterbancarioAsString) {
		this.maxInterbancarioAsString = maxInterbancarioAsString;
	}
	/**
	 * @return the maxPagosAsString
	 */
	public String getMaxPagosAsString() {
		return maxPagosAsString;
	}
	/**
	 * @param maxPagosAsString the maxPagosAsString to set
	 */
	public void setMaxPagosAsString(String maxPagosAsString) {
		this.maxPagosAsString = maxPagosAsString;
	}
	/**
	 * @return the vacio
	 */
	public String getVacio() {
		return vacio;
	}
	/**
	 * @param vacio the vacio to set
	 */
	public void setVacio(String vacio) {
		this.vacio = vacio;
	}
	/**
	 * @return the terceros
	 */
	public BigDecimal getTerceros() {
		return terceros;
	}
	/**
	 * @param terceros the terceros to set
	 */
	public void setTerceros(BigDecimal terceros) {
		this.terceros = terceros;
	}
	/**
	 * @return the maxTerceros
	 */
	public BigDecimal getMaxTerceros() {
		return maxTerceros;
	}
	/**
	 * @param maxTerceros the maxTerceros to set
	 */
	public void setMaxTerceros(BigDecimal maxTerceros) {
		this.maxTerceros = maxTerceros;
	}
	/**
	 * @return the diario
	 */
	public BigDecimal getDiario() {
		return diario;
	}
	/**
	 * @param diario the diario to set
	 */
	public void setDiario(BigDecimal diario) {
		this.diario = diario;
	}
	/**
	 * @return the maxInternacionalesSugerido
	 */
	public BigDecimal getMaxInternacionalesSugerido() {
		return maxInternacionalesSugerido;
	}
	/**
	 * @param maxInternacionalesSugerido the maxInternacionalesSugerido to set
	 */
	public void setMaxInternacionalesSugerido(BigDecimal maxInternacionalesSugerido) {
		this.maxInternacionalesSugerido = maxInternacionalesSugerido;
	}
	/**
	 * @return the maxInternacionales
	 */
	public BigDecimal getMaxInternacionales() {
		return maxInternacionales;
	}
	/**
	 * @param maxInternacionales the maxInternacionales to set
	 */
	public void setMaxInternacionales(BigDecimal maxInternacionales) {
		this.maxInternacionales = maxInternacionales;
	}
	

}
