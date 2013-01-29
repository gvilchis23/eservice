package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class RecuperaPasswordResponseTO implements Serializable {

	private static final long serialVersionUID = 7394465444991898258L;
		
	private String celular;
	private String correo;
	private String carrier;
	private String compania;
	private String alias;
	private String cliente;
	private Collection<String> listaCompanias;
	
	/*
	 * Segunda version
	 */
	private String passwordTel; 
	private String tarjetaasociada; 
	private String email;
	private String folio;
	private String pregunta; 
	private String activacion; 
	private String respuesta;
	private String cuentaasociada; 
	private String subproducto; 
	private String descripcion; 
	private String telefono; 
	private String nacimiento; 
	private String apellidos; 
	private String passwordConfirm;  
	private String cuenta; 
	private String producto; 
	private String username; 
	private String nombre; 
	private String contrato; 
	private String password;
	private String holderType; 
	private String paterno; 
	private String nombreCompleto; 
	private String materno; 
	private String telefonoOficina; 
	private String fiscal; 
	
	
	
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Collection<String> getListaCompanias() {
		return listaCompanias;
	}
	public void setListaCompanias(Collection<String> listaCompanias) {
		this.listaCompanias = listaCompanias;
	}
	public String getPasswordTel() {
		return passwordTel;
	}
	public void setPasswordTel(String passwordTel) {
		this.passwordTel = passwordTel;
	}
	public String getTarjetaasociada() {
		return tarjetaasociada;
	}
	public void setTarjetaasociada(String tarjetaasociada) {
		this.tarjetaasociada = tarjetaasociada;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getActivacion() {
		return activacion;
	}
	public void setActivacion(String activacion) {
		this.activacion = activacion;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getCuentaasociada() {
		return cuentaasociada;
	}
	public void setCuentaasociada(String cuentaasociada) {
		this.cuentaasociada = cuentaasociada;
	}
	public String getSubproducto() {
		return subproducto;
	}
	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHolderType() {
		return holderType;
	}
	public void setHolderType(String holderType) {
		this.holderType = holderType;
	}
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getTelefonoOficina() {
		return telefonoOficina;
	}
	public void setTelefonoOficina(String telefonoOficina) {
		this.telefonoOficina = telefonoOficina;
	}
	public String getFiscal() {
		return fiscal;
	}
	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
