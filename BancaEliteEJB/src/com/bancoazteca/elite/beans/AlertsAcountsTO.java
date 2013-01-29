package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class AlertsAcountsTO implements Serializable{
	
	

	private static final long serialVersionUID = 6400921741440062147L;
	
	
	private String compania;
	private String datosExistentes;	
	private String cuenta;
	private String telefono;
	private String cuentaFormateada;
	private String modo;
	private String tipo;
	private String deposito;
	private String retiro;
	private String indice;
	private String abonos;
	private String cargos;
	private String fechaPago;
	private String minAbonos;
	private String minCargos;
	private String minDeposito;
	private String minCuenta;
	
	
	
	public String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getDatosExistentes() {
		return datosExistentes;
	}
	public void setDatosExistentes(String datosExistentes) {
		this.datosExistentes = datosExistentes;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getRetiro() {
		return retiro;
	}
	public void setRetiro(String retiro) {
		this.retiro = retiro;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCuentaFormateada() {
		return cuentaFormateada;
	}
	public void setCuentaFormateada(String cuentaFormateada) {
		this.cuentaFormateada = cuentaFormateada;
	}
	public String getModo() {
		return modo;
	}
	public void setModo(String modo) {
		this.modo = modo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public String getAbonos() {
		return abonos;
	}
	public void setAbonos(String abonos) {
		this.abonos = abonos;
	}
	public String getCargos() {
		return cargos;
	}
	public void setCargos(String cargos) {
		this.cargos = cargos;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getMinAbonos() {
		return minAbonos;
	}
	public void setMinAbonos(String minAbonos) {
		this.minAbonos = minAbonos;
	}
	public String getMinCargos() {
		return minCargos;
	}
	public void setMinCargos(String minCargos) {
		this.minCargos = minCargos;
	}
	public String getMinDeposito() {
		return minDeposito;
	}
	public void setMinDeposito(String minDeposito) {
		this.minDeposito = minDeposito;
	}
	public String getMinCuenta() {
		return minCuenta;
	}
	public void setMinCuenta(String minCuenta) {
		this.minCuenta = minCuenta;
	}
}
