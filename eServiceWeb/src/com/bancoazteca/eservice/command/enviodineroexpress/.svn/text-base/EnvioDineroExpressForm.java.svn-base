package com.bancoazteca.eservice.command.enviodineroexpress;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EnvioDineroExpressForm extends FormBean{
	
	private String cuenta_cargo; 
	private String nombre_beneficiario;
	private String apellido_paterno;
	private String apellido_materno;
	private String pais;
	private String estado;
	private String ciudad;
	
	private String agente;
	private String sucursal;
	
	private String monto_enviar;
	private String comision;
	private String descuento;
	private String subtotal;
	private String impuestos;
	private String total_pagar;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	private String id_beneficiario;
	private String aceptar_contrato;
	
	
	
	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getAceptar_contrato() {
		return aceptar_contrato;
	}

	public void setAceptar_contrato(String aceptarContrato) {
		aceptar_contrato = aceptarContrato;
	}

	public String getId_beneficiario() {
		return id_beneficiario;
	}

	public void setId_beneficiario(String idBeneficiario) {
		id_beneficiario = idBeneficiario;
	}

	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();	
		
	   if (getComando().equalsIgnoreCase("validacion")) {


		if (Validator.isEmptyData(cuenta_cargo) || cuenta_cargo.startsWith("-")){
			error.add("cuenta_cargo", VALIDATOR_DINERO_EXPRESS_CUENTA_CARGO);
		}

		if (Validator.isEmptyData(pais) || pais.startsWith("-")){
			error.add("pais", VALIDATOR_DINERO_EXPRESS_PAIS);
		}
		if (Validator.isEmptyData(estado) || estado.startsWith("-")){
			estado="";			
		}

		if (Validator.isEmptyData(monto_enviar)){
			error.add("monto_enviar", VALIDATOR_DINERO_EXPRESS_MONTO_ENVIAR);
		}		
	  }	   
		return error;
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
	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}
	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}

}
