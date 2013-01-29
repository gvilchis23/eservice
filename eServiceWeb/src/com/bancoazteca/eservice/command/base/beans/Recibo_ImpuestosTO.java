package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Recibo_ImpuestosTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String titulo_pago;
	private String datos_generales;
	
	private String nombre;
	private String calle;
	private String colonia;
	private String CP;
	private String municipio;
	private String ciudad;
	
	private String concepto;
	private String ejercicio;
	private String bimestre;
	private String cuenta;
	
	private String banco;
	private String numero_autorizacion;
	private String fecha_pago;
	
	private String linea_captura;
	private String sello_digital;
	private String total_pagado;
	public String getTitulo_pago() {
		return titulo_pago;
	}
	public void setTitulo_pago(String tituloPago) {
		titulo_pago = tituloPago;
	}
	public String getDatos_generales() {
		return datos_generales;
	}
	public void setDatos_generales(String datosGenerales) {
		datos_generales = datosGenerales;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	public String getBimestre() {
		return bimestre;
	}
	public void setBimestre(String bimestre) {
		this.bimestre = bimestre;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getNumero_autorizacion() {
		return numero_autorizacion;
	}
	public void setNumero_autorizacion(String numeroAutorizacion) {
		numero_autorizacion = numeroAutorizacion;
	}
	public String getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(String fechaPago) {
		fecha_pago = fechaPago;
	}
	public String getLinea_captura() {
		return linea_captura;
	}
	public void setLinea_captura(String lineaCaptura) {
		linea_captura = lineaCaptura;
	}
	public String getSello_digital() {
		return sello_digital;
	}
	public void setSello_digital(String selloDigital) {
		sello_digital = selloDigital;
	}
	public String getTotal_pagado() {
		return total_pagado;
	}
	public void setTotal_pagado(String totalPagado) {
		total_pagado = totalPagado;
	}
	
	
	
	
	
}
