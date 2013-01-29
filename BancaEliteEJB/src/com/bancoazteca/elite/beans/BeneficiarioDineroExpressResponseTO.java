package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class BeneficiarioDineroExpressResponseTO implements Serializable{

	private static final long serialVersionUID = -2818823821266679976L;
	private String idBeneficiario;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String fechaNacimiento;
	private String tokenCode;
	private String opcionSeguridad;
	private String user;
	private String optionDispositive;
	private String clave;
	private DispositivoHuellaTO dispositivoHuellaTO;
	
	private Map<String,String> actionErrors;
	
	private Collection clientes;
	private Collection beneficiarios;
	
	public Map<String, String> getActionErrors() {
		return actionErrors;
	}
	public void setActionErrors(Map<String, String> actionErrors) {
		this.actionErrors = actionErrors;
	}
	public String getIdBeneficiario() {
		return idBeneficiario;
	}
	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
	public String getOpcionSeguridad() {
		return opcionSeguridad;
	}
	public void setOpcionSeguridad(String opcionSeguridad) {
		this.opcionSeguridad = opcionSeguridad;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getOptionDispositive() {
		return optionDispositive;
	}
	public void setOptionDispositive(String optionDispositive) {
		this.optionDispositive = optionDispositive;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Collection getClientes() {
		if( clientes == null )
			return new ArrayList();
		return clientes;
	}

	public void setClientes(Collection clientes) {
		this.clientes = clientes;
	}
	public Collection getBeneficiarios() {
		if( beneficiarios == null )
			return new ArrayList();
		else
			return beneficiarios;
	}
	public void setBeneficiarios(Collection beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
}
