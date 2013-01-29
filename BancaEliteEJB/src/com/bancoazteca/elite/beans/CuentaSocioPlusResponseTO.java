package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class CuentaSocioPlusResponseTO implements Serializable{

	
	private static final long serialVersionUID = 2628481369071013848L;
	
	private String validaCuentaSocioPlus;
	
	private String  valorUnidad;
	private Collection<CuentaLoTO> cuentas;
	private String date;
	private SocioPlusTO socioPlusTO;
	private byte[] pdf;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private String montoMinimo;
	
	
	private String cuentaCargo;
	private String montoInvertir;
	private String comision;
	private String ivaComision;
	private String totalUnidades;
	private String referencia;
	private String fechaValorUnidad;
	
	private String folioApertura;
	
	private ClienteTO clienteTO;
	
	private String numeroCuentaSocioPlus;
	private String unidadesAdquiridas;
	

	public String getNumeroCuentaSocioPlus() {
		return numeroCuentaSocioPlus;
	}
	public void setNumeroCuentaSocioPlus(String numeroCuentaSocioPlus) {
		this.numeroCuentaSocioPlus = numeroCuentaSocioPlus;
	}
	public String getUnidadesAdquiridas() {
		return unidadesAdquiridas;
	}
	public void setUnidadesAdquiridas(String unidadesAdquiridas) {
		this.unidadesAdquiridas = unidadesAdquiridas;
	}
	public ClienteTO getClienteTO() {
		return clienteTO;
	}
	public void setClienteTO(ClienteTO clienteTO) {
		this.clienteTO = clienteTO;
	}
	public String getFolioApertura() {
		return folioApertura;
	}
	public void setFolioApertura(String folioApertura) {
		this.folioApertura = folioApertura;
	}
	public byte[] getPdf() {
		return pdf;
	}
	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getValorUnidad() {
		return valorUnidad;
	}
	public void setValorUnidad(String valorUnidad) {
		this.valorUnidad = valorUnidad;
	}
	
	public Collection<CuentaLoTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaLoTO> cuentas) {
		this.cuentas = cuentas;
	}
	public SocioPlusTO getSocioPlusTO() {
		return socioPlusTO;
	}
	public void setSocioPlusTO(SocioPlusTO socioPlusTO) {
		this.socioPlusTO = socioPlusTO;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public String getMontoMinimo() {
		return montoMinimo;
	}
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getMontoInvertir() {
		return montoInvertir;
	}
	public void setMontoInvertir(String montoInvertir) {
		this.montoInvertir = montoInvertir;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getIvaComision() {
		return ivaComision;
	}
	public void setIvaComision(String ivaComision) {
		this.ivaComision = ivaComision;
	}
	public String getTotalUnidades() {
		return totalUnidades;
	}
	public void setTotalUnidades(String totalUnidades) {
		this.totalUnidades = totalUnidades;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getFechaValorUnidad() {
		return fechaValorUnidad;
	}
	public void setFechaValorUnidad(String fechaValorUnidad) {
		this.fechaValorUnidad = fechaValorUnidad;
	}
	public String getValidaCuentaSocioPlus() {
		return validaCuentaSocioPlus;
	}
	public void setValidaCuentaSocioPlus(String validaCuentaSocioPlus) {
		this.validaCuentaSocioPlus = validaCuentaSocioPlus;
	}

}
