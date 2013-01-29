package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class CancelacionElementTO implements Serializable {
	
	private static final long serialVersionUID = -1936089850084225129L;
	private String estadoAnterior;
	private String nombre;
	private String opcion;
	private String estado;
	private String tarjeta; 
	private String cuenta;
	private String situacion; 
	private String tipo;
	private String fechaUltimoMovimiento;	
	private String folio;
	
	
	private String tokencode;
	private String clave;
	private String ammount; 	
	private String newpin; 
	private String motivo; 
	private String userPasswordF;	
	private String servletWrapper;		
	private String userPassword;
	private String tarjetas;
	private String nueva;
	private String tarjetaCheck;
	private String multipartRequestHandler;
	private String confnewpin;
	
	
	private SecurityLevelTO securityLevelTO;
	
	public SecurityLevelTO getSecurityLevelTO() {
		return securityLevelTO;
	}
	public void setSecurityLevelTO(SecurityLevelTO securityLevelTO) {
		this.securityLevelTO = securityLevelTO;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getSituacion() {
		return situacion;
	}
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaUltimoMovimiento() {
		return fechaUltimoMovimiento;
	}
	public void setFechaUltimoMovimiento(String fechaUltimoMovimiento) {
		this.fechaUltimoMovimiento = fechaUltimoMovimiento;
	}
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
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
	public String getAmmount() {
		return ammount;
	}
	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}
	public String getNewpin() {
		return newpin;
	}
	public void setNewpin(String newpin) {
		this.newpin = newpin;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getUserPasswordF() {
		return userPasswordF;
	}
	public void setUserPasswordF(String userPasswordF) {
		this.userPasswordF = userPasswordF;
	}
	public String getServletWrapper() {
		return servletWrapper;
	}
	public void setServletWrapper(String servletWrapper) {
		this.servletWrapper = servletWrapper;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(String tarjetas) {
		this.tarjetas = tarjetas;
	}
	public String getNueva() {
		return nueva;
	}
	public void setNueva(String nueva) {
		this.nueva = nueva;
	}
	public String getTarjetaCheck() {
		return tarjetaCheck;
	}
	public void setTarjetaCheck(String tarjetaCheck) {
		this.tarjetaCheck = tarjetaCheck;
	}
	public String getMultipartRequestHandler() {
		return multipartRequestHandler;
	}
	public void setMultipartRequestHandler(String multipartRequestHandler) {
		this.multipartRequestHandler = multipartRequestHandler;
	}
	public String getConfnewpin() {
		return confnewpin;
	}
	public void setConfnewpin(String confnewpin) {
		this.confnewpin = confnewpin;
	}
	public String getEstadoAnterior() {
		return estadoAnterior;
	}
	public void setEstadoAnterior(String estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	} 

}
