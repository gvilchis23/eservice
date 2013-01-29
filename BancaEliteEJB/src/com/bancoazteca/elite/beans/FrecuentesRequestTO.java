package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Map;

public class FrecuentesRequestTO implements Serializable{

	private static final long serialVersionUID = -888979631656l;
	
	private String user;
	private Map<String, String> parametros;
	private String tarjeta;
	private String alias;
	private String referencia;
	private String telefono;
	private String index;
	private String method;
	private String banco;
	private String tipoTarjeta;
	
	private String paso;
	private String servicio;
	private String userId;
	private String tipoServicio;
	private String referenciaServicio;
	
	private String tokencode;
	private String clave;
	private String optionDispositive;
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Map<String, String> getParametros() {
		return parametros;
	}
	public void setParametros(Map<String, String> parametros) {
		this.parametros = parametros;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPaso() {
		return paso;
	}
	public void setPaso(String paso) {
		this.paso = paso;
	}
	public String getReferenciaServicio() {
		return referenciaServicio;
	}
	public void setReferenciaServicio(String referenciaServicio) {
		this.referenciaServicio = referenciaServicio;
	}
	public String getTokencode() {
		return tokencode;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public String getClave() {
		return clave;
	}
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
