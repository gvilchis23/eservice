package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class ConfirmarTransferenciaTercerosTO implements Serializable {

	private static final long serialVersionUID = 6457172901607197433L;
	
	private String form;
	private String cuentaAbono;
	private String folio;
	private String destinoCheck;
	private String existeComisionInvAzt;
	private String importeCheck;
	private String comisionMasIva;
	private String destino;
	private String clave;
	private String montoTotal;
	private String servletWrapper;
	private String multipartRequestHandler;
	private String newpin;
	private String confnewpin;
	private String conceptoCheck;
	private String referencia;
	private String account;
	private String cuentaCargo;
	private String mensajeRetecion;
	private BigDecimal comisionRetiroAntInvAzt;
	private String fechaAplicacion;
	private String emailDestino;
	private String titular;
	private String origenCheck;
	private BigDecimal ivaRetiroAntInvAzt;
	private BigDecimal ammount;
	private String concepto;
	private String tokencode;
	private String botonTraspaso;
	private String referenciaCheck;
	private BigDecimal importe;
	private String gratisComisionInvAzt;
	private String numeroTarjeta;
	private String source;
	private String origen;
	private Collection<CuentaTransaccionesTO> cuentas;
	
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getCuentaAbono() {
		return cuentaAbono;
	}
	public void setCuentaAbono(String cuentaAbono) {
		this.cuentaAbono = cuentaAbono;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Collection<CuentaTransaccionesTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaTransaccionesTO> cuentas) {
		this.cuentas = cuentas;
	}
	public String getDestinoCheck() {
		return destinoCheck;
	}
	public void setDestinoCheck(String destinoCheck) {
		this.destinoCheck = destinoCheck;
	}
	public String getExisteComisionInvAzt() {
		return existeComisionInvAzt;
	}
	public void setExisteComisionInvAzt(String existeComisionInvAzt) {
		this.existeComisionInvAzt = existeComisionInvAzt;
	}
	public String getImporteCheck() {
		return importeCheck;
	}
	public void setImporteCheck(String importeCheck) {
		this.importeCheck = importeCheck;
	}
	public String getComisionMasIva() {
		return comisionMasIva;
	}
	public void setComisionMasIva(String comisionMasIva) {
		this.comisionMasIva = comisionMasIva;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getServletWrapper() {
		return servletWrapper;
	}
	public void setServletWrapper(String servletWrapper) {
		this.servletWrapper = servletWrapper;
	}
	public String getMultipartRequestHandler() {
		return multipartRequestHandler;
	}
	public void setMultipartRequestHandler(String multipartRequestHandler) {
		this.multipartRequestHandler = multipartRequestHandler;
	}
	public String getNewpin() {
		return newpin;
	}
	public void setNewpin(String newpin) {
		this.newpin = newpin;
	}
	public String getConfnewpin() {
		return confnewpin;
	}
	public void setConfnewpin(String confnewpin) {
		this.confnewpin = confnewpin;
	}
	public String getConceptoCheck() {
		return conceptoCheck;
	}
	public void setConceptoCheck(String conceptoCheck) {
		this.conceptoCheck = conceptoCheck;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getMensajeRetecion() {
		return mensajeRetecion;
	}
	public void setMensajeRetecion(String mensajeRetecion) {
		this.mensajeRetecion = mensajeRetecion;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getEmailDestino() {
		return emailDestino;
	}
	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getOrigenCheck() {
		return origenCheck;
	}
	public void setOrigenCheck(String origenCheck) {
		this.origenCheck = origenCheck;
	}
	public BigDecimal getAmmount() {
		return ammount;
	}
	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getTokencode() {
		return tokencode;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public String getBotonTraspaso() {
		return botonTraspaso;
	}
	public void setBotonTraspaso(String botonTraspaso) {
		this.botonTraspaso = botonTraspaso;
	}
	public String getReferenciaCheck() {
		return referenciaCheck;
	}
	public void setReferenciaCheck(String referenciaCheck) {
		this.referenciaCheck = referenciaCheck;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getGratisComisionInvAzt() {
		return gratisComisionInvAzt;
	}
	public void setGratisComisionInvAzt(String gratisComisionInvAzt) {
		this.gratisComisionInvAzt = gratisComisionInvAzt;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public BigDecimal getComisionRetiroAntInvAzt() {
		return comisionRetiroAntInvAzt;
	}
	public void setComisionRetiroAntInvAzt(BigDecimal comisionRetiroAntInvAzt) {
		this.comisionRetiroAntInvAzt = comisionRetiroAntInvAzt;
	}
	public BigDecimal getIvaRetiroAntInvAzt() {
		return ivaRetiroAntInvAzt;
	}
	public void setIvaRetiroAntInvAzt(BigDecimal ivaRetiroAntInvAzt) {
		this.ivaRetiroAntInvAzt = ivaRetiroAntInvAzt;
	}
	
}
