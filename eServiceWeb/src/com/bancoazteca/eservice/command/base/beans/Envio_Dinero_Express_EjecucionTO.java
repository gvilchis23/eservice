package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Envio_Dinero_Express_EjecucionTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	
	private String nombre_beneficiario;
	private String apellido_paterno;
	private String apellido_materno;
	private String pais;
	private String estado;
	
	private String ciudad;
	private String agente_nombre;
	private String sucursal_nombre;
	
	
	private String monto_enviar;
	private String comision;
	private String descuento;
	private String subtotal;
	private String impuestos;
	private String total_pagar;
	private String folio_envio;
	private String remitente;

	private String nombre_divisa;
	private String nombre_corto_divisa;
	private String monto_recibir;
	private String tipo_cambio;
	
	
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getAgente_nombre() {
		return agente_nombre;
	}
	public void setAgente_nombre(String agenteNombre) {
		agente_nombre = agenteNombre;
	}
	public String getSucursal_nombre() {
		return sucursal_nombre;
	}
	public void setSucursal_nombre(String sucursalNombre) {
		sucursal_nombre = sucursalNombre;
	}
	public String getNombre_divisa() {
		return nombre_divisa;
	}
	public void setNombre_divisa(String nombreDivisa) {
		nombre_divisa = nombreDivisa;
	}
	public String getNombre_corto_divisa() {
		return nombre_corto_divisa;
	}
	public void setNombre_corto_divisa(String nombreCortoDivisa) {
		nombre_corto_divisa = nombreCortoDivisa;
	}
	public String getMonto_recibir() {
		return monto_recibir;
	}
	public void setMonto_recibir(String montoRecibir) {
		monto_recibir = montoRecibir;
	}
	public String getTipo_cambio() {
		return tipo_cambio;
	}
	public void setTipo_cambio(String tipoCambio) {
		tipo_cambio = tipoCambio;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}
	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}
	public String getMonto_enviar() {
		return monto_enviar;
	}
	public void setMonto_enviar(String monto_enviar) {
		this.monto_enviar = monto_enviar;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}
	public String getTotal_pagar() {
		return total_pagar;
	}
	public void setTotal_pagar(String total_pagar) {
		this.total_pagar = total_pagar;
	}
	public String getFolio_envio() {
		return folio_envio;
	}
	public void setFolio_envio(String folio_envio) {
		this.folio_envio = folio_envio;
	}

}
