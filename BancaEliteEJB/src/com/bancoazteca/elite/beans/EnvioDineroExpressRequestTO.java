package com.bancoazteca.elite.beans;

import java.io.Serializable;


public class EnvioDineroExpressRequestTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String mensajeMail;
	
	private String user;
	private String cuenta_cargo; 
	private String nombre_beneficiario;
	private String apellido_paterno;
	private String apellido_materno;
	private String fecha_nacimiento;
	private String pais;
	private String estado;
	private String ciudad;
	
	private String agente;
	private String sucursal;
	
	private String paisNombre;
	private String estadoNombre;
	private String ciudadNombre;
	private String monto_enviar;
	private String comision;
	private String descuento;
	private String subtotal;
	private String impuestos;
	private String total_pagar;
	private String clave_seguridad;
	private String opcion_seguridad;
	private String tokenCode;
	private String idBeneficiario;
	private String aceptarContrato;
	
	
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getAgente() {
		return agente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMensajeMail() {
		return mensajeMail;
	}
	public void setMensajeMail(String mensajeMail) {
		this.mensajeMail = mensajeMail;
	}
	public void setAgente(String agente) {
		this.agente = agente;
	}
	public String getAceptarContrato() {
		return aceptarContrato;
	}
	public void setAceptarContrato(String aceptarContrato) {
		this.aceptarContrato = aceptarContrato;
	}
	public String getIdBeneficiario() {
		return idBeneficiario;
	}
	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}
	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
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
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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
	public String getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(String clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
	public String getPaisNombre() {
		return paisNombre;
	}
	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}
	public String getEstadoNombre() {
		return estadoNombre;
	}
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
	public String getCiudadNombre() {
		return ciudadNombre;
	}
	public void setCiudadNombre(String ciudadNombre) {
		this.ciudadNombre = ciudadNombre;
	}
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

}
