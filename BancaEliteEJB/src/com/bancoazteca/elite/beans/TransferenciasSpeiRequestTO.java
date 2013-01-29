package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class TransferenciasSpeiRequestTO implements Serializable {

	private static final long serialVersionUID = -2601695352054665345L;
	
	private String user;
	private String method;
	private String tipotefspeiStr;
	private String emailDestino;
	private String origen;
	private String rfcord;
	private String destino;
	private String bancos;
	private String tipo;
	private String beneficiario;
	private String impuesto;
	private String benefRFC;
	private String benefIVA;
	private String importe;
	private String referencia;
	private String cobranza;
	private String fechaProgramacion;
	private String submit;
	private String tokencode;
	private String mensaje;
	private String clave;
	private String optionDispositive;
	private boolean frecuenteBloqueada;
	
	public boolean isFrecuenteBloqueada() {
		return frecuenteBloqueada;
	}
	public void setFrecuenteBloqueada(boolean frecuenteBloqueada) {
		this.frecuenteBloqueada = frecuenteBloqueada;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getTipotefspeiStr() {
		return tipotefspeiStr;
	}
	public void setTipotefspeiStr(String tipotefspeiStr) {
		this.tipotefspeiStr = tipotefspeiStr;
	}
	public String getEmailDestino() {
		return emailDestino;
	}
	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getRfcord() {
		return rfcord;
	}
	public void setRfcord(String rfcord) {
		this.rfcord = rfcord;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getBancos() {
		return bancos;
	}
	public void setBancos(String bancos) {
		this.bancos = bancos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	public String getBenefRFC() {
		return benefRFC;
	}
	public void setBenefRFC(String benefRFC) {
		this.benefRFC = benefRFC;
	}
	public String getBenefIVA() {
		return benefIVA;
	}
	public void setBenefIVA(String benefIVA) {
		this.benefIVA = benefIVA;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCobranza() {
		return cobranza;
	}
	public void setCobranza(String cobranza) {
		this.cobranza = cobranza;
	}
	public String getFechaProgramacion() {
		return fechaProgramacion;
	}
	public void setFechaProgramacion(String fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getTokencode() {
		return tokencode;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getOptionDispositive() {
		return optionDispositive;
	}
	public void setOptionDispositive(String optionDispositive) {
		this.optionDispositive = optionDispositive;
	}
	
}
